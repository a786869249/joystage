package cn.core.config;

import cn.core.config.filter.CustomerAuthenticationFilter;
import cn.core.entity.Permission;
import cn.core.service.impl.UsersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author shanjianfei
 * @create 2020-10-23 16:00
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomerAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private CustomerAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomerAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomerLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private CustomerAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomerSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<Permission> permissions = usersService.selectPermissionStates(1);
        List<String> permissionUrl = permissions.stream().map(Permission::getPermissionUrl).collect(Collectors.toList());

//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
//                = http.authorizeRequests();
//        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();//让Spring security放行所有preflight request

        //开启跨域 cors()
        http.cors().and().csrf().disable().authorizeRequests()
                //处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        //默认能够访问的页面
        String[] toBeStored = new String[permissionUrl.size()];
        permissionUrl.toArray(toBeStored);
        //登录成功和失败加载自定义和登出Handler,返回json数据
        http.authorizeRequests()
                .antMatchers(toBeStored).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()//允许所有用户.permitAll()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().logout().permitAll()
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")            //登出之后删除cookie
                .and().sessionManagement().maximumSessions(1)//限制一个账号只能一个人使用
                .expiredSessionStrategy(sessionInformationExpiredStrategy);
        //匿名用户无法访问页面返回json数据
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.addFilterAt(customerAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //开启跨域访问
//        http.cors().disable();
//        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
//        http.csrf().disable();
    }

    @Bean
    CustomerAuthenticationFilter customerAuthenticationFilter() throws Exception {
        CustomerAuthenticationFilter filter = new CustomerAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
