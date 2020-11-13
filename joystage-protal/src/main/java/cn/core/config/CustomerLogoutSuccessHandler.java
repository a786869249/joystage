package cn.core.config;

import cn.core.common.entity.JsonResult;
import cn.core.common.utils.ResultTool;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shanjianfei
 * @create 2020-11-05 16:21
 */
@Component
public class CustomerLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JsonResult result = ResultTool.success();
        response.setContentType("application/json;charset=utf-8");
        System.out.println("-------登出--------");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
