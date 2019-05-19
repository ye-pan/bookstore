package se.citerus.cqrs.bookstore.shopping.infrastructure;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.google.common.base.Preconditions;

import se.citerus.cqrs.bookstore.shopping.domain.Cart;
import se.citerus.cqrs.bookstore.shopping.domain.CartRepository;

@Repository
public class InMemoryCartRepository implements CartRepository {
	private ConcurrentHashMap<String, Cart> sessions = new ConcurrentHashMap<>();
	
	@Override
	public void save(Cart cart) {
		if(sessions.putIfAbsent(cart.cartId, cart) != null) {
			throw new IllegalArgumentException(String.format("Shopping cart with id '%s' already exists", cart.cartId));
		}
	}
	
	@Override
	public Cart get(String cartId) {
		Cart cart = sessions.get(cartId);
		Preconditions.checkArgument(cart != null, "No shopping cart withd id '%s' exists", cartId);
		return cart;
	}
	
	@Override
	public Cart find(String cartId) {
		return sessions.get(cartId);
	}
	
	@Override
	public void delete(String cartId) {
		sessions.remove(cartId);
	}
	
}
