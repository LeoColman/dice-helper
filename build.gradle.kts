/*
 * Copyright 2020 Leonardo Colman Lopes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either press or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.lang.System.getenv

plugins {
    kotlin("jvm") version "1.5.10"
    `maven-publish`
    signing
    id("org.jetbrains.dokka") version "1.4.32"
    id("io.gitlab.arturbosch.detekt").version("1.15.0-RC1")
}

// FIXME Replace with group
group = "br.com.colman."
version = getenv("RELEASE_VERSION") ?: "local"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:4.6.0")
    
    // Mockk
    testImplementation("io.mockk:mockk:1.11.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    explicitApi()
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    dependsOn("dokkaHtml")
    archiveClassifier.set("javadoc")
    from("$buildDir/dokka")
}


publishing {
    repositories {

        maven("https://oss.sonatype.org/service/local/staging/deploy/maven2") {
            credentials {
                username = getenv("OSSRH_USERNAME")
                password = getenv("OSSRH_PASSWORD")
            }
        }
    }

    publications {

        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())

            pom {
                name.set("NAME")    // FIXME change name
                description.set("DESCRIPTION") //FIXME change description
                url.set("https://www.github.com/LeoColman/repo") // FIXME change URL


                scm {
                    connection.set("scm:git:http://www.github.com/LeoColman/repo") // FIXME change URL
                    developerConnection.set("scm:git:http://github.com/LeoColman/repo") // FIXME change URL
                    url.set("https://www.github.com/LeoColman/repo") // FIXME change URL
                }

                licenses {
                    license {
                        name.set("The Apache 2.0 License")
                        url.set("https://opensource.org/licenses/Apache-2.0")
                    }
                }

                developers {
                    developer {
                        id.set("LeoColman")
                        name.set("Leonardo Colman Lopes")
                        email.set("leonardo.dev@colman.com.br")
                    }
                }
            }
        }
    }
}

val signingKey: String? by project
val signingPassword: String? by project

signing {
    useGpgCmd()
    if(signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(signingKey, signingPassword)
    }

    sign(publishing.publications["mavenJava"])
}
