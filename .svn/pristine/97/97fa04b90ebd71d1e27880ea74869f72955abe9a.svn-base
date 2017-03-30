package com.hbcsoft.common.model;

import java.io.Serializable;

import com.hbcsoft.common.utils.JsonMapper;

/**
 * Ajax请求Json响应结果模型.
 * 
 */
@SuppressWarnings("serial")
public class Result implements Serializable {

	/**
	 * 成功
	 */
	public static final int SUCCESS = 1;
	/**
	 * 警告
	 */
	public static final int WARN = 2;
	/**
	 * 失败
	 */
	public static final int ERROR = 0;
	
	/**
	 * 逻辑异常
	 */
	public static final int RESULT_ERROR = 3;
	
	/**
	 * 成功消息
	 */
	public static final String SUCCESS_MSG = "操作成功！";
	/**
	 * 失败消息
	 */
	public static final String ERROR_MSG = "操作失败:发生未知异常！";
	
	/**
	 * 删除部门失败消息
	 */
	public static final String ERROR_MSG_ID = "该部门下还有子机构，不能进行删除";
	/**
	 * 删除人员失败消息
	 */
	public static final String ERROR_MSG_PERSON = "该部门下还有人员，不能进行删除";
	/**
	 * 逻辑异常消息
	 */
	public static final String RESULT_MSG = "该数据违背业务逻辑条件，不能提交！";

	/**
	 * 结果状态码(可自定义结果状态码) 1:操作成功 0:操作失败
	 */
	private int code;
	/**
	 * 响应结果描述
	 */
	private String msg;
	/**
	 * 其它数据信息（比如跳转地址）
	 */
	private Object obj;
	
	 /**
	 * 无参构造方法
	 */
	public Result() {
		super();
	}

	/**
	 * 
	 * @param code
	 *            结果状态码(可自定义结果状态码或者使用内部静态变量 1:操作成功 0:操作失败 2:警告)
	 * @param msg
	 *            响应结果描述
	 * @param obj
	 *            其它数据信息（比如跳转地址）
	 */
	public Result(final int code,final String msg,final Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}

	/**
	 * 默认操作成功结果.
	 */
	public static Result successResult() {
		return new Result(SUCCESS, SUCCESS_MSG, null);
	}

	/**
	 * 默认操作失败结果.
	 */
	public static Result errorResult() {
		return new Result(ERROR, ERROR_MSG, null);
	}
	
	/**
	 * 操作机构失败结果.
	 */
	public static Result errorResultID() {
		return new Result(ERROR, ERROR_MSG_ID, null);
	}
	
	/**
	 * 删除子机构失败结果.
	 */
	public static Result errorResultCparentId() {
		return new Result(ERROR, ERROR_MSG_ID, null);
	}
	/**
	 * 删除机构下人员失败结果.
	 */
	public static Result errorResultCparentPerson() {
		return new Result(ERROR, ERROR_MSG_PERSON, null);
	}
	
	/**
	 * 逻辑判断失败结果.
	 */
	public static Result unNormalResult() {
		return new Result(RESULT_ERROR, RESULT_MSG, null);
	}

	/**
	 * 结果状态码(可自定义结果状态码) 1:操作成功 0:操作失败
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置结果状态码(约定 1:操作成功 0:操作失败 2:警告)
	 * 
	 * @param code
	 *            结果状态码
	 */
	public Result setCode(final int code) {
		this.code = code;
		return this;
	}

	/**
	 * 响应结果描述
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置响应结果描述
	 * 
	 * @param msg
	 *            响应结果描述
	 */
	public Result setMsg(final String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * 其它数据信息（比如跳转地址）
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * 设置其它数据信息（比如跳转地址）
	 * 
	 * @param obj
	 *其它数据信息（比如跳转地址）
	 */
	public Result setObj(final Object obj) {
		this.obj = obj;
		return this;
	}
	 /**
	 * toString
	 */
	@SuppressWarnings("static-access")
	@Override
	public String toString() {
		return new JsonMapper().nonDefaultMapper().toJson(this);
	}
}
