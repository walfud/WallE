package com.walfud.walle.lang

import android.util.Base64

/**
 * Created by walfud on 2017/7/9.
 */

fun String.fromBase64() = Base64.decode(this, Base64.DEFAULT)

fun String.md5() = toByteArray().md5()
fun String.sha1() = toByteArray().sha1()
fun String.sha256() = toByteArray().sha256()
fun String.sha512() = toByteArray().sha512()