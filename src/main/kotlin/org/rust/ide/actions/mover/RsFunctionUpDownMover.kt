/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.rust.ide.actions.mover

import com.intellij.psi.PsiElement
import org.rust.lang.core.psi.RsElementTypes.*
import org.rust.lang.core.psi.RsFunction
import org.rust.lang.core.psi.ext.ancestorStrict

class RsFunctionUpDownMover : RsStatementUpDownMover() {
    override fun collectedElement(element: PsiElement): Pair<PsiElement, List<Int>>? {
        val collectedElement = element.ancestorStrict<RsFunction>() ?: return null
        return collectedElement to listOf(collectedElement.line, collectedElement.fn.line).mapNotNull { it }
    }
    override val containers = listOf(
        FUNCTION,
        MOD_ITEM,
        TRAIT_ITEM,
        IMPL_ITEM
    )
}
