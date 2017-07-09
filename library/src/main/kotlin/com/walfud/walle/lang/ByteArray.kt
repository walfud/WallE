package com.walfud.walle.lang

import java.security.MessageDigest

/**
 * Created by walfud on 2017/7/9.
 */

fun ByteArray.string() = map { it.string() }.joinToString("")

fun ByteArray.md5() = MessageDigest.getInstance("MD5").run { digest(this@md5).string() }