package net.hyren.web.api.applications.http.controller

import com.redefantasy.core.shared.CoreProvider
import com.redefantasy.core.shared.users.data.User
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * @author Gutyerrez
 */
@RestController
@RequestMapping(
	path = [ "/users" ],
	consumes = [MediaType.APPLICATION_JSON_VALUE],
	produces = [ MediaType.APPLICATION_JSON_VALUE ]
)
class UserController {

	@GetMapping("/{id}")
	fun show(
		@RequestParam id: Any
	): User? {
		return when (id) {
			is String -> CoreProvider.Cache.Local.USERS.provide().fetchByName(id)
			is UUID -> CoreProvider.Cache.Local.USERS.provide().fetchById(id)
			else -> null
		}
	}

}