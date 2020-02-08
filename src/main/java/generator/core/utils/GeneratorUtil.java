package generator.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class GeneratorUtil {
	
	private static Configuration cfg;
	
	static {
		cfg = getConfiguration();
	}
	
	public static void generate(String key, Path toPath, Object config) {
		File parentFile = toPath.getParent().toFile();
		parentFile.mkdirs();
		
		try (BufferedWriter writer = Files.newBufferedWriter(toPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			cfg.getTemplate(key, "utf-8").process(config, writer);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	}
	
	public static void putTemplate(String key, String template) {
		StringTemplateLoader templateLoader = (StringTemplateLoader) cfg.getTemplateLoader();
		templateLoader.putTemplate(key, template);
	}
	
	public static Configuration getConfiguration() {
		if(cfg == null) {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			cfg.setTemplateLoader(new StringTemplateLoader());
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);			
			return cfg;
		}
		return cfg;
    }
	
	public static String readTemplateString(Path path) throws IOException {
		List<String> lines = Files.readAllLines(path);
		String ret = StringUtil.joinLineList(lines);
		return ret;
	}
}
