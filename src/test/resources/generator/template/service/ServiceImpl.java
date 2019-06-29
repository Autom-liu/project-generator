package ${basePackage}.${moduleName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basePackage}.common.proxy.CriteriaProxy;
import ${basePackage}.common.service.BaseService;
import ${basePackage}.common.vo.PageVO;
import ${basePackage}.${moduleName}.bean.${beanName};
import ${basePackage}.${moduleName}.bean.${beanName}Example;
import ${basePackage}.${moduleName}.dto.${beanName}DTO;
import ${basePackage}.${moduleName}.mapper.${beanName}Mapper;
import ${basePackage}.${moduleName}.query.${beanName}Query;
import ${basePackage}.${moduleName}.service.${beanName}Service;
import ${basePackage}.${moduleName}.vo.${beanName}VO;

@Service
public class ${beanName}ServiceImpl extends BaseService<${beanName}, ${beanName}DTO, ${beanName}VO> implements ${beanName}Service {

	@Autowired
	private ${beanName}Mapper ${mapperVariable};
	
	@Override
	public PageVO<${beanName}VO> queryPage(${beanName}Query query) {
		
		${beanName}Example example = new ExampleProxy();
		
		super.handlePageOrder(query, null, example);
		
		// TODO Here build the condition you want
		
		List<${beanName}> ${beanListVariable} = ${mapperVariable}.selectByExample(example);
		
		return super.handlePageResult(${beanListVariable});
	}
	

	@Override
	public List<${beanName}VO> queryList(${beanName}Query query) {
		query.setPageFlag(false);
		PageVO<${beanName}VO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}
	
	@Override
	protected void fieldConverter(${beanName} source, ${beanName}VO target) {

	}

	private class ExampleProxy extends ${beanName}Example {
		// 静态代理一下
		public Criteria createCriteria() {
			return (Criteria) CriteriaProxy.getInstance(super.createCriteria());
		}
		
		public Criteria or() {
			return (Criteria) CriteriaProxy.getInstance(super.or());
		}
		
	}
}
