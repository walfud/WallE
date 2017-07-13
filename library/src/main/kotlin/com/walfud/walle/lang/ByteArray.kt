package com.walfud.walle.lang

import android.util.Base64
import java.security.MessageDigest

/**
 * Created by walfud on 2017/7/9.
 */

fun ByteArray.string() = map { it.string() }.joinToString("")

fun ByteArray.base64() = String(Base64.encode(this, Base64.DEFAULT))

fun ByteArray.md5() = MessageDigest.getInstance("MD5").run { digest(this@md5).string() }
fun ByteArray.sha1() = MessageDigest.getInstance("SHA-1").run { digest(this@sha1).string() }
fun ByteArray.sha256() = MessageDigest.getInstance("SHA-256").run { digest(this@sha256).string() }
fun ByteArray.sha512() = MessageDigest.getInstance("SHA-512").run { digest(this@sha512).string() }