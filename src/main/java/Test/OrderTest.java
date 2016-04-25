package Test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wd.entity.Orders;
import com.wd.service.order.IOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext-*.xml"})
public class OrderTest {
	@Resource IOrderService orderService;
	
	@Test
	public void testAddOrder() {
		Orders order = new Orders();
		order.setI_id(2);
		order.setU_id(2);
		order.setO_count(1);
		order.setO_time(new Date());
		orderService.addOrdersService(order);
	}
	
	@Test
	public void testListOrder() {
		int u_id = 2;
		List<Orders> list_orders = orderService.listOrdersService(u_id);
		for(Orders o : list_orders){
			System.out.println(o);
		}
	}
}
