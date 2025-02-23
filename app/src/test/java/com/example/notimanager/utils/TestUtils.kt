package com.example.notimanager.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

object TestUtils {
    private val objectMapper = jacksonObjectMapper()

    fun readJsonFile(dirPath: String, fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream("json/$dirPath/$fileName")
            ?: throw IllegalArgumentException("File not found: $fileName")

        return BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
    }

    infix fun <T> String.toDto(dto: Class<T>): T {
        return objectMapper.readValue(this, dto)
    }

    infix fun <T> String.toDtoList(dto: Class<T>): List<T> {
        return objectMapper.readValue(this, object : TypeReference<List<T>>() {
            override fun getType(): Type {
                return objectMapper.typeFactory.constructCollectionType(List::class.java, dto)
            }
        })
    }
}