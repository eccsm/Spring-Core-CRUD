package com.eccsm.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eccsm.model.Item;
import com.eccsm.repository.impl.ItemDaoImpl;
import com.eccsm.validator.ItemValidator;

@Transactional
@RestController
public class ServiceController {

	@Autowired
	private ItemDaoImpl itemDao;

	@Autowired
	private ItemValidator validator;

	@RequestMapping(value = "item/update/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Item updateitem(@RequestBody Item taken, @ModelAttribute Item item, @PathVariable long id,BindingResult result) {
		validator.validate(taken, result);
		if (!result.hasErrors()) {
		item = itemDao.getItem(id);

		item.setName(taken.getName());
		item.setPrice(taken.getPrice());
		item.setDescription(taken.getDescription());
		itemDao.updateItem(item);
		return item;
		} else {
			System.out.println(result.toString());
		}
		return null;
	}

	@RequestMapping(value = "item/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public Item savetem(@RequestBody Item taken, @ModelAttribute Item item, BindingResult result) {

		validator.validate(taken, result);
		if (!result.hasErrors()) {

			itemDao.saveItem(taken);
			return taken;

		} else {
			System.out.println(result.toString());
		}
		return null;
	}

	@RequestMapping(value = "item/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteitem(@PathVariable long id) {

		itemDao.deleteItem(id);
		return "Success";
	}

	@RequestMapping(value = "item/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Item> findAllitem() {

		List<Item> itemList = itemDao.listItems();
		return itemList;
	}

}
