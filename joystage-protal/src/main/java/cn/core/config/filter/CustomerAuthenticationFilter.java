package cn.core.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author shanjianfei
 * @create 2020-11-06 12:14
 * <p>
 * json登入
 */
public class CustomerAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported" + request.getMethod());
        }
        //说明是以json的形式传递参数
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            String username = null;
            String password = null;
            //将传入的json数据转换成map再通过get("key")获得
            try {
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(),
                        Map.class);
                username = map.get("username");
                password = map.get("password");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (username == null) {

            }
            if (password == null) {

            }
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }

        return super.attemptAuthentication(request, response);
    }
}