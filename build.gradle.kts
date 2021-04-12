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
}

dependencies {
	// kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// spring-boot
	implementation("org.springframework.boot:spring-boot-starter-web")

	// commons-lang 3
	implementation("org.apache.commons:commons-lang3:3.11")

	// exposed
	implementation("org.jetbrains.exposed:exposed-dao:0.29.1")
	implementation("org.jetbrains.exposed:exposed-core:0.29.1")
	implementation("org.jetbrains.exposed:exposed-jodatime:0.29.1")

	// redis
	implementation("redis.clients:jedis:3.3.0")

	// influx db
	implementation("org.influxdb:influxdb-java:2.20")

	// jackson
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// eventbus
	implementation("org.greenrobot:eventbus:3.2.0")

	// caffeine
	implementation("com.github.ben-manes.caffeine:caffeine:2.8.5")

	// core-shared
	implementation("com.redefantasy:core-shared:0.1-ALPHA")
}