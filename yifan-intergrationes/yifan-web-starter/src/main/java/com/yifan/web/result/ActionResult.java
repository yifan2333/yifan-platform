package com.yifan.web.result;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * The type Action result.
 *
 * @param <T> the type parameter
 * @author wuyifan
 * @date 2020年04月17日 18:06
 */
public class ActionResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;
	private String message;
	private T data;

	/**
	 * Instantiates a new Action result.
	 *
	 * @param builder the builder
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public ActionResult(Builder<T> builder) {
		this.code = builder.code;
		this.message = builder.message;
		this.data = builder.data;
	}

	/**
	 * Instantiates a new Action result.
	 *
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public ActionResult() {
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code the code
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets message.
	 *
	 * @return the message
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets message.
	 *
	 * @param message the message
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets data.
	 *
	 * @return the data
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets data.
	 *
	 * @param data the data
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Successful boolean.
	 *
	 * @return the boolean
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public boolean successful() {
		return ResultType.OK.code().equals(code);
	}

	/**
	 * Is data success boolean.
	 *
	 * @param actionResult the action result
	 * @return the boolean
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public static boolean isDataSuccess(ActionResult<?> actionResult) {
		return actionResult != null && ResultType.OK.code().equals(actionResult.getCode()) && actionResult.getData() != null;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	/**
	 * The type Builder.
	 *
	 * @param <T> the type parameter
	 * @author wuyifan
	 * @date 2020年04月17日 18:06
	 */
	public static class Builder<T> {
		private int code;
		private String message;
		private T data;

		/**
		 * Instantiates a new Builder.
		 *
		 * @author wuyifan
		 * @date 2020年04月17日 18:06
		 */
		public Builder() {}

		/**
		 * Code builder.
		 *
		 * @param code the code
		 * @return the builder
		 * @author wuyifan
		 * @date 2020年04月17日 18:06
		 */
		public Builder<T> code(int code) {
			this.code = code;
			return this;
		}

		/**
		 * Message builder.
		 *
		 * @param message the message
		 * @return the builder
		 * @author wuyifan
		 * @date 2020年04月17日 18:06
		 */
		public Builder<T> message(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Data builder.
		 *
		 * @param data the data
		 * @return the builder
		 * @author wuyifan
		 * @date 2020年04月17日 18:06
		 */
		public Builder<T> data(T data) {
			this.data = data;
			return this;
		}

		/**
		 * Result type builder.
		 *
		 * @param resultType the result type
		 * @return the builder
		 * @author wuyifan
		 * @date 2020年04月17日 18:06
		 */
		public Builder<T> resultType(BaseResultType resultType) {
			this.code = resultType.code();
			this.message = resultType.msg();
			return this;
		}

		/**
		 * Build action result.
		 *
		 * @return the action result
		 * @author wuyifan
		 * @date 2020年04月17日 18:06
		 */
		public ActionResult<T> build() {
			initDefaultValue(this);
			return new ActionResult<>(this);
		}

		/**
		 * Init default value.
		 *
		 * @param builder the builder
		 * @author wuyifan
		 * @date 2020年04月17日 18:06
		 */
		private void initDefaultValue(Builder<T> builder) {
			if (builder.code == 0) {
				builder.code = ResultType.OK.code();
			}
			if (builder.message == null) {
				builder.message = ResultType.OK.msg();
			}
		}
	}

}
