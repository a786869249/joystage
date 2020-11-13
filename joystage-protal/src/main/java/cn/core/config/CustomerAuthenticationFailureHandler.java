package cn.core.config;

import cn.core.common.entity.JsonResult;
import cn.core.common.enums.ResultCode;
import cn.core.common.utils.ResultTool;
import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shanjianfei
 * @create 2020-11-05 15:49
 */
@Component
public class CustomerAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //登录失败,返回json数据
        JsonResult result = null;
        if(exception instanceof AccountExpiredException){
            //账号过期
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        }else if(exception instanceof BadCredentialsException){
            //密码错误
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        }else if(exception instanceof CredentialsExpiredException){
            //密码过期
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
        }else if(exception instanceof DisabledException){
            //账号不可用
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
        }else if(exception instanceof LockedException){
            //账号锁定
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_LOCKED);
        }else if(exception instanceof InternalAuthenticationServiceException){
            //用户不存在
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }else{
            //其他错误
            result = ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        //处理编码方式，防止中文乱码
        response.setContentType("application/json;charset=utf-8");
        //加到HttpServletResponse中返回给前端
        response.getWriter().write(JSON.toJSONString(result));
    }
}
