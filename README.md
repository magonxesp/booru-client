# Booru client

Danbooru, yande.re and konachan client for kotlin

## Instalation

Add the dependency and the required dependencies

```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_corroutines_version") // required
implementation("io.github.magonxesp:booru-client:1.0.0")
```

## Usage

Search by tag on Konachan for example.

```kotlin
import io.github.magonxesp.booruclient.konachan.KonachanClient
import kotlinx.coroutines.runBlocking

runBlocking {
    val client = KonachanClient()

    client.search {
        tag("sousou_no_frieren")
    }.forEach { post ->
        println(post.previewUrl)
    }
}

```

The `search` method has a lambda that builds the search query.

For the Konachan client has the following methods:
* `tag`
* `size`
* `order`
* `rating`

The other clients have only the `tag` method for search.

### Available clients

* `io.github.magonxesp.booruclient.danbooru.DanbooruClient` The Danbooru client
* `io.github.magonxesp.booruclient.konachan.KonachanClient` The Konachan sfw client
* `io.github.magonxesp.booruclient.konachan.KonachanNsfwClient` The Konachan nsfw client
* `io.github.magonxesp.booruclient.yandere.YandereClient` The Yande.re client
* `io.github.magonxesp.booruclient.safebooru.SafebooruClient.kt` The Safebooru client
