package net.hyren.web.api.applications.http.controller

import com.redefantasy.core.shared.CoreProvider
import com.redefantasy.core.shared.users.passwords.storage.dto.FetchUserPasswordByUserIdDTO
import net.hyren.web.api.applications.http.models.Account
import net.hyren.web.api.misc.http.HttpResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Gutyerrez
 */
@RestController
@RequestMapping(
	path = [ "/accounts" ],
	consumes = [MediaType.APPLICATION_JSON_VALUE],
	produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AccountController {

	@PostMapping()
	fun authenticate(
		@RequestBody account: Account
	): HttpResponse {
		val user = CoreProvider.Cache.Local.USERS.provide().fetchByName(
			account.name
		)

		if (user === null || !CoreProvider.Repositories.Postgres.USERS_PASSWORDS_REPOSITORY.provide().fetchByUserId(
				FetchUserPasswordByUserIdDTO(user.getUniqueId())
			).stream()
				.filter { it.enabled }
				.findFirst()
				.isPresent
		) return HttpResponse(
			false,
			"Usuário não está registrado."
		)

		if (!user.attemptLogin(account.password)) return HttpResponse(
			false,
			"A senha inserida está incorreta."
		)

		return HttpResponse(
			true
		)
	}

}