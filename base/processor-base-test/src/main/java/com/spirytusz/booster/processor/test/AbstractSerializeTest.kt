package com.spirytusz.booster.processor.test

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.TypeAdapterFactory
import com.spirytusz.booster.processor.test.extensions.fromResource
import com.spirytusz.booster.processor.test.extensions.getClassByName
import com.spirytusz.booster.processor.test.extensions.toJsonObject
import com.tschuchort.compiletesting.JvmCompilationResult
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import org.junit.Test

abstract class AbstractSerializeTest {

    abstract val sourceCodePath: String

    abstract val beanClassName: String

    abstract val typeAdapterFactoryClassName: String

    abstract val jsonFilePath: String

    protected val json by lazy { this::class.java.getResource(jsonFilePath).readText() }

    abstract fun compile(sources: List<SourceFile>): JvmCompilationResult

    abstract fun getExpectJsonObject(result: JvmCompilationResult): JsonObject

    @Test
    fun test() {
        val source = SourceFile.fromResource(sourceCodePath)
        val sources = listOf(source)
        val result = compile(sources)

        assert(result.exitCode == KotlinCompilation.ExitCode.OK)

        val beanClass = result.getClassByName(beanClassName)

        testSerialize(result, beanClass)
        testDeserialize(result, beanClass)
    }

    private fun testSerialize(result: JvmCompilationResult, beanClass: Class<*>) {
        printLog("start testSerialize >>>")
        val boosterResult = makeBooster(result).fromJson(json, beanClass)

        val expectJsonObject = getExpectJsonObject(result)
        printLog("serialize expect: $expectJsonObject")
        printLog("serialize actual: ${boosterResult.toJsonObject()}")

        assert(boosterResult.toJsonObject() == expectJsonObject)
        printLog("end testSerialize <<<")
    }

    private fun testDeserialize(result: JvmCompilationResult, beanClass: Class<*>) {
        printLog("start testDeserialize >>>")
        val bean = Gson().fromJson(getExpectJsonObject(result).toString(), beanClass)

        val boosterResult =
            Gson().fromJson(makeBooster(result).toJson(bean), JsonObject::class.java)
        val expectJsonObject = getExpectJsonObject(result)
        printLog("deserialize expect: $expectJsonObject")
        printLog("deserialize actual: ${boosterResult.toJsonObject()}")

        assert(boosterResult.toJsonObject() == expectJsonObject)
        printLog("end testDeserialize <<<")
    }

    private fun makeBooster(result: JvmCompilationResult): Gson {
        val typeAdapterFactory = result.getClassByName(typeAdapterFactoryClassName).newInstance()
                as TypeAdapterFactory
        return GsonBuilder()
            .registerTypeAdapterFactory(typeAdapterFactory)
            .create()
    }

    protected fun printLog(msg: String) {
        println("[BoosterTest] $msg")
    }
}