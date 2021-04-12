package net.hyren.web.api

import com.fasterxml.jackson.annotation.JsonInclude
import com.redefantasy.core.shared.CoreConstants

/**
 * @author Gutyerrez
 */
object APIConstants {

	const val APPLICATION_TYPE = "Bearer"
	const val APPLICATION_KEY = "HYREN-095301-1247-658357-ASD"

	init {
		CoreConstants.JACKSON.setSerializationInclusion(
			JsonInclude.Include.NON_NULL
		)
	}

}