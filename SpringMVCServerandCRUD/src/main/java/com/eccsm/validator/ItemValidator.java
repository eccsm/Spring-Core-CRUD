package com.eccsm.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.eccsm.model.Item;

@Component
public class ItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Item.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Item item = (Item) target;

		if (item.getName() == null) {
			errors.rejectValue("name", "name.required", "Name is Required");
		}
		if (item.getDescription() == null) {
			errors.rejectValue("description", "description.required", "Description is Required");
		}
		if (item.getPrice() == 0) {
			errors.rejectValue("price", "price.required", "Price is Required");
		}

		if (item.getName().length() < 4 || item.getName().length() > 8) {
			errors.rejectValue("name", "name.length", "Please enter item name within 4 to 8 charaters only");
		}

		if (item.getDescription().length() < 6) {
			errors.rejectValue("description", "description.lenght", "Please enter description at least 6 charaters");
		}

	}

}
