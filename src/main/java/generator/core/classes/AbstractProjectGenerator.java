package generator.core.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import generator.config.BasePath;
import generator.core.AbstractGenerator;

public abstract class AbstractProjectGenerator extends AbstractGenerator {
	
	protected Path templateBase;
	
	protected AbstractProjectGenerator() {
		this.templateBase = BasePath.TEST_RESOURCE.resolve(Paths.get("generator", "template"));
	}

	protected void generate() {
		this.baseGenerate();
		Path templatePath = getTemplateBasepath();
		Path targetBasePath = getTargetBasepath();
		try {
			Files.walk(templatePath).forEach(p -> {
				Path relatePath = templatePath.relativize(p);
				try {
					Path target = targetBasePath.resolve(relatePath);
					if(Files.isDirectory(p)) {
						if(!Files.isDirectory(target)) {
							Files.createDirectories(target);
						}
					} else {
						fileGenerate(p, target);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			this.success();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void fileGenerate(Path fromPath, Path toPath) {
		super.generator(fromPath, toPath, getConfig());
	}
	
	/**
	 * return the local template path
	 * @return the local template path
	 */
	protected abstract Path getTemplateBasepath();
	
	/**
	 * return the target base path begin from project base path
	 * @return
	 */
	protected abstract Path getTargetBasepath();
	
	/**
	 * return the project base path
	 * @return the project base path
	 */
	protected abstract Path getProjectPath();
	
	/**
	 * 
	 * @return the configuration to render template
	 */
	protected abstract Object getConfig();
	
	/**
	 * the hook
	 */
	protected abstract void success();

	protected void baseGenerate() {
		Path projectPath = getProjectPath();
		Path targetBasepath = getTargetBasepath();
		try {
			if(!Files.isDirectory(targetBasepath)) {
				Files.createDirectories(targetBasepath);
			}
			if(!Files.isDirectory(projectPath.resolve(BasePath.RESOURCE_PATH))) {
				Files.createDirectories(projectPath.resolve(BasePath.RESOURCE_PATH));
			}
			if(!Files.isDirectory(projectPath.resolve(BasePath.TEST_PATH))) {
				Files.createDirectories(projectPath.resolve(BasePath.TEST_PATH));
			}
			if(!Files.isDirectory(projectPath.resolve(BasePath.TEST_RESOURCE))) {
				Files.createDirectories(projectPath.resolve(BasePath.TEST_RESOURCE));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
