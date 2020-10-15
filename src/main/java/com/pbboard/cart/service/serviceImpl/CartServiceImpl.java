package com.pbboard.cart.service.serviceImpl;


import com.pbboard.cart.domain.CartDTO;
import com.pbboard.cart.domain.CartVO;
import com.pbboard.cart.mapper.CartMapper;
import com.pbboard.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    final
    CartMapper cartMapper;

    @Autowired
    public CartServiceImpl(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @Override
    public void save(CartDTO cartDTO) {
        cartMapper.save(cartDTO);
    }

    @Override
    public List<CartVO> list(String id) {
        return cartMapper.list(id);
    }

    @Override
    public void delete(int seq) {
        cartMapper.delete(seq);
    }

    @Override
    public String totalPrice(String id) {
        return cartMapper.totalPrice(id);
    }
}
