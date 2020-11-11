package cn.core.mapper;

import cn.core.entity.UsersRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shanjianfei
 * @create 2020-10-23 18:27
 */
@Repository
public interface UsersRoleMapper extends BaseMapper<UsersRole> {

    public List<UsersRole> selectListAll();

}
