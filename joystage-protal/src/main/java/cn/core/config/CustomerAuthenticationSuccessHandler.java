package cn.core.config;

import cn.core.common.entity.JsonResult;
import cn.core.common.utils.ResultTool;
import cn.core.entity.Users;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shanjianfei
 * @create 2020-11-05 15:30
 */
@Component
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //返回json数据  用户登录成功
        //更新用户表上次登录时间、更新人、更新时间等字段
//        Users userDetails = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Users user = userService.selectByName(userDetails.getUsername());
//        sysUser.setLastLoginTime(new Date());
//        sysUser.setUpdateTime(new Date());
//        sysUser.setUpdateUser(sysUser.getId());
//        sysUserService.update(sysUser);
//        String name = userDetails.getUsername();
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication1.getName();
        JsonResult result = ResultTool.success(name);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
