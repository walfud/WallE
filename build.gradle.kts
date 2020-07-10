buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
        classpath(kotlin("gradle-plugin", version = "1.3.72"))
    }
}

ext {
    set("kotlinVersion", "1.3.72")
    set("compileSdkVersion", 29)
    set("buildToolsVersion", "29.0.2")
    set("minSdkVersion", 21)
    set("targetSdkVersion", 30)

    set("majorVersion", 3)
    set("minorVersion", 0)
    set("patchVersion", 0)
    set("versionCode", get("majorVersion") as Int * 100 + get("minorVersion") as Int * 10 + get("patchVersion") as Int)
    set("versionName", "${get("majorVersion")}.${get("minorVersion")}.${get("patchVersion")}")
}

try {
    java.io.FileInputStream(File(rootDir, "local.properties")).use { fis ->
        val props = java.util.Properties().also { it.load(fis) }
        props.forEach { (k, v) ->
            (project.extensions.getByName("ext") as ExtraPropertiesExtension).set(k as String, v)
        }
    }
} catch (e: Exception) {
    println(e)
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}