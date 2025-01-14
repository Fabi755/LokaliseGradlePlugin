import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin)
    `java-gradle-plugin`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.test.kotlinTest)
    testImplementation(libs.test.strikt)
}

gradlePlugin {
    plugins {
        register("com.ioki.lokalise") {
            id = "com.ioki.lokalise"
            implementationClass = "com.ioki.lokalise.gradle.plugin.LokaliseGradlePlugin"
        }
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

version = "1.0.0"
group = "com.ioki"
publishing {
    publications {
        register("pluginMaven", MavenPublication::class.java) {
            artifactId = "lokalise"
            pom {
                url.set("https://github.com/ioki-mobility/LokaliseGradlePlugin")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/ioki-mobility/LokaliseGradlePlugin/blob/main/LICENSE.md")
                    }
                }
                organization {
                    name.set("ioki")
                    url.set("https://ioki.com")
                }
                developers {
                    developer {
                        name.set("Stefan 'StefMa' M.")
                        email.set("StefMaDev@outlook.com")
                        url.set("https://StefMa.guru")
                        organization.set("ioki")
                    }
                }
                scm {
                    url.set("https://github.com/ioki-mobility/LokaliseGradlePlugin")
                    connection.set("https://github.com/ioki-mobility/LokaliseGradlePlugin.git")
                    developerConnection.set("git@github.com:ioki-mobility/LokaliseGradlePlugin.git")
                }
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}