/**
 * Copyright 2022 SPeCS.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. under the License.
 */

package pt.up.fe.comp;

import org.junit.Test;

public class TutorialTest {


    private static final String EXPRESSION = "expression";

    private static void noErrors(String code, String grammarRule) {
        if (grammarRule.isEmpty()) {
            System.out.println(
                    "Name of grammar rule is empty, please define it in the static field at the beginning of the class '"
                            + TutorialTest.class.getName() + "' if test is to be executed");
            return;
        }

        var result = TestUtils.parse(code, grammarRule);
        TestUtils.noErrors(result.getReports());

        System.out.println("Code: " + code + "\n");
        System.out.println("AST:\n\n" + result.getRootNode().toTree());
        System.out.println("\n---------\n");
    }

    private static void noErrors(String code) {
        noErrors(code, "Program");
    }

    @Test
    public void testExprMult() {
        noErrors("2 * 3", EXPRESSION);
    }

    @Test
    public void testExprDiv() {
        noErrors("2 / 3", EXPRESSION);
    }

    @Test
    public void testExprMultChain() {
        noErrors("1 * 2 / 3 * 4", EXPRESSION);
    }

    @Test
    public void testExprAdd() {
        noErrors("2 + 3", EXPRESSION);
    }

    @Test
    public void testExprSub() {
        noErrors("2 - 3", EXPRESSION);
    }

    @Test
    public void testExprAddChain() {
        noErrors("1 + 2 - 3 + 4", EXPRESSION);
    }


}
