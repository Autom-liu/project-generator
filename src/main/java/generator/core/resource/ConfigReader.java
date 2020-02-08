package generator.core.resource;

import java.io.IOException;
import java.io.InputStream;

import generator.core.config.MainConfig;

public interface ConfigReader {
	
	InputStream getInputStream() throws IOException;
	
	MainConfig parseConfiguration() throws Exception;

}
