package com.wd.service.items;

import com.wd.entity.Item;
import com.wd.entity.Pages;
/**
 * 商品Service
 * @author zhengbinMac
 *
 */
public interface IItemService {
	
	public boolean editItemStockService(int i_id, int stock);
	
	/**
	 * 增加商品的销量
	 * @param i_id
	 * @param sales
	 * @return
	 */
	public boolean editItemSalesService(int i_id, int sales);
		
	/**
	 * 商铺发布商品
	 * @param item
	 * @return
	 */
	public boolean addItemService(Item item);
	
	/**
	 * 商铺删除商品
	 * @param i_id
	 * @return
	 */
	public boolean deleteItemService(int i_id);
	
	/**
	 * 商铺编辑商品
	 * @param item
	 * @return
	 */
	public boolean editItemService(Item item);
	
	/**
	 * 根据商品id获得商品详情
	 * @param i_id
	 * @return
	 */
	public Item getItemService(int i_id);
	
	/**
	 * 获得商品列表（按时间倒排）
	 * @return
	 */
	public Pages listItemsService(int pageNum,int pageSize);
	
	/**
	 * 获得商品列表（未登录）（按时间倒排）
	 * @return
	 */
	public Pages listItemsNoLoginService(int pageNum, int pageSize); 
	
	/**
	 * 根据用户id获得该卖家店铺的商品列表
	 * @param u_id
	 * @return
	 */
	public Pages listStoreItemsService(int pageNum, int pageSize, int u_id);
	
}
