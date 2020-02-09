package generator.core.core.exec;

/**
 * common模块生成执行器
 * @author Autom
 * @date 2020年2月9日
 * @version 1.0
 *
 */
public interface CommonExecutor extends GenerateExcutor {
	
	void enumsGenerate() throws Exception;
	
	void exceptionGenerate() throws Exception;
	
	void utilGenerate() throws Exception;
	
}
