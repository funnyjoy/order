package com.jpetstore.order.domain.service.catalog;

import com.jpetstore.order.domain.model.Item;

public interface CatalogService {

	Item getItem(String itemId);

	void updateInventoryQuantity(Item item);

	int getInventoryQuantity(String itemId);

}
