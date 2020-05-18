package com.eccsm.repository;

import java.util.List;

import com.eccsm.model.Item;

public interface ItemDao {

	public void saveItem(Item item);

	public List<Item> listItems();

	public Item updateItem(Item item);

	public void deleteItem(long id);

	public Item getItem(long id);

}
