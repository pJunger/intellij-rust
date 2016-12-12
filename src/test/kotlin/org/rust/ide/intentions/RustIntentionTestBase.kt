package org.rust.ide.intentions

import com.intellij.codeInsight.intention.IntentionAction
import org.intellij.lang.annotations.Language
import org.rust.lang.RustTestCaseBase

abstract class RustIntentionTestBase(val intention: IntentionAction) : RustTestCaseBase() {
    final override val dataPath: String get() = ""

    protected fun doAvailableTest(@Language("Rust") before: String, @Language("Rust") after: String) {
        InlineFile(before).withCaret()
        myFixture.launchAction(intention)
        myFixture.checkResult(replaceCaretMarker(after))
    }

    protected fun doUnavailableTest(@Language("Rust") before: String) = doAvailableTest(before, before)
}
