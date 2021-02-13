package ${basePackage}.${moduleName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basePackage}.common.proxy.CriteriaProxy;
import ${basePackage}.common.service.BaseService;
import ${basePackage}.common.vo.PageVO;
import ${basePackage}.${moduleName}.bean.${beanClassName};
import ${basePackage}.${moduleName}.bean.${beanClassName}Example;
import ${basePackage}.${moduleName}.dto.${dtoClassName};
import ${basePackage}.${moduleName}.mapper.${mapperClassName};
import ${basePackage}.${moduleName}.query.${queryClassName};
import ${basePackage}.${moduleName}.service.${serviceClassName};
import ${basePackage}.${moduleName}.vo.${voClassName};

@Service
public class ${className} extends BaseService<${beanClassName}, ${dtoClassName}, ${voClassName}> implements ${serviceClassName} {

	@Autowired
    private ${mapperClassName} ${mapperVariable};
	
	@Override
	public PageVO<${voClassName}> queryPage(${queryClassName} query) {
		
		${beanClassName}Example example = new ExampleProxy();
		
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

	private class ExampleProxy extends ${beanClassName}Example {
		// 静态代理一下
		public Criteria createCriteria() {
			return (Criteria) CriteriaProxy.getInstance(super.createCriteria());
		}
		
		public Criteria or() {
			return (Criteria) CriteriaProxy.getInstance(super.or());
		}
		
	}
}
