package ${basePackage}.${moduleName}.enums;

/**
 * 用于定义排序字段的枚举常量
 * 所有需要排序的字段枚举常量都需要实现该接口，以做分页排序逻辑处理
 * @author Autom
 * @see ${basePackage}.${moduleName}.service.BaseService.handlePageOrder
 */
public interface BaseOrderByEnum extends BaseStatusEnum {
	
	String getOrderField();
	
}
