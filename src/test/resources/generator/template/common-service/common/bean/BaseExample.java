package ${basePackage}.${moduleName}.bean;

/**
 * If you want to handle pagination and order by clause by using
 * ${basePackage}.common.service.BaseService.handlePageOrder(PageQuery, Class<? extends BaseOrderByEnum>, BaseExample)
 * you should make your xxxExample bean implements BaseExample
 * @author Autom
 *
 */
public abstract class BaseExample {
	
	public abstract void setOrderByClause(String orderByClause);
	
	public abstract String getOrderByClause();
	
	public abstract void setDistinct(boolean distinct);
	
	public abstract boolean isDistinct();
	
}
