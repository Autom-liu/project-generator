package ${basePackage}.${moduleName}.service;

import java.util.List;

import ${basePackage}.common.service.IService;
import ${basePackage}.common.vo.PageVO;
import ${basePackage}.${moduleName}.bean.${beanName};
import ${basePackage}.${moduleName}.dto.${beanName}DTO;
import ${basePackage}.${moduleName}.query.${beanName}Query;
import ${basePackage}.${moduleName}.vo.${beanName}VO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface ${beanName}Service extends IService<${beanName}, ${beanName}DTO, ${beanName}VO> {
	
	PageVO<${beanName}VO> queryPage(${beanName}Query query);
	
	List<${beanName}VO> queryList(${beanName}Query query);
	
}
