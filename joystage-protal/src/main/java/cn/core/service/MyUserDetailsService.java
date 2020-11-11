package cn.core.service;

import cn.core.entity.*;
import cn.core.mapper.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author shanjianfei
 * @create 2020-10-15 19:26
 */
@Slf4j
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UsersRoleMapper usersRoleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //调用usersMapper方法查询数据库 根据用户名查询数据库
        List<Users> users = usersMapper.selectByMap(new HashMap<String, Object>() {{
            put("username", username);
        }});
        if (users == null && users.size()==0) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        Users user = users.get(0);
        List<UsersRole> userRoles = usersRoleMapper.selectByMap(new HashMap<String, Object>() {{
            put("user_id", user.getId());
        }});
        log.info("userRoles ={}",userRoles);
        if (CollectionUtils.isEmpty(userRoles)) {
            log.info("该用户尚未分配角色");
            return null;
        }
        List<Integer> roleIds = userRoles.stream().map(UsersRole::getRoleId).distinct().collect(Collectors.toList());
        log.info("roleIds ={}",roleIds);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(new QueryWrapper<RolePermission>().in("role_id", roleIds));
        log.info("rolePermissions ={}",rolePermissions);
        if(CollectionUtils.isEmpty(rolePermissions)){
            log.info("所有均角色尚未绑定权限");
            return null;
        }

        //获取该用户所拥有的权限
        List<Integer> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).distinct().collect(Collectors.toList());
        log.info("permissionIds ={}",permissionIds);
        List<Permission> permissions = permissionMapper.selectBatchIds(permissionIds);
        log.info("permissions ={}",permissions);
        if(CollectionUtils.isEmpty(permissions)){
            log.info("权限不存在");
            return null;
        }

        String codes = permissions.stream().map(Permission::getCodes).distinct().collect(Collectors.joining(","));
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(codes);
        //从查询数据库返回users对象，得到用户名和密码返回
        return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), auths);
    }

}
