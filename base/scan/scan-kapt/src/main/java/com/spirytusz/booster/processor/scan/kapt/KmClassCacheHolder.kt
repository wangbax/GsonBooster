package com.spirytusz.booster.processor.scan.kapt

import com.spirytusz.booster.processor.base.log.MessageLogger
import kotlin.metadata.KmClass
import kotlin.metadata.jvm.KotlinClassMetadata
import javax.lang.model.element.TypeElement

class KmClassCacheHolder(private val logger: MessageLogger) {

    private val cache = mutableMapOf<String, KmClass>()

    fun get(typeElement: TypeElement): KmClass {
        val fqName = typeElement.qualifiedName.toString()
        return cache.getOrPut(fqName) {
            val kmClass = typeElement.asKmClass()
                ?: throw IllegalStateException("Unexpected metadata received for element $fqName")
            kmClass
        }
    }

    private fun TypeElement.asKmClass(): KmClass? {
        val metadataAnnotation = getAnnotation(Metadata::class.java) ?: run {
            logger.error("@Metadata annotation not found", this)
            return null
        }
        val kotlinClassMetadata = try {
            KotlinClassMetadata.readLenient(metadataAnnotation)
        } catch (e: Exception) {
            logger.error("Failed to read KotlinClassMetadata: ${e.message}", this)
            return null
        }
        kotlinClassMetadata as? KotlinClassMetadata.Class ?: run {
            logger.error("parse result is NOT KotlinClassMetadata.Class", this)
            return null
        }
        return kotlinClassMetadata.kmClass
    }
}