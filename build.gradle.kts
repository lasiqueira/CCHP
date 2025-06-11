import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.0"
    id("me.champeau.jmh") version "0.7.1"
    application
}
group = "com.lucas.cchp"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.openjdk.jmh:jmh-core:1.37")
    testAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.37")

    jmh ("org.openjdk.jmh:jmh-core:1.37")
    jmh ("org.openjdk.jmh:jmh-generator-annprocess:1.37")


}

tasks.test {
    useJUnit()
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("--add-modules", "jdk.incubator.vector")
}

tasks.withType<Test>().configureEach {
    jvmArgs("--add-modules", "jdk.incubator.vector")
}
kotlin {
    jvmToolchain(21)
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "21"
}

application {
    mainClass.set("MainKt")
}