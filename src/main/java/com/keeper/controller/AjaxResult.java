package com.keeper.controller;

public class AjaxResult {
	private boolean success;
	private String errorMsg;
	private Object data;
	private String code;

	public AjaxResult() {
		this.success = true;
		this.code = "000000";
	}

	public AjaxResult(String errorMsg) {
		this.success = false;
		this.errorMsg = errorMsg;
		this.code = "777777";
	}

	public AjaxResult with(Object data) {
		this.data = data;
		return this;
	}

	public AjaxResult with(String code) {
		this.code = code;
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
