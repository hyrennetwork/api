package net.hyren.web.api.misc.services

import net.hyren.web.api.applications.http.middleware.Middleware
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author Gutyerrez
 */
@Component
class MiddlewareService(
	private vararg val middlewares: Middleware
) : WebMvcConfigurer {

	override fun addInterceptors(
		interceptorRegistry: InterceptorRegistry
	) {
		middlewares.forEach {
			interceptorRegistry.addInterceptor(it)
		}
	}

}