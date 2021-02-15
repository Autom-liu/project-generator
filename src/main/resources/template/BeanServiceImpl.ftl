package ${basePackage}.${moduleName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basePackage}.common.proxy.CriteriaProxy;
import ${basePackage}.common.service.CommonService;
import ${basePackage}.common.vo.PageVO;
import ${basePackage}.${moduleName}.bean.${beanClassName};
import ${basePackage}.${moduleName}.bean.${beanClassName}Example;
import ${basePackage}.${moduleName}.dto.${dtoClassName};
import ${basePackage}.${moduleName}.mapper.${mapperClassName};
import ${basePackage}.${moduleName}.query.${queryClassName};
import ${basePackage}.${moduleName}.service.${serviceClassName};
import ${basePackage}.${moduleName}.vo.${voClassName};

@Service
public class ${className} extends CommonService<${beanClassName}, ${dtoClassName}, ${voClassName}> implements ${serviceClassName} {


    private ${mapperClassName} ${mapperVariable};

	public ${className}(${mapperClassName} ${mapperVariable}) {
		super(${mapperVariable}, ${beanClassName}.class, ${dtoClassName}.class, ${voClassName}.class);
		this.${mapperVariable} = ${mapperVariable};
	}
	
	@Override
	public PageVO<${voClassName}> queryPage(${queryClassName} query) {
		
		${beanClassName}Example example = new ${beanClassName}Example();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<${beanClassName}> resultList = ${mapperVariable}.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<${voClassName}> queryList(${queryClassName} query) {
		query.setPageFlag(false);
		PageVO<${voClassName}> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

}
