package com.restaurant.model;

import java.util.List;

import com.restaurantpicture.model.RestaurantPictureVO;

public interface Restaurant_interface {

	public void insert(RestaurantVO restaurantVO);
	public void easyinsert(RestaurantVO restaurantVO);
	public RestaurantVO easyInsertWithPics(RestaurantVO restaurantVO,RestaurantPictureVO restaurantPictureVO);
	public void update(RestaurantVO restaurantVO);
	public void delete(String storeId);
	public RestaurantVO findByPrimaryKey(String storeId);
	public List<RestaurantVO> getAll();
}
