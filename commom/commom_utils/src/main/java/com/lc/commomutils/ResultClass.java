package com.lc.commomutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultClass {
    @ApiModelProperty(value = "是否成功")
    private boolean isSuccess;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> resultData = new HashMap<>();

    //私有的构造器
    private ResultClass() {
    }

    //响应成功
    public static ResultClass success() {
        ResultClass successRes = new ResultClass();
        //封装相关信息
        successRes.setSuccess(true);

        successRes.setCode(ResultCode.SUCCESS);

        successRes.setMessage("成功");

        return successRes;
    }

    //链式编程
    public ResultClass success(boolean isSuccess) {
        this.setSuccess(isSuccess);
        return this;
    }

    public ResultClass code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultClass message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultClass data(Map<String, Object> resultData) {
        this.setResultData(resultData);
        return this;
    }

    public ResultClass data(String key, Object value) {
        this.resultData.put(key, value);
        return this;
    }

    //响应失败
    public static ResultClass ERROR() {
        ResultClass errorRes = new ResultClass();
        //封装相关信息
        errorRes.setSuccess(false);

        errorRes.setCode(ResultCode.ERROR);

        errorRes.setMessage("失败");

        return errorRes;
    }

}