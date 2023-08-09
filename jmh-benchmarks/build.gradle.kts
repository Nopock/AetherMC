plugins {
    id("me.champeau.jmh") version ("0.7.1")
}

dependencies {
    jmh("org.openjdk.jmh:jmh-core:0.9")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:0.9")
    implementation(rootProject)
}