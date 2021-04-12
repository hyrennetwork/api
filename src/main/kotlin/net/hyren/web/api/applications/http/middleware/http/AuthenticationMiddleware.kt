package net.hyren.web.api.applications.http.middleware.http

import net.hyren.web.api.APIConstants
import net.hyren.web.api.applications.http.middleware.Middleware
import net.hyren.web.api.misc.http.HttpResponse
import net.hyren.web.api.misc.http.send
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Gutyerrez
 */
@Component
class AuthenticationMiddleware : Middleware {

	override fun handle(
		request: HttpServletRequest,
		response: HttpServletResponse,
		handler: Any
	): Boolean {
		if (request.servletPath == "/") return true

		println("Não é a rota principal")

		val authorization = request.getHeader("Authorization")

		println("Validando token")

		if (authorization !== null && authorization == "${APIConstants.APPLICATION_TYPE} ${APIConstants.APPLICATION_KEY}") {
			println("Token válido")

			return true
		}

		println("Token inválido")

		return response.send(
			403,
			HttpResponse(
				false,
				"Acesso negado"
			)
		)
	}

}