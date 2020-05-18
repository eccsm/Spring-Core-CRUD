package com.eccsm.repository.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eccsm.model.Item;
import com.eccsm.repository.ItemDao;

@Repository
public class ItemDaoImpl implements ItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveItem(Item item) {
		sessionFactory.getCurrentSession().save(item);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> listItems() {
		return sessionFactory.getCurrentSession().createQuery("FROM " + Item.class.getName()).list();
	}

	@Override
	public Item updateItem(Item item) {
		sessionFactory.getCurrentSession().update(item);
		return item;
	}

	@Override
	public void deleteItem(long id) {

		Item item = new Item();
		item.setId(id);
		sessionFactory.getCurrentSession().delete(item);

	}

	@Override
	public Item getItem(long id) {
		return sessionFactory.getCurrentSession().get(Item.class, id);
	}

}
