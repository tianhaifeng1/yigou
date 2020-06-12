package com.t.httplib.yigou.bean;


import com.trjx.tbase.mvp.RespResultInfo;

/**
 * 响应参数对象的框架类
 *
 * @param <T> 响应参数的类
 */
public class DemoRespBean<T> extends RespResultInfo {
	/**返回code*/
	private int status;
	/**返回参数*/
	private String message = "";
	/**返回数据*/
	private T data;
	public DemoRespBean(){
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}


	@Override
	public int getResultState() {
		return status;
	}

	@Override
	public String getRemindMessage() {
		return message;
	}

}
