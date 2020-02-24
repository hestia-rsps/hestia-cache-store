import java.util.Date
import com.jfrog.bintray.gradle.BintrayExtension.*
import org.gradle.api.publish.maven.MavenPom

plugins {
    kotlin("jvm") version "1.3.61"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
    maven
    idea
}

group = "world.gregs.hestia"
version = "0.0.4"

val bintrayUser: String? by project
val bintrayKey: String? by project
val versionName = version.toString()
val artifactName = "hestia-cache-store"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.displee:rs-cache-library:6.0")

    //Logging
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    //Testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testImplementation("org.assertj:assertj-core:3.14.0")
    testImplementation("io.mockk:mockk:1.9.3")
}


val sourcesJar by tasks.creating(Jar::class) {
    from(sourceSets.getByName("main").java.srcDirs)
    archiveClassifier.set("sources")
}

artifacts {
    archives(sourcesJar)
}

fun MavenPom.addDependencies() = withXml {
    asNode().appendNode("dependencies").let { depNode ->
        configurations.compile.get().allDependencies.forEach {
            depNode.appendNode("dependency").apply {
                appendNode("groupId", it.group)
                appendNode("artifactId", it.name)
                appendNode("version", it.version)
            }
        }
    }
}

publishing {
    publications {
        create("production", MavenPublication::class) {
            artifact("$buildDir/outputs/aar/app-release.aar")
            artifactId = artifactName
            version = versionName
            pom.addDependencies()
        }
    }
}

bintray {
    user = bintrayUser
    key = bintrayKey
    setConfigurations("archives")
    publish = true
    pkg(delegateClosureOf<PackageConfig> {
        repo = "Hestia"
        name = artifactName
        desc = "Library for reading RS2 cache files"
        userOrg = "hestia-rsps"
        vcsUrl = "https://github.com/hestia-rsps/hestia-cache-store.git"
        setLabels("kotlin")
        setLicenses("BSD 3-Clause")
        publicDownloadNumbers = true
        version(delegateClosureOf<VersionConfig> {
            name = versionName
            released = Date().toString()
            vcsTag = "v$versionName"
        })
    })
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}