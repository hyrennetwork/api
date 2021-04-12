package hyren.net.web.api

import com.redefantasy.core.shared.CoreProvider
import com.redefantasy.core.shared.applications.ApplicationType
import com.redefantasy.core.shared.applications.data.Application
import hyren.net.web.api.applications.http.middleware.http.AuthenticationMiddleware
import hyren.net.web.api.services.Middleware
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.InetSocketAddress

/**
 * @author Gutyerrez
 */
@SpringBootApplication
class WebAPIApplication {

	companion object {

		@JvmStatic
		fun main(args: Array<String>) {
			CoreProvider.prepare(
				Application(
					"api",
					"API",
					null,
					InetSocketAddress(
						"0.0.0.0",
						0
					),
					ApplicationType.GENERIC,
					null,
					null
				)
			)

			runApplication<WebAPIApplication>()

			Middleware(
				AuthenticationMiddleware()
			)
		}

	}

}