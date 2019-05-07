package org.seeyou.generator;

import org.junit.Test;

public class AllGeneratorTest {

	@Test
	public void testGenerator() {
		AllGenerator.yaml(Thread.currentThread().getContextClassLoader().getResourceAsStream("project.yaml"))
				.generator("target/out");
	}

}
