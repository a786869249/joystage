package cn.core.mapper;

import cn.core.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shanjianfei
 * @create 2020-10-23 18:26
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    public List<Role> selectListAll();
}
