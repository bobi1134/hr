package cn.mrx.hr.utils;

/**
 * Author: xialiangbo
 * Date: 2017/7/27 22:57
 * Description:	AjaxResult JSON返回对象封装
 */
public class AjaxResult {

	private boolean success;
	private String msg;
	private Object data;

	/** getter and setter menthod */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/** method */
	public boolean isSuccess() {
		return success;
	}

	public static AjaxResult R(boolean success) {
		return R(success, "");
	}

	public static AjaxResult R(boolean success, String msg) {
		return R(success, msg, null);
	}

	public static AjaxResult R(boolean success, String msg, Object data) {
		AjaxResult r = new AjaxResult();
		r.setSuccess(success);
		r.setMsg(msg);
		r.setData(data);
		return r;
	}
}
