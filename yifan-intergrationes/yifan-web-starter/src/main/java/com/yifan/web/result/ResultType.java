package com.yifan.web.result;

/**
 * http 请求返回状态码
 * 
 * @author caowuchao
 * @since 2018年5月16日
 * @version 1.0
 */
public enum ResultType implements BaseResultType {

	OK(200, "成功"),

	FAILURE(210, "请求失败"),

	BAD_REQUEST(400, "请求参数错误"),

	UNAUTHORIZED (401, "授权失败"),

	FORBIDDEN(403, "禁止访问"),

	NOT_FOUND(404, "请求地址错误"),

	METHOD_NOT_ALLOWED(405, "请求方法错误"),

	UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),

	INTERNAL_ERROR(500, "服务异常"),
	
	NOT_EXTENDED(510, "未知错误"),

	FAIL_LIST(520, "上传失败");


	private final Integer code;
	private final String msg;

	ResultType(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer code() {
		return code;
	}

	@Override
	public String msg() {
		return msg;
	}

	public static ResultType getResultTypeByCode(int code) {
		for (ResultType obj :ResultType.values()) {
			if (obj.code() == code) {
				return obj;
			}
		}
		return ResultType.NOT_EXTENDED;
	}
}
