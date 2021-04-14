package net.hyren.web.api.applications.http

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author Gutyerrez
 */
@Component
class Kernel : WebMvcConfigurer {

	val CORS = arrayOf(
		"http://localhost:3000",
		"https://hyren.net",
		"http://hyren.net"
	)

	override fun addCorsMappings(
		corsRegistry: CorsRegistry
	) {
		corsRegistry.addMapping("/**").allowedOrigins(
			*CORS
		)
	}

}