package hyren.net.web.api.applications.http.middleware.http

import hyren.net.web.api.APIConstants
import hyren.net.web.api.applications.http.middleware.Middleware
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Gutyerrez
 */
@Component
class AuthenticationMiddleware : Middleware {

	override fun preHandle(
		httpServletRequest: HttpServletRequest,
		httpServletResponse: HttpServletResponse,
		handler: Any
	): Boolean {
		if (httpServletRequest.servletPath == "/") return true

		println("Não é a rota principal")

		val authorization = httpServletRequest.getHeader("Authorization")

		println("Validando token")

		if (authorization !== null && authorization == APIConstants.APPLICATION_KEY) {
			println("Token válido")

			return true
		}

		println("Token inválido")

		return false
	}

}