package net.hyren.web.api.applications.http.controller

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.redefantasy.core.shared.CoreProvider
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
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
		@PathVariable id: Any
	): JsonMapper? {
		val user = when (id) {
			is String -> CoreProvider.Cache.Local.USERS.provide().fetchByName(id)
			is UUID -> CoreProvider.Cache.Local.USERS.provide().fetchById(id)
			else -> null
		} ?: return null

		return jsonMapper {
			user.name
		}
	}

}