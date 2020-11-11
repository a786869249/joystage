package cn.core.config;

import cn.core.common.entity.JsonResult;
import cn.core.common.enums.ResultCode;
import cn.core.common.utils.ResultTool;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shanjianfei
 * @create 2020-11-05 15:15
 * 未登录跳转页面，返回json数据
 */
@Component
public class CustomerAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //返回json数据  用户未登录  2001
        JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
