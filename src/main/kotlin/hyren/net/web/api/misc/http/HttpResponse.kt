package hyren.net.web.api.misc.http

import com.redefantasy.core.shared.CoreConstants
import org.springframework.http.MediaType
import javax.servlet.http.HttpServletResponse

/**
 * @author Gutyerrez
 */
data class HttpResponse(
	val success: Boolean,
	val message: String? = null
)

fun HttpServletResponse.send(
	status: Int,
	message: Any?
): Boolean {
	this.status = status
	this.contentType = MediaType.APPLICATION_JSON_VALUE

	if (message !== null) this.writer.print(
		CoreConstants.JACKSON.writeValueAsString(
			message
		)
	)

	return status == 200
}

fun HttpServletResponse.send(
	message: Any
) = this.send(200, message)