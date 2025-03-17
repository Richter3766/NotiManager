package com.notimanager.notimanager.common.objects

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object Encoder {
    fun getEncodedString(target: String): String {
        return URLEncoder.encode(target, StandardCharsets.UTF_8.toString())
    }

    fun getDecodeString(target: String): String {
        return URLDecoder.decode(target, StandardCharsets.UTF_8.toString())
    }
}