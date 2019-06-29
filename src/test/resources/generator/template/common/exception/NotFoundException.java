package ${basePackage}.${moduleName}.exception;

import ${basePackage}.${moduleName}.enums.NotFoundEnum;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2419651699941615039L;
	
	private NotFoundEnum notFoundEnums;

	public NotFoundException(NotFoundEnum notFoundEnums) {
		super(notFoundEnums.getMsg());
		this.notFoundEnums = notFoundEnums;
	}

	public NotFoundEnum getNotFoundEnums() {
		return notFoundEnums;
	}
	
	public Integer getCode() {
		return notFoundEnums.getCode();
	}
	
	public String getMsg() {
		return notFoundEnums.getMsg();
	}
}
