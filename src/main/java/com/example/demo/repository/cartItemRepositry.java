package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;

@Repository
public interface cartItemRepositry extends JpaRepository<CartItem, Long> {
	List<CartItem> findByCartId(Long cartId);

}

