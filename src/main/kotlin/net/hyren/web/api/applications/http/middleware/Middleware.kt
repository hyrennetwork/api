package net.hyren.web.api.applications.http.middleware

import org.springframework.web.servlet.AsyncHandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Gutyerrez
 */
interface Middleware : AsyncHandlerInterceptor {

	override fun preHandle(
		request: HttpServletRequest,
		response: HttpServletResponse,
		handler: Any
	) = handle(request, response, handler)

	fun handle(
		request: HttpServletRequest,
		response: HttpServletResponse,
		handler: Any
	): Boolean

}