package io.github.magonxesp.booruclient

sealed class ClientException(override val message: String? = null) : Exception(message) {
	class InvalidTagFormat(override val message: String? = null) : ClientException(message)
	class RequestFailed(override val message: String? = null) : ClientException(message)
	class InvalidLimit(override val message: String? = null) : ClientException(message)
	class InvalidPage(override val message: String? = null) : ClientException(message)
	class ParseError(override val message: String? = null) : ClientException(message)
}
