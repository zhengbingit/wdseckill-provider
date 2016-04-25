package Test;

import java.util.ArrayList;
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
	
}
