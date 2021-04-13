package net.hyren.web.api.applications.http.controller

import com.fasterxml.jackson.databind.JsonNode
import com.redefantasy.core.shared.CoreProvider
import com.redefantasy.core.shared.misc.jackson.builder.JsonBuilder
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
	): JsonNode? {
		val user = when (id) {
			is String -> CoreProvider.Cache.Local.USERS.provide().fetchByName(id)
			is UUID -> CoreProvider.Cache.Local.USERS.provide().fetchById(id)
			else -> null
		} ?: return null

		return JsonBuilder().append(
			"id", user.id
		).append(
			"name", user.name
		).append(
			"created_at", user.createdAt
		).build()
	}

}