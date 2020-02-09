package generator.core.core.exec;

/**
 * common-service 模块生成执行器
 * @author Autom
 * @date 2020年2月9日
 * @version 1.0
 *
 */
public interface CommonServiceExcutor {

	void baseGenerate() throws Exception;
	
	void configGenerate() throws Exception;
	
	void enumsGenerate() throws Exception;
	
	void factoryGenerate() throws Exception;
	
	void proxyGenerate() throws Exception;
	
	void queryGenerate() throws Exception;
	
	void serviceGenerate() throws Exception;
	
	void voGenerate() throws Exception;
	
	void webGenerate() throws Exception;
	
}
