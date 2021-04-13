package net.hyren.web.api.applications.http.middleware.http

import com.redefantasy.core.shared.misc.jackson.builder.JsonBuilder
import net.hyren.web.api.APIConstants
import net.hyren.web.api.applications.http.middleware.IMiddleware
import net.hyren.web.api.misc.http.send
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Gutyerrez
 */
@Component
class AuthenticationMiddleware : IMiddleware {

	override fun handle(
		request: HttpServletRequest,
		response: HttpServletResponse,
		handler: Any
	): Boolean {
		if (request.servletPath == "/") return true

		val authorization = request.getHeader("Authorization")

		if (authorization !== null && authorization == "${APIConstants.APPLICATION_TYPE} ${APIConstants.APPLICATION_KEY}") {
			return true
		}

		return response.send(
			403,
			JsonBuilder().append(
				"success", false
			).append(
				"message", "Acesso negado"
			).build()
		)
	}

}