package Test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wd.entity.User;
import com.wd.service.user.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext-*.xml"})
public class UserTest {
	@Resource IUserService userService;
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setU_name("zhengbin1");
		user.setU_pwd("950906");
		user.setU_issell(0);
		user.setU_money(100000);
		userService.addUserService(user);
	}
	
}
