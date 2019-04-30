package org.seeyou.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openapitools.codegen.OpenAPIGenerator;

public class ProjectGenerator {

	private String openApiModelFile;
	private String outputFolder = "target/out";
	private String packageName = "org.seeyou.demo";
	private String projectName = "seeyou-demo";

	public ProjectGenerator(String openApiModelFile) {
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
		CommandLineParams params = new CommandLineParams(moduleName, generatorName);
		params.set("--invoker-package", packageName);
		params.set("--model-package", packageName + ".model");
		params.set("--api-package", packageName + ".api");

		OpenAPIGenerator.main(params.toArgs());
	}

	private void genAxiosProject(String moduleName) {
		CommandLineParams params = new CommandLineParams(moduleName, "typescript-axios");

		OpenAPIGenerator.main(params.toArgs());
	}

	private void genDocsProject(String moduleName) {
		CommandLineParams params = new CommandLineParams(moduleName, "html2");
		params.set("-t", "src/main/resources/seeyou/htmlDocs2");
		params.set("--invoker-package", packageName);
		params.set("--model-package", packageName + ".model");
		params.set("--api-package", packageName + ".api");

		OpenAPIGenerator.main(params.toArgs());
	}

	class CommandLineParams {
		private Map<String, String> params = new HashMap<>();

		CommandLineParams(String moduleName, String generatorName) {
			params.put("-g", generatorName);
			params.put("-i", openApiModelFile);
			params.put("-o", outputFolder + "/" + projectName + "/" + projectName + "-" + moduleName);
		}

		void set(String key, String value) {
			params.put(key, value);
		}

		String[] toArgs() {
			List<String> args = new ArrayList<>();
			args.add("generate");
			params.forEach((key, value) -> {
				args.add(key);
				args.add(value);
			});
			return args.toArray(new String[args.size()]);
		}
	}

}
