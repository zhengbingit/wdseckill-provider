package com.wd.service.items;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wd.dao.items.IItemDao;
import com.wd.entity.Item;
import com.wd.entity.Pages;

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

	public Pages listItemsService(int pageNum, int pageSize) {
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		Pages pages = new Pages();
		pages.setList(itemDao.listItems());
		pages.setPages(page.getPages());
		return pages;
	}

	public Pages listStoreItemsService(int pageNum, int pageSize, int u_id) {
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		Pages pages = new Pages();
		pages.setList(itemDao.listStoreItems(u_id));
		pages.setPages(page.getPages());
		return pages;
	}
	
	public Pages listItemsNoLoginService(int pageNum, int pageSize) {
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		Pages pages = new Pages();
		pages.setList(itemDao.listItemsNoLogin());
		pages.setPages(page.getPages());
		return pages;
	}

	public boolean editItemSalesService(int i_id, int sales) {
		return itemDao.editItemSales(i_id, sales);
	}

	public boolean editItemStockService(int i_id, int stock) {
		return itemDao.editItemStock(i_id, stock);
	}

}
