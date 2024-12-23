plugins {
	java
	id("org.springframework.boot") version "3.4.1-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.aneesh"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")


	implementation("org.json:json:20240303")
	implementation("com.squareup.okhttp3:okhttp:4.12.0")

	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

	// Lombok

	compileOnly ("org.projectlombok:lombok")
	annotationProcessor ("org.projectlombok:lombok")

	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")



}

tasks.withType<Test> {
	useJUnitPlatform()
}
