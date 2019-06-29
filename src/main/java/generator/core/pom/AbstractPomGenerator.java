package generator.core.pom;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import generator.core.AbstractGenerator;

public abstract class AbstractPomGenerator extends AbstractGenerator {
	
	public void generate() {
		try {
			createPath(getTargetPath());
			super.generator(getTemplatePath(), getTargetPath(), getConfig());
			this.success();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public abstract Path getTemplatePath();
	
	protected abstract Object getConfig();
	
	protected abstract Path getTargetPath();
	
	protected void createPath(Path path) throws IOException {
		Path parent = path.getParent();
		deleteDirectory(parent.toFile());
		if(!Files.isDirectory(parent)) {
			Files.createDirectories(parent);
		}
	}
	
	// hook
	protected void success() {}
	
	/**
	 * 删除目录
	 * @param dir
	 */
	protected void deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			for(File file:dir.listFiles()) {
				if(file.isDirectory()) {
					deleteDirectory(file);
				}else {
					file.delete();
				}
			}
			dir.delete();			
		}
	}
	
}
