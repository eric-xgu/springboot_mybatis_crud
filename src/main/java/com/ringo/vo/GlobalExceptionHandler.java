package com.ringo.vo;

import com.ringo.exceptions.ParamsException;
import com.ringo.model.ResultInfo;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public ResultInfo resolveException(Exception e){
        if(e instanceof ParamsException){
            ParamsException e1=(ParamsException)e;
            ResultInfo resultInfo=new ResultInfo();
            resultInfo.setCode(e1.getCode());
            resultInfo.setMsg(e1.getMsg());
            return  resultInfo;
        }
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setCode(301);
        resultInfo.setMsg("操作失败1");
        return resultInfo;
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResultInfo resolvebindException(BindException e){
        if(e instanceof BindException){
            ResultInfo resultInfo=new ResultInfo();
            resultInfo.setCode(302);
            resultInfo.setMsg(e.getBindingResult().getFieldError().getDefaultMessage());
            return  resultInfo;
        }
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setCode(302);
        resultInfo.setMsg("参数校验失败");
        return resultInfo;
    }

}
