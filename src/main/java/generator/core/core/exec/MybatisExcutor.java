package generator.core.core.exec;

import org.mybatis.generator.config.Configuration;

/**
 * Mybatis-generator 生成器，表变更使用
 * @author Autom
 * @date 2020年2月9日
 * @version 1.0
 *
 */
public interface MybatisExcutor extends GenerateExcutor {
	
	void beforeGenerate() throws Exception;
	
	void afterGenerate(Configuration config) throws Exception;
	
}
