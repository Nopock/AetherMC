plugins {
    `java-library`
    alias(libs.plugins.blossom)

    `maven-publish`
    signing
    alias(libs.plugins.nexuspublish)
}

version = System.getenv("SHORT_COMMIT_HASH") ?: "dev"

allprojects {
    apply(plugin = "java")

    group = "net.minestom"
    version = rootProject.version
    description = "Lightweight and multi-threaded Minecraft server implementation"

    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    configurations.all {
        // We only use Jetbrains Annotations
        exclude("org.checkerframework", "checker-qual")
    }

    dependencies {
        implementation("de.articdive:jnoise-pipeline:4.0.0")
    }

    java {
        withSourcesJar()
        withJavadocJar()

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<Zip> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.withType<Test> {
        useJUnitPlatform()

        // Viewable packets make tracking harder. Could be re-enabled later.
        jvmArgs("-Dminestom.viewable-packet=false")
        jvmArgs("-Dminestom.inside-test=true")
    }
}

sourceSets {
    main {
        java.srcDir(file("src/main/java"))
        java.srcDir(file("src/autogenerated/java"))
    }
}

dependencies {
    // Core dependencies
    api(libs.slf4j)
    api(libs.jetbrainsAnnotations)
    api(libs.bundles.adventure)
    api(libs.hydrazine)
    api(libs.bundles.kotlin)
    api(libs.bundles.hephaistos)
    implementation(libs.minestomData)

    // Performance/data structures
    implementation(libs.caffeine)
    api(libs.fastutil)
    implementation(libs.bundles.flare)
    api(libs.gson)
    implementation(libs.jcTools)

    // Testing
    testImplementation(libs.bundles.junit)
    testImplementation(project(":testing"))
}

tasks {
    withType<Javadoc> {
        (options as? StandardJavadocDocletOptions)?.apply {
            encoding = "UTF-8"

            // Custom options
            addBooleanOption("html5", true)
            addStringOption("-release", "17")
            // Links to external javadocs
            links("https://docs.oracle.com/en/java/javase/17/docs/api/")
            links("https://jd.adventure.kyori.net/api/${libs.versions.adventure.get()}/")
        }
    }

    blossom {
        val gitFile = "src/main/java/net/minestom/server/Git.java"

        val gitCommit = System.getenv("GIT_COMMIT")
        val gitBranch = System.getenv("GIT_BRANCH")
        val group = System.getenv("GROUP")
        val artifact = System.getenv("ARTIFACT")

        replaceToken("\"&COMMIT\"", if (gitCommit == null) "null" else "\"${gitCommit}\"", gitFile)
        replaceToken("\"&BRANCH\"", if (gitBranch == null) "null" else "\"${gitBranch}\"", gitFile)
        replaceToken("\"&GROUP\"", if (group == null) "null" else "\"${group}\"", gitFile)
        replaceToken("\"&ARTIFACT\"", if (artifact == null) "null" else "\"${artifact}\"", gitFile)
    }

    nexusPublishing{
        useStaging.set(true)
        this.packageGroup.set("dev.hollowcube")

        repositories.sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            if (System.getenv("SONATYPE_USERNAME") != null) {
                username.set(System.getenv("SONATYPE_USERNAME"))
                password.set(System.getenv("SONATYPE_PASSWORD"))
            }
        }
    }

    publishing.publications.create<MavenPublication>("maven") {
        groupId = "me.outspending"
        artifactId = "AetherMC"
        version = project.version.toString()

        from(project.components["java"])

        pom {
            name.set("AetherMC")
            description.set("Lightweight and multi-threaded 1.20.1 Minecraft server")
            url.set("https://github.com/hollow-cube/minestom-ce")

            licenses {
                license {
                    name.set("Apache 2.0")
                    url.set("https://github.com/hollow-cube/minestom-ce/blob/main/LICENSE")
                }
            }

            developers {
                developer {
                    id.set("TheMode")
                }
                developer {
                    id.set("mworzala")
                    name.set("Matt Worzala")
                    email.set("matt@hollowcube.dev")
                }
            }

            issueManagement {
                system.set("GitHub")
                url.set("https://github.com/hollow-cube/minestom-ce/issues")
            }

            scm {
                connection.set("scm:git:git://github.com/hollow-cube/minestom-ce.git")
                developerConnection.set("scm:git:git@github.com:hollow-cube/minestom-ce.git")
                url.set("https://github.com/hollow-cube/minestom-ce")
                tag.set("HEAD")
            }

            ciManagement {
                system.set("Github Actions")
                url.set("https://github.com/hollow-cube/minestom-ce/actions")
            }
        }
    }

    signing {
        isRequired = System.getenv("CI") != null

        val privateKey = System.getenv("GPG_PRIVATE_KEY")
        val keyPassphrase = System.getenv()["GPG_PASSPHRASE"]
        useInMemoryPgpKeys(privateKey, keyPassphrase)

        sign(publishing.publications)
    }
}
