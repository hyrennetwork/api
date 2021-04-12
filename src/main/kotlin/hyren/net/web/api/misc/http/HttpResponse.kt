package hyren.net.web.api.misc.http

/**
 * @author Gutyerrez
 */
data class HttpResponse(
	val success: Boolean,
	val message: String? = null
)