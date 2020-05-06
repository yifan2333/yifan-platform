package com.yifan.web.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yifan.web.result.ActionResult;
import com.yifan.web.result.ResultType;

import springfox.documentation.annotations.ApiIgnore;


/**
 * 全局异常处理
 *
 * @author caowuchao
 * @since 2018年5月17日
 * @version 1.0
 */
@ApiIgnore
@RestController
public class BaseExceptionController implements ErrorController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping(value = "/error")
	public ActionResult<String> error(HttpServletResponse response, Exception e) {
		ActionResult.Builder<String> builder = new ActionResult.Builder<>();
		int status = response.getStatus();
		logger.error("Response error code: {}", response.getStatus());

		switch (status) {
			case HttpServletResponse.SC_UNAUTHORIZED:
				logger.error("Unauthorized");
				return builder.resultType(ResultType.UNAUTHORIZED).build();
			case HttpServletResponse.SC_FORBIDDEN:
				logger.error("Permission denied");
				return builder.resultType(ResultType.FORBIDDEN).build();
			case HttpServletResponse.SC_NOT_FOUND:
				logger.error("404 NOT FOUND");
				return builder.resultType(ResultType.NOT_FOUND).build();
			default:
				logger.error("ERROR...");
				break;
		}

		return builder.resultType(ResultType.NOT_EXTENDED).build();
	}
}
