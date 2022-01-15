package com.example.demo.src.cart.model;


import com.example.demo.src.product.model.ProductMiniInfoRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartFromUserRes {
    // 냉장(cold) 냉동(frozen) 상온(roomTemp)
    // 냉장/스티로폼 냉동/스티로폼 상온/종이포장
    private int cartIdx;
    private List<ProductMiniInfoRes> coldProducts;
    private List<ProductMiniInfoRes> frozenProducts;
    private List<ProductMiniInfoRes> roomTempProducts;
    private String address;
    private String subAddress;
}
