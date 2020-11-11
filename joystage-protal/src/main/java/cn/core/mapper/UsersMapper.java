package cn.core.mapper;

import cn.core.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shanjianfei
 * @create 2020-10-15 14:38
 */
@Repository
public interface UsersMapper extends BaseMapper<Users> {

    public List<Users> selectListAll();


}
