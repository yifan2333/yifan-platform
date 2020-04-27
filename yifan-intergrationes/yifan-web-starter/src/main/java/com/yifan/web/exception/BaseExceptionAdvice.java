package com.yifan.web.exception;


import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yifan.web.result.ActionResult;
import com.yifan.web.result.ResultType;


/**
 *
 * @author: wuyifan
 * @date: 2018年07月27日 10:25
 */
@ResponseBody
@ControllerAdvice
public class BaseExceptionAdvice {

	private final Logger logger = LoggerFactory.getLogger(BaseExceptionAdvice.class);

	/**
	 * 400 请求 Bad Request
	 *
	 * @param e e
	 * @return enn.icome.kernel.web.result.ActionResult
	 * @author wuyifan
	 * @date 2019年9月17日 下午2:40
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ActionResult<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		logger.error("缺少请求参数{}", e.getMessage());
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		return builder.resultType(ResultType.BAD_REQUEST).message(e.getMessage()).build();
	}

	/**
	 * 400 请求 Bad Request
	 *
	 * @param e e
	 * @return enn.icome.kernel.web.result.ActionResult
	 * @author wuyifan
	 * @date 2019年9月17日 下午2:41
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ActionResult<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		logger.error("参数解析异常 {}", e.getMessage());
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		return builder.resultType(ResultType.BAD_REQUEST).build();
	}

	/**
	 * hibernate-validator 校验异常统一处理
	 * @return enn.icome.kernel.team.common.kernel.ActionResult
	 * @param e e
	 * @author wuyifan
	 * @date 2018年8月3日 11:20
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ActionResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		FieldError error = result.getFieldError();
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		builder.resultType(ResultType.BAD_REQUEST);
		if (error != null) {
			logger.error("参数校验异常 {}", error.getDefaultMessage());
			builder.message(error.getDefaultMessage());
		}
		return builder.build();
	}

	/**
	 * 400 请求 Bad Request
	 *
	 * @param e e
	 * @return enn.icome.kernel.web.result.ActionResult
	 * @author wuyifan
	 * @date 2019年9月17日 下午2:42
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ActionResult<String> handleBindException(BindException e) {
		BindingResult bindingResult = e.getBindingResult();
		FieldError fieldError = bindingResult.getFieldError();
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		builder.resultType(ResultType.BAD_REQUEST);
		if (fieldError != null) {
			logger.error("参数绑定失败 {}", fieldError.getDefaultMessage());
			builder.message(fieldError.getDefaultMessage());
		}
		return builder.build();
	}

	/**
	 * 400 请求 Bad Request
	 *
	 * @param e e
	 * @return enn.icome.kernel.web.result.ActionResult
	 * @author wuyifan
	 * @date 2019年9月17日 下午3:13
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ActionResult<String> handleServiceException(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		ConstraintViolation<?> violation = violations.iterator().next();
		String message = violation.getMessage();
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		builder.resultType(ResultType.BAD_REQUEST);
		if (StringUtils.isNotBlank(message)) {
			logger.error("参数验证失败 {}", message);
			builder.message(message);
		}
		return builder.build();
	}

	/**
	 * 400 请求 Bad Request
	 *
	 * @param e e
	 * @return enn.icome.kernel.web.result.ActionResult
	 * @author wuyifan
	 * @date 2019年9月17日 下午3:13
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public ActionResult<String> handleValidationException(ValidationException e) {
		logger.error("参数验证失败 {}", e.getMessage());
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		return builder.resultType(ResultType.BAD_REQUEST).build();
	}

	/**
	 * 405 Method Not Allowed
	 *
	 * @param e e
	 * @return enn.icome.kernel.web.result.ActionResult
	 * @author wuyifan
	 * @date 2019年9月17日 下午3:13
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ActionResult<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		logger.error("不支持当前请求的HTTP Method {}", e.getMessage());
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		String message = "当前方法不支持" + e.getMethod() + "请求";
		if (e.getSupportedMethods() != null && e.getSupportedMethods().length > 0) {
			message += ",支持" + String.join(",", e.getSupportedMethods());
		}
		return builder.resultType(ResultType.METHOD_NOT_ALLOWED).message(message).build();
	}

	/**
	 * 415 Unsupported Media Type
	 *
	 * @param e e
	 * @return enn.icome.kernel.web.result.ActionResult
	 * @author wuyifan
	 * @date 2019年9月17日 下午3:14
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ActionResult<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		logger.error("Unsupported Media Type {}", e.getMessage());
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		builder.resultType(ResultType.UNSUPPORTED_MEDIA_TYPE);
		if (e.getContentType() != null) {
			builder.message(e.getContentType() + "不支持");
		}
		return builder.build();
	}

	/**
	 * Handle exception action result.
	 *
	 * @param e the e
	 * @return the action result
	 * @author wuyifan
	 * @date 2020年04月26日 17:01
	 */
	@ExceptionHandler(Exception.class)
	public ActionResult<String> handleException(Exception e) {
		logger.error("系统异常", e);
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		return builder.resultType(ResultType.INTERNAL_ERROR).build();
	}
}
