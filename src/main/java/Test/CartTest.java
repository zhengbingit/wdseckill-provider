package Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wd.entity.Cart;
import com.wd.service.cart.ICartService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext-*.xml"})
public class CartTest {
	@Resource ICartService cartService;
	
	@Test
	public void testAddCart() {
		Cart cart = new Cart();
		cart.setU_id(3);
		cart.setI_id(2);
		cart.setC_count(2);
		cartService.addCart(cart);
	}
	
	@Test
	public void testListCart() {
		int u_id = 2;
		List<Cart> list_cart = (ArrayList<Cart>)cartService.listCart(u_id);
		for(Cart cart : list_cart) {
			System.out.println(cart);
		}
	}
	
	@Test
	public void testTime() {
		Date date = new Date();
		List<Cart> list_cart = cartService.listCart(2);
        Date date2 = list_cart.get(0).getItem().getI_killtime();
        
        long temp = date2.getTime() - date.getTime();    //相差毫秒数
        long hours = temp / 1000 / 3600;                //相差小时数
        long temp2 = temp % (1000 * 3600);
        long mins = temp2 / 1000 / 60;                    //相差分钟数
        System.out.println("date2 与 date 相差" + hours + "小时"+ mins + "分钟");
	}
	
	@Test
	public void testGetCart() {
		int c_id = 2;
		Cart cart = cartService.getCart(c_id);
		System.out.println(cart);
	}
	
}
