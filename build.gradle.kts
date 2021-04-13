plugins {
	id("org.springframework.boot") version "2.5.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"

	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.31"
}

group = "hyren.net"
version = "0.0.1"

repositories {
	mavenCentral()

	mavenLocal()

	jcenter()

	maven("https://repo.spring.io/milestone")
	maven("https://repo.spring.io/snapshot")
}

tasks {
	compileKotlin {
		kotlinOptions {
			jvmTarget = "1.8"
		}
	}

	bootJar {
		manifest {
			attributes["Start-Class"] = "net.hyren.web.api.WebAPIApplication"
		}

		val fileName = "${project.name}.jar"

		archiveFileName.set("${project.name}.jar")

		doLast {
			try {
				val file = file("build/libs/$fileName")

				val toDelete = file("/home/cloud/output/$fileName")

				if (toDelete.exists()) toDelete.delete()

				file.copyTo(file("/home/cloud/output/$fileName"))
				file.delete()
			} catch (ex: java.io.FileNotFoundException) {
				ex.printStackTrace()
			}
		}
	}
}

dependencies {
	// kotlin
	compileOnly("org.jetbrains.kotlin:kotlin-reflect")
	compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// spring-boot
	implementation("org.springframework.boot:spring-boot-starter-web")

	// commons-lang 3
	compileOnly("org.apache.commons:commons-lang3:3.11")

	// exposed
	implementation("org.jetbrains.exposed:exposed-dao:0.29.1")
	compileOnly("org.jetbrains.exposed:exposed-core:0.29.1")
	compileOnly("org.jetbrains.exposed:exposed-jodatime:0.29.1")

	// redis
	compileOnly("redis.clients:jedis:3.3.0")

	// influx db
	compileOnly("org.influxdb:influxdb-java:2.20")

	// jackson
	compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-guava:2.11.2")
	compileOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-joda:2.11.2")

	// eventbus
	compileOnly("org.greenrobot:eventbus:3.2.0")

	// caffeine
	compileOnly("com.github.ben-manes.caffeine:caffeine:2.8.5")

	// core-shared
	implementation("com.redefantasy:core-shared:0.1-ALPHA")
}