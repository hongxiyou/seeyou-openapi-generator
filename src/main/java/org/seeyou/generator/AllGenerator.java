package org.seeyou.generator;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.ClientOpts;
import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.CodegenConfigLoader;
import org.openapitools.codegen.DefaultGenerator;
import org.seeyou.generator.yaml.YamlLoader;

import com.github.jknack.handlebars.internal.lang3.StringUtils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

public class AllGenerator {

	public final static OpenAPI EMPTY_OPENAPI = createEmptyOpenAPI();

	public static OpenAPI createEmptyOpenAPI() {
		OpenAPI openAPI = new OpenAPI();
		openAPI.setComponents(new Components());

		final Info info = new Info();
		info.setDescription("Empty OpenAPI");
		info.setVersion("0.1.0");
		info.setTitle("My title");
		openAPI.setInfo(info);

		final Server server = new Server();
		server.setUrl("https://localhost/");
		openAPI.setServers(Collections.singletonList(server));

		openAPI.setPaths(new Paths());

		return openAPI;
	}

	public static AllGenerator yaml(InputStream yaml) {
		return new AllGenerator(YamlLoader.load(yaml));
	}

	private Map<String, Object> options;

	public AllGenerator(Map<String, Object> options) {
		this.options = options;
	}

	private void createParentProject(String outputDir) {
		final String parentArtifactId = (String) options.get("parent.artifactId");
		Validate.notBlank(parentArtifactId, "parent.artifactId must be specified");

		if (StringUtils.isBlank(outputDir)) {
			outputDir = ".";
		}

		CodegenConfig config = CodegenConfigLoader.forName("seeyou-projects");
		config.setOutputDir(outputDir + "/" + parentArtifactId);
		config.additionalProperties().putAll(options);

		ClientOptInput opts = new ClientOptInput();
		opts.setOpenAPI(EMPTY_OPENAPI);
		opts.setConfig(config);
		opts.setOpts(new ClientOpts());
		new DefaultGenerator().opts(opts).generate();
	}

	private void createModuleProject(String moduleName, String outputDir) {

	}

	public void generator(String outputDir) {

		createParentProject(outputDir);

		String[] modules = (String[]) options.get("parent.modules");
		if (modules != null && modules.length > 0) {
			for (String module : modules) {
				createModuleProject(module, outputDir + "/" + module);
			}
		}
	}

}
