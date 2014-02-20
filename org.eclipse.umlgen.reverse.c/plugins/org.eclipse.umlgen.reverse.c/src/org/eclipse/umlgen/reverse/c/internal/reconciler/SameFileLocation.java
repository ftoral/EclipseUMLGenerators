/**
 * 
 */
package org.eclipse.umlgen.reverse.c.internal.reconciler;

import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;

public final class SameFileLocation implements Predicate<IASTNode>
{
    private final IASTTranslationUnit astTranslationUnit;

    public SameFileLocation(IASTTranslationUnit astTranslationUnit)
    {
        this.astTranslationUnit = astTranslationUnit;
    }

    public boolean apply(IASTNode input)
    {
        if (input != null && astTranslationUnit != null) {
            IASTFileLocation fileLocation = input.getFileLocation();
            String filePath = astTranslationUnit.getFilePath();
            if (fileLocation != null) {
                return Objects.equal(filePath, fileLocation.getFileName());
            }
        }
        
        return false;
    }
}