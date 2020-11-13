package cn.core.controller;

import cn.core.common.entity.JsonResult;
import cn.core.common.utils.ResultTool;
import cn.core.entity.Users;
import cn.core.entity.UsersRole;
import cn.core.mapper.RoleMapper;
import cn.core.mapper.UsersMapper;
import cn.core.mapper.UsersRoleMapper;
import cn.core.service.MyUserDetailsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


/**
 * @author shanjianfei
 * @create 2020-10-15 15:02
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UsersRoleMapper usersRoleMapper;

    @PostMapping("/user/register")
    public void insert(Users users) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(password);
        int result = usersMapper.insert(users);
        if (result > 0) {
            log.info("添加用户成功");
        } else {
            log.error("添加用户失败");
        }
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("username", users.getUsername());
        Users user = usersMapper.selectOne(usersQueryWrapper);

        UsersRole usersRole = new UsersRole();
        usersRole.setRoleId(2);
        usersRole.setUserId(user.getId());
        usersRoleMapper.insert(usersRole);
    }

    @GetMapping({"/root"})
    @PreAuthorize("hasAnyRole('ROLE_ROOT')")
    public String root() {
        return "root";
    }

    @GetMapping({"/admin"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String admin() {
        return "admin";
    }

    @GetMapping({"/video"})
    @PreAuthorize("hasAnyRole('ROLE_VIDEO')")
    public String video() {
        return "video";
    }

    @GetMapping({"/shop"})
    @PreAuthorize("hasAnyRole('ROLE_SHOP')")
    public String shop() {
        return "shop";
    }

    @GetMapping({"/game"})
    @PreAuthorize("hasAnyRole('ROLE_GAME')")
    public String game() {
        return "game";
    }

    @GetMapping({"/palette"})
    @PreAuthorize("hasAnyRole('ROLE_PALETTE')")
    public String palette() {
        return "palette";
    }

    @GetMapping({"/user"})
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String user() {
        return "user";
    }

    @GetMapping("/")
    public String index() {
        return "x";
    }


}
