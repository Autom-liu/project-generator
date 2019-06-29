package generator.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public abstract class AbstractGenerator {
	
	protected void generator(Path fromPath, Path toPath, Object config) {
		try (BufferedWriter writer = Files.newBufferedWriter(toPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
			freemarker.template.Configuration cfg = getConfiguration(fromPath);
			cfg.getTemplate(fromPath.getFileName().toString()).process(config, writer);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	}
	
	private freemarker.template.Configuration getConfiguration(Path fromPath) throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(fromPath.getParent().toFile());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }
	
}
