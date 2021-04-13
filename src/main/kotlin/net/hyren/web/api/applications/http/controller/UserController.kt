package net.hyren.web.api.applications.http.controller

import com.redefantasy.core.shared.CoreProvider
import com.redefantasy.core.shared.users.data.User
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * @author Gutyerrez
 */
@RestController
@RequestMapping(
	path = [ "/users" ],
	produces = [ MediaType.APPLICATION_JSON_VALUE ]
)
class UserController {

	@GetMapping("/{id}")
	fun show(
		id: Any
	): User? {
		return when (id) {
			is String -> CoreProvider.Cache.Local.USERS.provide().fetchByName(id)
			is UUID -> CoreProvider.Cache.Local.USERS.provide().fetchById(id)
			else -> null
		}
	}

}