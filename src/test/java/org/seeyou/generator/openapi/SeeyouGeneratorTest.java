package org.seeyou.generator.openapi;

import org.junit.Test;
import org.seeyou.generator.ProjectGenerator;

/***
 * This test allows you to easily launch your code generation software under a
 * debugger. Then run this test under debug mode. You will be able to step
 * through your java code and then see the results in the out directory.
 *
 * To experiment with debugging your code generator: 1) Set a break point in
 * SeeyouGenerator.java in the postProcessOperationsWithModels() method. 2) To
 * launch this test in Eclipse: right-click | Debug As | JUnit Test
 *
 */
public class SeeyouGeneratorTest {

//	@Test
//	public void launchCodeGeneratorInDebugMode() {
//		// use this test to launch you code generator in the debugger.
//		// this allows you to easily set break points in SeeyouGenerator.
//		String commandLineParams = "generate " + "-i ./src/test/resources/petstore.yaml " + // sample swagger
//				"-t ./src/main/resources/seeyou " + // template directory
//				"-o target/out/seeyou " + // output directory
//				"-g seeyou "; // use this codegen library
//
//		try {
//			OpenAPIGenerator.main(commandLineParams.split(" "));
//		} catch (Exception ex) {
//			System.err.println(ex.toString());
//		} catch (Error er) {
//			System.err.println(er.toString());
//		}
//	}

	/**
	 * 完成整个项目集的代码生成
	 */
	@Test
	public void testProjectGenerator() {
		new ProjectGenerator("src/test/resources/openapi-petstore.yaml").run();
	}

}
