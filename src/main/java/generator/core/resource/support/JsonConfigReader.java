package generator.core.resource.support;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import generator.core.config.MainConfig;
import generator.core.resource.ConfigReader;

public class JsonConfigReader implements ConfigReader {
	
	private String configFileName;
	
	public JsonConfigReader(String configFileName) {
		super();
		this.configFileName = configFileName;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return Resources.getResourceAsStream(configFileName);
	}

	@Override
	public MainConfig parseConfiguration() throws IOException {
		InputStream inputStream = getInputStream();
		MainConfig config = (MainConfig) JSON.parseObject(inputStream, Charset.defaultCharset(), MainConfig.class, Feature.AutoCloseSource);
		return config;
	}

}
