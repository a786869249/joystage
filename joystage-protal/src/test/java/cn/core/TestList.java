package cn.core;

import cn.core.entity.Users;
import cn.core.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestList {

	@Autowired
	private UsersMapper usersMapper;

	@Test
	public void test01(){
		List<Users> list = usersMapper.selectListAll();
		for(Users user : list) {
			String str = user.toString();
			String x = str.replaceAll(",", "#*#");
			System.out.println(x);
		}
	}

}
