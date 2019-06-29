package generator.core.yml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public abstract class AbstractYmlGenerator {
	
	public void generate() {
		Path sourcePath = getSourcePath();
		Path targetPath = getTargetPath();
		try {
			Files.walk(sourcePath).forEach(p -> {
				try {
					Path q = targetPath.resolve(sourcePath.relativize(p));
					if(Files.isDirectory(p)) {
						Files.createDirectories(q);
					} else {
						Files.copy(p, q, StandardCopyOption.REPLACE_EXISTING);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract Path getSourcePath();
	
	protected abstract Path getTargetPath();
	
}
