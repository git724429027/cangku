package com.wang.yun.cache;

import com.wang.bus.domain.Customer;
import com.wang.bus.domain.Goods;
import com.wang.bus.domain.Provider;
import com.wang.bus.mapper.CustomerMapper;
import com.wang.bus.mapper.GoodsMapper;
import com.wang.bus.mapper.ProviderMapper;
import com.wang.yun.common.SpringUtil;
import com.wang.yun.mapper.DeptMapper;
import com.wang.yun.mapper.UserMapper;
import com.wang.yun.pojo.Dept;
import com.wang.yun.pojo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 缓存
 * @author LJH
 *
 */
public class CachePool {
	
	/**
	 * 所有的缓存数据放到这个CACHE_CONTAINER类似于redis
	 */
	public static volatile Map<String, Object> CACHE_CONTAINER = new HashMap<>();
	
	
	/**
	 * 根据KEY删除缓存
	 * @param key
	 */
	public static void removeCacheByKey(String key) {
		if(CACHE_CONTAINER.containsKey(key)) {
			CACHE_CONTAINER.remove(key);
		}
	}
	/**
	 * 清空所有缓存
	 * @param key
	 */
	public static void removeAll() {
		CACHE_CONTAINER.clear();
	}
	
	/**
	 * 同步缓存
	 */
	public static void syncData() {
		//同步部门数据
		DeptMapper deptMapper = SpringUtil.getBean(DeptMapper.class);
		List<Dept> deptList = deptMapper.selectList(null);
		for (Dept dept : deptList) {
			CACHE_CONTAINER.put("dept:"+dept.getId(), dept);
		}
		//同步用户数据
		UserMapper userMapper = SpringUtil.getBean(UserMapper.class);
		List<User> userList = userMapper.selectList(null);
		for (User user : userList) {
			CACHE_CONTAINER.put("user:"+user.getId(), user);
		}
		//同步客户数据
		CustomerMapper customerMapper = SpringUtil.getBean(CustomerMapper.class);
		List<Customer> customerList = customerMapper.selectList(null);
		for (Customer customer : customerList) {
			CACHE_CONTAINER.put("customer:"+customer.getId(), customer);
		}
		//同步供应商数据
		ProviderMapper providerMapper = SpringUtil.getBean(ProviderMapper.class);
		List<Provider> providerList = providerMapper.selectList(null);
		for (Provider provider : providerList) {
			CACHE_CONTAINER.put("customer:"+provider.getId(), provider);
		}
		//同步商品数据
		GoodsMapper goodsMapper=SpringUtil.getBean(GoodsMapper.class);
		List<Goods> goodsList = goodsMapper.selectList(null);
		for (Goods goods : goodsList) {
			CACHE_CONTAINER.put("goods:"+goods.getId(), goods);
		}
	}
	
}
