package cn.core.service.impl;

import cn.core.entity.Permission;
import cn.core.mapper.PermissionMapper;
import cn.core.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shanjianfei
 * @create 2020-11-02 12:37
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> selectPermissionStates(Integer states) {
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.eq("states", states);
        List<Permission> permissions = permissionMapper.selectList(permissionQueryWrapper);
        return permissions;
    }

}
