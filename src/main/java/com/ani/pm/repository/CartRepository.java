package com.ani.pm.repository;

import com.ani.pm.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findByClientId(String clientId);
}
