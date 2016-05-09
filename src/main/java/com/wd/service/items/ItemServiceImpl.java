package com.wd.service.items;

import java.util.List;

import com.wd.dao.items.IItemDao;
import com.wd.entity.Item;

public class ItemServiceImpl implements IItemService {
	private IItemDao itemDao;
	public IItemDao getItemDao() {
		return itemDao;
	}
	public void setItemDao(IItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public boolean addItemService(Item item) {
		return itemDao.addItem(item) == true ? true : false;
	}

	public boolean deleteItemService(int i_id) {
		return itemDao.deleteItem(i_id) == true ? true : false;
	}

	public boolean editItemService(Item item) {
		return itemDao.editItem(item) == true ? true : false;
	}

	public Item getItemService(int i_id) {
		return	itemDao.getItem(i_id);
	}

	public List<Item> listItemsService() {
		return itemDao.listItems();
	}

	public List<Item> listStoreItemsService(int u_id) {
		return itemDao.listStoreItems(u_id);
	}
	
	public List<Item> listItemsNoLoginService() {
		return itemDao.listItemsNoLogin();
	}

	public boolean editItemSalesService(int i_id, int sales) {
		return itemDao.editItemSales(i_id, sales);
	}

	public boolean editItemStockService(int i_id, int stock) {
		return itemDao.editItemStock(i_id, stock);
	}

}
