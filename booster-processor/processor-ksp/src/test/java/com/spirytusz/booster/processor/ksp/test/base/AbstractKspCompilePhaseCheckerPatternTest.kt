package com.spirytusz.booster.processor.ksp.test.base

import com.tschuchort.compiletesting.JvmCompilationResult
import com.tschuchort.compiletesting.KotlinCompilation
import java.util.regex.Pattern

abstract class AbstractKspCompilePhaseCheckerPatternTest : AbstractKspCompilePhaseCheckerTest() {

    abstract val pattern: Pattern

    open val exitCode = KotlinCompilation.ExitCode.COMPILATION_ERROR

    override fun checkResult(result: JvmCompilationResult) {
        assert(result.exitCode == exitCode)

        assert(pattern.matcher(result.messages).find())
    }
}