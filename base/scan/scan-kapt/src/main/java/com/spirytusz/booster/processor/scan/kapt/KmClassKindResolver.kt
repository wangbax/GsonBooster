package com.spirytusz.booster.processor.scan.kapt

import com.spirytusz.booster.processor.base.data.BoosterClassKind
import com.spirytusz.booster.processor.base.log.MessageLogger
import kotlin.metadata.ClassKind
import kotlin.metadata.kind
import javax.lang.model.element.TypeElement

class KmClassKindResolver(
    private val belongingClass: TypeElement,
    private val kmClassCacheHolder: KmClassCacheHolder,
    private val logger: MessageLogger
) {

    private val kmClass by lazy {
        kmClassCacheHolder.get(belongingClass)
    }

    fun resolveClassKind(): BoosterClassKind {
        return when (kmClass.kind) {
            ClassKind.CLASS -> BoosterClassKind.CLASS
            ClassKind.ANNOTATION_CLASS -> BoosterClassKind.ANNOTATION
            ClassKind.ENUM_ENTRY -> BoosterClassKind.ENUM_ENTRY
            ClassKind.ENUM_CLASS -> BoosterClassKind.ENUM_CLASS
            ClassKind.INTERFACE -> BoosterClassKind.INTERFACE
            ClassKind.OBJECT, ClassKind.COMPANION_OBJECT -> BoosterClassKind.OBJECT
            else -> {
                logger.error("unexpected class kind on class $belongingClass", belongingClass)
                throw IllegalArgumentException("unexpected class kind on class $belongingClass")
            }
        }
    }
}