import io.qameta.allure.gradle.AllureExtension

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("io.qameta.allure:allure-gradle:2.6.0")
    }
}

tasks.existing(Wrapper::class) {
    gradleVersion = "4.10.2"
    distributionType = Wrapper.DistributionType.ALL
}

group = "io.eroshenkoam.allure"
version = version

plugins {
    java
    maven
}

apply(plugin = "io.qameta.allure")

configure<AllureExtension> {
    autoconfigure = true
    aspectjweaver = true
    version = "2.12.1"

    useTestNG {
        version = "2.12.1"
    }

}

tasks.withType(JavaCompile::class) {
    sourceCompatibility = "${JavaVersion.VERSION_1_8}"
    targetCompatibility = "${JavaVersion.VERSION_1_8}"
    options.encoding = "UTF-8"
}

tasks.withType(Test::class) {
    ignoreFailures = true
    useTestNG() {}
}


repositories {
    maven(url = "https://dl.bintray.com/qameta/maven-unstable/")
    mavenCentral()
    mavenLocal()
}

dependencies {
    compile("commons-io:commons-io:2.6")
    compile("io.qameta.allure:allure-java-commons:2.12.1")

    testCompile("org.testng:testng:6.14.3")
    testCompile("io.qameta.allure:allure-ee-testng:3.18.0")
}
