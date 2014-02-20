package org.eclipse.umlgen.gen.c.structural.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value={
		TestStorageClasses.class,
		TestTypeDef.class,
		TestStruct.class,
		TestOperation.class,
		TestIncludeGuard.class,
		TestIncludes.class,
		TestEnum.class,
		TestComment.class,
		TestDefine.class,
		TestCUnit.class,
})
public class AllTests {

}
