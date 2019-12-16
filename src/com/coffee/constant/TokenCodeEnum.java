package com.coffee.constant;

public enum TokenCodeEnum {
    TOKEN_OK("200","token正常"),
    TOKEN_NOT_EXIST("201","token不存在"),
    TOKEN_TIME_OUT("202","token失效"),
    TOKEN_ERROR("203","token错误"),
    TOKEN_FORMAT_ERROR("204","token格式错误");

    private String code;
    private String desc;

    TokenCodeEnum(String code, String desc)
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
