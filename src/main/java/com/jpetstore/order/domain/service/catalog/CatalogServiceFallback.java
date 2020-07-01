package com.jpetstore.order.domain.service.catalog;

import org.springframework.stereotype.Component;

import com.jpetstore.order.domain.model.Item;

@Component
public class CatalogServiceFallback implements CatalogService {

	@Override
	public Item getItem(String itemId) {
		Item item = new Item();
		item.setItemId(itemId);
		return item;
	}

	@Override
	public void updateInventoryQuantity(String itemId, Item item) {
	}

	@Override
	public int getInventoryQuantity(String itemId) {
		return -1;
	}

}
