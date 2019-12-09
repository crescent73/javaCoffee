package com.murmur.kit;

/**
 * 返回数据：
 * code: 返回码
 * msg: 结果提示信息
 * data:结果数据信息
 */
public class ResultData {
	
    private String code; //结果返回码
    private String msg;  //结果提示信息
    private Data data; //结果数据信息

	public ResultData(){

	}
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
	public void setResult(ResultCodeEnum code) {
		this.code = code.getCode();
		this.msg = code.getDesc();
	}
	
	@Override
	public String toString() {
		return "ResultData [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}
