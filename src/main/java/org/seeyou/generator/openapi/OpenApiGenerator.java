package org.seeyou.generator.openapi;

import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;

public class OpenApiGenerator {

	private String openApiModelFile;
	private String outputFolder = "target/out";
	private String packageName = "org.seeyou.demo";
	private String projectName = "seeyou-demo";

	public OpenApiGenerator(String openApiModelFile) {
		this.openApiModelFile = openApiModelFile;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void run() {
		genJavaProject("server", "spring");
		genJavaProject("client", "java");
		genAxiosProject("axios");
		genDocsProject("docs");
	}

	private void genJavaProject(String moduleName, String generatorName) {
		CodegenConfigurator configurator = new CodegenConfigurator();

		configurator.setInputSpec(openApiModelFile);
		configurator.setOutputDir(outputFolder + "/" + projectName + "/" + projectName + "-" + moduleName);
		configurator.setGeneratorName(generatorName);

		configurator.setGroupId(packageName);
		configurator.setInvokerPackage(packageName);
		configurator.setModelPackage(packageName + ".model");
		configurator.setApiPackage(packageName + ".api");

		final ClientOptInput clientOptInput = configurator.toClientOptInput();
		new DefaultGenerator().opts(clientOptInput).generate();
	}

	private void genAxiosProject(String moduleName) {
		CodegenConfigurator configurator = new CodegenConfigurator();

		configurator.setInputSpec(openApiModelFile);
		configurator.setOutputDir(outputFolder + "/" + projectName + "/" + projectName + "-" + moduleName);
		configurator.setGeneratorName("typescript-axios");

		configurator.setGroupId(packageName);
		configurator.addDynamicProperty("npmName", projectName + "-" + moduleName);
		configurator.addDynamicProperty("npmVersion", "0.1.0");

		final ClientOptInput clientOptInput = configurator.toClientOptInput();
		new DefaultGenerator().opts(clientOptInput).generate();
	}

	private void genDocsProject(String moduleName) {
		CodegenConfigurator configurator = new CodegenConfigurator();

		configurator.setInputSpec(openApiModelFile);
		configurator.setOutputDir(outputFolder + "/" + projectName + "/" + projectName + "-" + moduleName);

		configurator.setGeneratorName("html2");
		configurator.setTemplateDir("src/main/resources/org/seeyou/generator/openapi/htmlDocs2");

		configurator.setGroupId(packageName);
		configurator.setInvokerPackage(packageName);
		configurator.setModelPackage(packageName + ".model");
		configurator.setApiPackage(packageName + ".api");

		final ClientOptInput clientOptInput = configurator.toClientOptInput();
		new DefaultGenerator().opts(clientOptInput).generate();
	}

}
