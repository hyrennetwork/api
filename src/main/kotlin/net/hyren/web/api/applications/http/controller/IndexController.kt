package net.hyren.web.api.applications.http.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Gutyerrez
 */
@RestController
@RequestMapping(
	produces = [
		MediaType.APPLICATION_JSON_VALUE
	]
)
class IndexController {

	@GetMapping("/")
	fun version() = "{\"Hyren API Version\": \"0.1-ALPHA\"}"

}