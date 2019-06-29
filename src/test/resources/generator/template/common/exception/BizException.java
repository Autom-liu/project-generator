package ${basePackage}.${moduleName}.exception;

import ${basePackage}.${moduleName}.enums.BizEnum;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = -8402477412842893090L;
	
	private BizEnum bizExceptionEnum;

	public BizException(BizEnum bizExceptionEnum) {
		super();
		this.bizExceptionEnum = bizExceptionEnum;
	}

	public BizEnum getBizExceptionEnum() {
		return bizExceptionEnum;
	}
	
	public Integer getCode() {
		return bizExceptionEnum.getCode();
	}
	
	public String getMsg() {
		return bizExceptionEnum.getMsg();
	}
}
