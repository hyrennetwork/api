package hyren.net.web.api.applications.http.controller

import com.redefantasy.core.shared.CoreProvider
import com.redefantasy.core.shared.users.passwords.storage.dto.FetchUserPasswordByUserIdDTO
import hyren.net.web.api.misc.http.HttpResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/**
 * @author Gutyerrez
 */
@RestController
@RequestMapping(
	consumes = [ MediaType.APPLICATION_JSON_VALUE ],
	produces = [ MediaType.APPLICATION_JSON_VALUE ]
)
class AccountController {

	@PostMapping(
		path = [ "/accounts" ],
		params = [ "username", "password" ]
	)
	fun authenticate(
		@RequestBody
		account: Account?
	): HttpResponse {
		if (account === null) return HttpResponse(
			success = false,
			message = "Parametros inválidos"
		)

		val user = CoreProvider.Cache.Local.USERS.provide().fetchByName(
			account.name
		)

		if (user === null || CoreProvider.Repositories.Postgres.USERS_PASSWORDS_REPOSITORY.provide().fetchByUserId(
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

	data class Account(
		val name: String,
		val password: String
	)

}