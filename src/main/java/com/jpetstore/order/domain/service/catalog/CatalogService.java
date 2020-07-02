package com.jpetstore.order.domain.service.catalog;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jpetstore.order.domain.model.Item;

@RefreshScope
@FeignClient(name = "catalog-api", fallback = CatalogServiceFallback.class)
@Service
public interface CatalogService {

	@GetMapping("/items/{itemId}")
	Item getItem(@PathVariable("itemId") String itemId);

	@PostMapping("/items/{itemId}/inventory")
	void updateInventoryQuantity(@PathVariable("itemId") String itemId, Item item);

	@GetMapping("/items/{itemId}/inventory")
	int getInventoryQuantity(@PathVariable("itemId") String itemId);

}
