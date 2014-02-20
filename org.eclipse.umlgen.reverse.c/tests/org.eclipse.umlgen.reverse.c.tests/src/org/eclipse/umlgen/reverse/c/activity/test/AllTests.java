package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { TestSimple.class, TestIfElseIf.class,
		TestForLoop.class, TestDoWhileLoop.class, TestWhileLoop.class,
		TestSwitchConditional.class, TestBreakInLoop.class,
		TestSimpleForAndIfNested.class, TestReturn.class, TestContinue.class,
		TestExistingCode.class, TestSpecialChars.class, TestLabel.class,
		TestComments.class })
public class AllTests {
}
