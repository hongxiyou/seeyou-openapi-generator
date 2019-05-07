package org.seeyou.generator.openapi;

import java.io.File;

import org.openapitools.codegen.CodegenType;
import org.openapitools.codegen.DefaultCodegen;
import org.openapitools.codegen.SupportingFile;

/**
 * 项目结构生成器
 */
public class ProjectsGenerator extends DefaultCodegen {

	/**
	 * Configures the type of generator.
	 * 
	 * @return the CodegenType for this generator
	 * @see org.openapitools.codegen.CodegenType
	 */
	public CodegenType getTag() {
		return CodegenType.OTHER;
	}

	/**
	 * Configures a friendly name for the generator. This will be used by the
	 * generator to select the library with the -g flag.
	 * 
	 * @return the friendly name for the generator
	 */
	public String getName() {
		return "seeyou-projects";
	}

	/**
	 * Returns human-friendly help for the generator. Provide the consumer with help
	 * tips, parameters here
	 * 
	 * @return A string value for the help message
	 */
	public String getHelp() {
		return "Generates a SeeYou Projects.";
	}

	public ProjectsGenerator() {
		super();

		/**
		 * Template Location. This is the location which templates will be read from.
		 * The generator will use the resource stream to attempt to read the templates.
		 */
		templateDir = "seeyou-projects";

		/**
		 * Supporting Files. You can write single files for the generator with the
		 * entire object tree available. If the input file has a suffix of `.mustache it
		 * will be processed by the template engine. Otherwise, it will be copied
		 */
		supportingFiles.add(new SupportingFile("pom.mustache", // the input template or file
				"", // the destination folder, relative `outputFolder`
				"pom.xml") // the output file
		);
		supportingFiles.add(new SupportingFile("gitignore.txt", "", ".gitignore"));
	}

	/**
	 * Escapes a reserved word as defined in the `reservedWords` array. Handle
	 * escaping those terms here. This logic is only called if a variable matches
	 * the reserved words
	 * 
	 * @return the escaped term
	 */
	@Override
	public String escapeReservedWord(String name) {
		return "_" + name; // add an underscore to the name
	}

	/**
	 * Location to write model files. You can use the modelPackage() as defined when
	 * the class is instantiated
	 */
	public String modelFileFolder() {
		return outputFolder + "/" + modelPackage().replace('.', File.separatorChar);
	}

	/**
	 * Location to write api files. You can use the apiPackage() as defined when the
	 * class is instantiated
	 */
	@Override
	public String apiFileFolder() {
		return outputFolder + "/" + apiPackage().replace('.', File.separatorChar);
	}

	/**
	 * override with any special text escaping logic to handle unsafe characters so
	 * as to avoid code injection
	 *
	 * @param input String to be cleaned up
	 * @return string with unsafe characters removed or escaped
	 */
	@Override
	public String escapeUnsafeCharacters(String input) {
		// injection
		return input;
	}

	/**
	 * Escape single and/or double quote to avoid code injection
	 *
	 * @param input String to be cleaned up
	 * @return string with quotation mark removed or escaped
	 */
	public String escapeQuotationMark(String input) {
		// injection
		return input.replace("\"", "\\\"");
	}
}