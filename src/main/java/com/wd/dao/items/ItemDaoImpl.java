package com.wd.dao.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.wd.entity.Item;

public class ItemDaoImpl extends SqlSessionDaoSupport implements IItemDao {

	public boolean addItem(Item item) {
		try{
			super.getSqlSession().insert("com.wd.dao.item.mapper.addItem", item);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean deleteItem(int i_id) {
		try{
			super.getSqlSession().delete("com.wd.dao.item.mapper.deleteItem", i_id);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

	public boolean editItem(Item item) {
		try{
			super.getSqlSession().update("com.wd.dao.item.mapper.updateItem", item);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

	public Item getItem(int i_id) {
		return super.getSqlSession().selectOne("com.wd.dao.item.mapper.getItem", i_id);
	}

	public List<Item> listItems() {
		return super.getSqlSession().selectList("com.wd.dao.item.mapper.getItemsU");
	}

	public List<Item> listStoreItems(int u_id) {
		try{
			List<Item> list_items = super.getSqlSession().selectList("com.wd.dao.item.mapper.getUserItems", u_id);
			return list_items;
		}catch(Exception e){
			return null;
		}
	}

	public int getSales(int i_id) {
		System.out.println("Cache");
		return 0;
	}

	public int getStock(int i_id) {
		System.out.println("Cache");
		return 0;
	}

	public boolean editItemSales(int i_id, int i_sales) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("i_id", i_id);
		map.put("i_sales", i_sales);
		try{
			super.getSqlSession().update("com.wd.dao.item.mapper.updateSales", map);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean editItemStock(int i_id, int i_stock) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("i_id", i_id);
		map.put("i_stock", i_stock);
		try{
			super.getSqlSession().update("com.wd.dao.item.mapper.updateStock", map);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public List<Item> listItemsNoLogin() {
		return super.getSqlSession().selectList("com.wd.dao.item.mapper.getItemsU");
	}

}
