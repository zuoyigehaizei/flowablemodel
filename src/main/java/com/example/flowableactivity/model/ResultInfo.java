package com.example.flowableactivity.model;

public class ResultInfo<T> {

	private String code;
	private String msg;
	private T result;

	public static final String codeSuccessfully = "000000";
	public static final String codeFailure = "000001";
	
	public ResultInfo() {
		super();
	}

	/**
	 * 初始化返回信息
	 * @param code
	 * @param msg
	 * @param result
	 */
	public ResultInfo(String code, String msg, T result) {
		super();

		this.code = code;
		this.msg = msg;
		this.result = result;
	}

	/**
	 * 初始化返回信息
	 * @param result
	 */
	public ResultInfo(T result) {
		super();

		this.code = ResultInfo.codeSuccessfully;
		this.msg = "";
		this.result = result;
	}
	
	public void setCode(String value) {
		this.code = value;
	}

	public String getCode() {
		return this.code;
	}

	public void setMsg(String value) {
		this.msg = value;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setResult(T value) {
		this.result = value;
	}

	public T getResult() {
		return this.result;
	}

}
