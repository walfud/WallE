import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.5"
}

android {
    compileSdkVersion(rootProject.ext["compileSdkVersion"] as Int)

    defaultConfig {
        minSdkVersion(rootProject.ext["minSdkVersion"] as Int)
        targetSdkVersion(rootProject.ext["targetSdkVersion"] as Int)
        versionCode = rootProject.ext["versionCode"] as Int
        versionName = rootProject.ext["versionName"] as String

        consumerProguardFiles("proguard-rules.pro")
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    api("com.google.code.gson:gson:2.8.6")

    api("com.squareup.okhttp3:okhttp:4.7.2")
    api("com.squareup.retrofit2:retrofit:2.8.1")
    api("com.squareup.retrofit2:converter-gson:2.8.1")
}


val androidSourcesJar = tasks.create<Jar>("androidSourcesJar") {
    archiveClassifier.set("sources")
    from(android.sourceSets.map { it.java.srcDirs })
}

val androidJavadoc = tasks.create<Javadoc>("androidJavadoc") {
    source(android.sourceSets.map { it.java.srcDirs })
    classpath += files(android.bootClasspath)

    options {
        this as StandardJavadocDocletOptions
        encoding = "utf-8"
        charSet = "utf-8"
    }
    isFailOnError = false
}
val androidJavadocJar = tasks.create<Jar>("androidJavadocJar") {
    archiveClassifier.set("javadoc")
    from(androidJavadoc)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("aarToMaven") {
                groupId = "com.walfud"
                artifactId = "walle"
                version = rootProject.ext["versionName"] as String

                artifact(tasks.getByName("bundleReleaseAar"))
                artifact(androidSourcesJar)
                artifact(androidJavadocJar)

                pom.withXml {
                    fun addDependency(depsNode: groovy.util.Node, dep: Dependency, scope: String) {
                        if (dep !is ExternalModuleDependency) {
                            return
                        }

                        if (dep.group == null || dep.name == null || dep.version == null || dep.version == "unspecified") {
                            return
                        }

                        val dependencyNode = depsNode.appendNode("dependency")
                        dependencyNode.appendNode("groupId", dep.group)
                        dependencyNode.appendNode("artifactId", dep.name)
                        dependencyNode.appendNode("version", dep.version)
                        dependencyNode.appendNode("scope", scope)

                        if (!dep.isTransitive) {
                            // If this dependency is not transitive, we should force exclude all its dependencies from the POM
                            val exclusionNode = dependencyNode.appendNode("exclusions").appendNode("exclusion")
                            exclusionNode.appendNode("groupId", "*")
                            exclusionNode.appendNode("artifactId", "*")
                        } else if (dep.excludeRules.isNotEmpty()) {
                            // Otherwise add specified exclude rules
                            val exclusionsNode = dependencyNode.appendNode("exclusions")
                            dep.excludeRules.forEach { rule ->
                                val exclusionNode = exclusionsNode.appendNode("exclusion")
                                exclusionNode.appendNode("groupId", rule.group)
                                exclusionNode.appendNode("artifactId", rule.module)
                            }
                        }
                    }

                    val dependenciesNode = asNode().appendNode("dependencies")
                    configurations.compile.get().dependencies.forEach { dep -> addDependency(dependenciesNode, dep, "compile") }
                    configurations.api.get().dependencies.forEach { dep -> addDependency(dependenciesNode, dep, "compile") }
                    configurations.implementation.get().dependencies.forEach { dep -> addDependency(dependenciesNode, dep, "runtime") }
                }
            }
        }

        repositories {
            mavenLocal()
        }
    }
}
if (rootProject.ext.has("JCENTER_USER") && rootProject.ext.has("JCENTER_KEY")) {
    bintray {
        user = rootProject.ext["JCENTER_USER"] as String
        key = rootProject.ext["JCENTER_KEY"] as String
        setPublications("aarToMaven")

        pkg.apply {
            repo = "maven"
            name = "walle"
            version.apply {
                name = rootProject.ext["versionName"] as String
            }
        }
    }
}