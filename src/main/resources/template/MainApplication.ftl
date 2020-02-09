package ${basePackage};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("${basePackage}.${moduleName}.mapper")
public class ${className} {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(${className}.class, args);
	}
}
