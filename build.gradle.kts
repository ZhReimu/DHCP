import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.administrator"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/repository/public/")
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.0")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.17.0")
    // https://mvnrepository.com/artifact/org.pcap4j/pcap4j-core
    implementation("org.pcap4j:pcap4j-core:2.0.0-alpha.6")
    // https://mvnrepository.com/artifact/org.pcap4j/pcap4j-packetfactory-static
    implementation("org.pcap4j:pcap4j-packetfactory-static:2.0.0-alpha.6")
    //  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}