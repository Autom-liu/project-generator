package generator.core.config;

public enum GenerateMode {

	/** 基础框架生成  只生成common和common-service **/
	BASE,
	
	/** 只生成模块 **/
	MODULE,
	
	/** 表变更 只实体覆盖 **/
	ENTITY_OVERRIDE,
	
	/** 完整生成 **/
	INTEGRATE
	
}
