package io.github.magonxesp.booruclient.danbooru

import arrow.core.Either
import arrow.core.left
import io.github.magonxesp.booruclient.Client
import io.github.magonxesp.booruclient.ClientException
import io.github.magonxesp.booruclient.Tag
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class DanbooruClient : Client() {
	override val baseUrl: String = "https://danbooru.donmai.us/"

	class Builder {
		private val criteria: MutableMap<String, String> = mutableMapOf()

		fun tag(tag: String) {
			criteria["tags"] = Tag(tag).value
		}

		fun build() = criteria.values.joinToString(" ")
	}

	suspend fun search(setup: Builder.() -> Unit): Either<ClientException, List<DanbooruPost>> =
		Either.catch {
			val builder = Builder()
			builder.setup()

			val searchTag = builder.build()
			val url = fromBaseUrl("/posts.json", mapOf("tags" to searchTag))
			val response = httpClient.get(url)

			if (!response.status.isSuccess()) {
				return ClientException.RequestFailed("Request to $url failed with status code ${response.status.value} and body: ${response.bodyAsText()}")
					.left()
			}

			val collection = jsonSerializer.decodeFromString<List<DanbooruPost>>(response.bodyAsText())
			collection
		}.mapLeft {
			if (it !is ClientException) {
				ClientException.UnknownError("An unexpected error was occurred: ${it::class}: ${it.message}")
			} else {
				it
			}
		}

}