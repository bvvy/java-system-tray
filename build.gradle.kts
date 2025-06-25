import org.jreleaser.model.Active
import org.jreleaser.model.Signing

plugins {
    id("java")
    id("maven-publish")
    id("signing")
    apply { id("org.jreleaser") version "1.17.0" }
}

group = "io.github.bvvy"
version = "1.0.1"

repositories {
    mavenCentral()
}


java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            // 主JAR文件
            from(components["java"])

            // 使用Dokka生成的文档JAR
//            artifact(dokkaJar.get())

            // 完整POM配置
            pom {
                name.set("java-system-tray")
                description.set("Swing-based system tray with full Unicode support, fixing native Java limitations.")
                url.set("https://github.com/bvvy/java-system-tray")

                // 许可证信息
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }

                // 开发者信息
                developers {
                    developer {
                        id.set("bvvy")
                        name.set("bvvy")
                        email.set("bvvy@qq.com")
                        url.set("https://github.com/bvvy")
                    }
                }

                // SCM信息
                scm {
                    connection.set("scm:git://github.com/bvvy/java-system-tray.git")
                    developerConnection.set("scm:git:ssh://github.com/bvvy/java-system-tray.git")
                    url.set("https://github.com/bvvy/java-system-tray")
                }

                // 问题跟踪
                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/bvvy/java-system-tray/issues")
                }
            }
        }
    }
    repositories {
        maven {
            url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

jreleaser {
    signing {
        active.set(Active.ALWAYS)
        armored = true
        mode.set(Signing.Mode.FILE)
        publicKey = "C:/Users/bvvy/public.pgp"
        secretKey = "C:/Users/bvvy/private.pgp"
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active.set(Active.ALWAYS)
                    url.set("https://central.sonatype.com/api/v1/publisher")
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }
}



tasks.test {
    useJUnitPlatform()
}