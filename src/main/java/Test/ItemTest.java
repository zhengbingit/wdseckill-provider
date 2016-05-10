package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.wd.entity.Item;
import com.wd.entity.Pages;
import com.wd.service.items.IItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext-*.xml"})
public class ItemTest {
	@Resource IItemService itemService;
	
	@Test
	public void testEditItemSales() {
		int i_id = 2;
		int sales = 3;
		System.out.println(itemService.editItemSalesService(i_id, sales));
	}
	
	@Test
	public void testEditItemStock() {
		int i_id = 2;
		int stock = 3;
		System.out.println(itemService.editItemStockService(i_id, stock));
	}
	
	@Test
	public void testGetItem() {
		int i_id = 1;
		Item item = itemService.getItemService(i_id);
		System.out.println(item);
	}
	
	@Test
	public void testAddItem() {
		Item item = new Item();
		itemService.addItemService(item);
	}
	
	@Test
	public void testDeleteItem() {
		int i_id = 0;
		itemService.deleteItemService(i_id);
	}
	
	@Test
	public void testEditDeleteItem() {
		Item item = new Item();
		
		itemService.editItemService(item);
	}
	
	@Test
	public void testListItems() {
		int pageNum = 2;
		int pageSize = 2;
		Pages pages = itemService.listItemsService(pageNum, pageSize);
		List<Item> list_item = (ArrayList<Item>)pages.getList();
		System.out.println(pages.getPages());
		for(Item item : list_item){
			System.out.println(item);
		}
	}
	
	@Test
	public void testListStoreItems() {
		PageHelper.startPage(1, 1);
		int u_id = 2;
		List<Item> list_item = itemService.listStoreItemsService(u_id);
		for(Item item : list_item) {
			System.out.println(item);
		}
	}
	
	@Test
	public void testTime() throws ParseException {
		int i_id = 2;
		//商品原有日期
		Item item = itemService.getItemService(i_id);
		//日期格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//系统当前日期
		Date date = new Date();//获得系统时间.
		System.out.println(sdf.format(date));
		//输入自定义时间
		String diyTime = "1995-10-29 11:00:12";
		Date date1 = sdf.parse(diyTime);
        item.setI_time(date1);
        //修改商品发布时间
		itemService.editItemService(item);
	}
	@Test
	public void compareTime() {
		int i_id = 7;
		//商品原有日期
		Item item = itemService.getItemService(i_id);
		Date i_time = item.getI_killtime();
		Date n_time = new Date();
		System.out.println(i_time.before(n_time));
	}
}
