package se.citerus.cqrs.bookstore.shopping.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import se.citerus.cqrs.bookstore.shopping.domain.Cart;
import se.citerus.cqrs.bookstore.shopping.domain.Item;
import se.citerus.cqrs.bookstore.shopping.domain.LineItem;

public class CartDtoFactory {
	public static CartDto fromCart(Cart cart) {
		List<LineItemDto> lineItems = new ArrayList<>();
		for(LineItem lineItem : cart.getItems()) {
			LineItemDto itemDto = toLineItemDto(lineItem);
			lineItems.add(itemDto);
		}
		
		CartDto cartDto = new CartDto();
		cartDto.setCartId(cart.getId());
		cartDto.setTotalPrice(cart.getTotalPrice());
		cartDto.setTotalQuantity(cart.getTotalQuantity());
		cartDto.setLineItems(lineItems);
		return cartDto;
	}
	
	private static LineItemDto toLineItemDto(LineItem lineItem) {
		BigDecimal price = lineItem.getTotalPrice();
		int quantity = lineItem.getQuantity();
		Item item = lineItem.getItem();
		LineItemDto itemDto = new LineItemDto();
		itemDto.setProductId(item.getProductId().getProductId());
		itemDto.setTitle(item.getTitle());
		itemDto.setPrice(item.getPrice());
		itemDto.setQuantity(quantity);
		itemDto.setTotalPrice(price);
		return itemDto;
	}
}
