/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2024 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */
package com.github.javaparser.ast.visitor.equals;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.EqualsVisitor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class EqualsVisitorVariableTest extends EqualsVisitorTest
{
    private static final String VARIABLE = "class a{ void b(){int a=15;}} ";

    @Test
    void equals_sameVariable_true()
    {
        parseAndClone(VARIABLE);
        boolean result = EqualsVisitor.equals(nodeLeft, nodeRight);
        assertThat(result, is(true));
    }

    VariableDeclarator getRightVariable()
    {
        return nodeRight.findFirst(VariableDeclarator.class).get();
    }

    @Test
    void equals_differentInitializer_false()
    {
        parseAndClone(VARIABLE);
        VariableDeclarator variable = getRightVariable();
        variable.setInitializer("16");
        boolean result = EqualsVisitor.equals(nodeLeft, nodeRight);
        assertThat(result, is(false));
    }

    @Test
    void equals_differentName_false()
    {
        parseAndClone(VARIABLE);
        VariableDeclarator variable = getRightVariable();
        variable.setName(variable.getName()+"differentName");
        boolean result = EqualsVisitor.equals(nodeLeft, nodeRight);
        assertThat(result, is(false));
    }

    @Test
    void equals_differentType_false()
    {
        parseAndClone(VARIABLE);
        VariableDeclarator variable = getRightVariable();
        variable.setType("long");
        boolean result = EqualsVisitor.equals(nodeLeft, nodeRight);
        assertThat(result, is(false));
    }
}
