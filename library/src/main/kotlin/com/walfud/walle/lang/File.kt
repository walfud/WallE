package com.walfud.walle.lang

import java.io.File

/**
 * Created by walfud on 2017/7/9.
 */

fun File.md5() = readBytes().md5()
fun File.sha1() = readBytes().sha1()
fun File.sha256() = readBytes().sha256()
fun File.sha512() = readBytes().sha512()