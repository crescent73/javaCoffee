package com.murmur.kit;


public enum ResultCodeEnum
{
	
	SITES_OPEN("101","网页打开成功"),
	INTERNTE_FAILURE("102","网络错误，请重试"),
	UNKOWN_ERROE("103","未知的错误"),
	REQUEST_NO_PARAM_ID_ERROR("104","页面请求参数错误"),
	DB_SYS_ERROR("105","数据库错误"),
	
	//连接
	DB_CONNECTION_SUCCESS("200","数据库连接成功"),
	DB_CONNECTION_FAILURE("201","数据库连接失败"),
	//增
	DB_ADD_SUCCESS("202","添加成功"),
	DB_ADD_FAILURE("203","添加失败"),
	DB_ADD_FAILURE_TEACHER_NOT_EXIST("203","添加失败_老师不存在"),
	DB_ADD_FAILURE_COURSE_NOT_EXIST("203","添加失败_课程不存在"),
	DB_ADD_FAILURE_TEACHER_ALREADY_EXIST("203","添加失败_老师已存在"),
	DB_ADD_FAILURE_STUDENT_ALREADY_EXIST("203","添加失败_学生已存在"),
	//删
	DB_DELETE_SUCCESS("204","删除成功"),
	DB_DELETE_FAILURE("205","删除失败"),
	DB_DELETE_FAILURE_COURSE_NOT_EXIST("205","删除失败_课程不存在"),
	//改
	DB_UPDATE_SUCCESS("206","修改成功"),
	DB_UPDATE_ERROR("207","修改失败"),
	DB_UPDATE_REQUEST_NULL("207","修改失败_请输入修改数据"),
	DB_UPDATE_ERROR_OVERFLOW("207","修改失败_字段字数超过规定"),
	DB_UPDATE_FAILURE_TEACHER_NOT_EXIST("207","修改失败_老师不存在"),
	DB_UPDATE_FAILURE_COURSE_NOT_EXIST("207","修改失败_课程不存在"),
	DB_UPDATE_FAILURE_NOTICE_NOT_EXIST("207","修改失败_公告不存在"),
	//查
	DB_FIND_SUCCESS("208","查找成功"),
	DB_FIND_FAILURE("209","查找失败，没有该条记录"),
	//详细错误
	
	
	//请求参数	
	PARA_WORNING_NULL("301","必要请求参数为空"),
	PARA_FORMAT_ERROR("302","请求的参数格式错误"),
	PARA_NUM_ERROR("303","请求的参数个数错误"),

	//系统功能
	LOGIN_SUCCESS("400","登录成功"),
	LOGIN_ERROR("401","登录失败_账号或密码错误"),
	NO_EXIST_USER("402","用户不存在"),
	NO_ENOUGH_MES("403","登录失败_账号或密码为空"),
	LOGOUT_SUCCESS("404","退出登录成功"),
	NO_LOGIN_USER("405","退出登录失败_用户未登录"),
	FILE_UPLOAD_SUCCESS("406","文件上传成功"),
	FILE_UPLOAD_FAILURE("407","文件上传失败"),
	FILE_DOWNLOAD_SUCCESS("408","文件下载成功"),
	FILE_DOWNLOAD_FAILURE("409","文件下载失败");
	
	private String code;
    private String desc;

    ResultCodeEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    public String getCode()
    {
        return code;
    }

    public String getDesc()
    {
        return desc;
    }


}
