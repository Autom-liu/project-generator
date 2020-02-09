package generator.core.core;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.DefaultCommentGenerator;

public class MyCommentGenerator extends DefaultCommentGenerator {
	
    private boolean suppressAllComments;
    
    public MyCommentGenerator() {
        super();
        suppressAllComments = false;
    }
    
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		field.addJavaDocLine("/**" + introspectedColumn.getRemarks() + " **/");
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * " + introspectedColumn.getRemarks());
		method.addJavaDocLine(" **/");
	}
    
    
    
}
