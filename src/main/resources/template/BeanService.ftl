package ${basePackage}.${moduleName}.service;

import java.util.List;

import ${basePackage}.common.exception.BizException;
import ${basePackage}.common.service.IService;
import ${basePackage}.common.vo.PageVO;
import ${basePackage}.${moduleName}.bean.${beanClassName};
import ${basePackage}.${moduleName}.dto.${dtoClassName};
import ${basePackage}.${moduleName}.query.${queryClassName};
import ${basePackage}.${moduleName}.vo.${voClassName};

/**
 * if your service don't need this method  you can remove it manually
 */
public interface ${className} extends IService<${beanClassName}, ${dtoClassName}, ${voClassName}> {
	
	PageVO<${voClassName}> queryPage(${queryClassName} query) throws BizException;
	
	List<${voClassName}> queryList(${queryClassName} query) throws BizException;
	
}
