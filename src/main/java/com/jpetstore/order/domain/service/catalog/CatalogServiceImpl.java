/*
 *    Copyright 2010-2013 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.jpetstore.order.domain.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jpetstore.order.domain.model.Item;

/**
 * @author Eduardo Macarron
 */
@RefreshScope
@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${product.service.url}")
	private String PRODUCT_SERVICE_URL;

	public Item getItem(String itemId) {

		return restTemplate.getForObject(PRODUCT_SERVICE_URL + "/items/" + itemId, Item.class);
	}

	public void updateInventoryQuantity(Item item) {

		restTemplate.postForEntity(PRODUCT_SERVICE_URL + "/items/" + item.getItemId() + "/inventory", item, Void.class);
	}

	public int getInventoryQuantity(String itemId) {

		return restTemplate.getForObject(PRODUCT_SERVICE_URL + "/items/" + itemId + "/inventory", Integer.class);
	}
}
