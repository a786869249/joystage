package cn.core.mapper;

import cn.core.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author shanjianfei
 * @create 2020-10-24 13:07
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    public List<RolePermission> selectListAll();
}
