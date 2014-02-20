package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { TestComplex.class, TestSimple.class,
		TestBreakInLoop.class, TestContinue.class, TestDoWhile.class,
		TestWhile.class, TestExistingCode.class, TestForAndIfNested.class,
		TestFor.class, TestIfElseIf.class, TestLabel.class, TestReturn.class,
		TestSpecialChars.class, TestSwitch.class, TestComments.class })
public class AllTests {
}
