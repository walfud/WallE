package com.walfud.walle.lang

import java.io.File

/**
 * Created by walfud on 2017/7/9.
 */

fun File.md5() = readBytes().md5()