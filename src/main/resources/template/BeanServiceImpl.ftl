package ${basePackage}.${moduleName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.query.QueryBuilder;
import ${basePackage}.common.service.BaseService;
import ${basePackage}.common.vo.PageVO;
import ${basePackage}.common.util.ConverterUtils;
import ${basePackage}.${moduleName}.bean.${beanClassName};
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
	public PageVO<${voClassName}> queryPage(${queryClassName} query) throws BizException {
		QueryBuilder<${beanClassName}> queryBuilder = new QueryBuilder<>(${beanClassName}.class);

		// TDDO here to build your condition you want
		queryBuilder.selectAll().createCriteria();

		return super.queryPage(query, queryBuilder);
	}
	

	@Override
	public List<${voClassName}> queryList(${queryClassName} query) throws BizException {
		QueryBuilder<${beanClassName}> queryBuilder = new QueryBuilder<>(${beanClassName}.class);

		// TDDO here to build your condition you want
		queryBuilder.selectAll().createCriteria();

		List<${beanClassName}> result = ${mapperVariable}.selectByExample(queryBuilder);
		return ConverterUtils.copyList(result, ${voClassName}.class);
	}
}
