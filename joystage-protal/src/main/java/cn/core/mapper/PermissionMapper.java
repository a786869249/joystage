package cn.core.mapper;

import cn.core.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author shanjianfei
 * @create 2020-10-24 13:07
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    public List<Permission> selectListByUser(Integer userId);

}
