package hyren.net.web.api.applications.http.middleware

import org.springframework.web.servlet.AsyncHandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Gutyerrez
 */
interface Middleware : AsyncHandlerInterceptor {

	override fun preHandle(
		httpServletRequest: HttpServletRequest,
		httpServletResponse: HttpServletResponse,
		handler: Any
	): Boolean

}