package com.pbboard.Bh.shop.service;

import com.pbboard.Bh.shop.domain.ShopPageMaker;
import com.pbboard.domain.ShopVO;

import java.util.List;

public interface ShopService {

    public List<ShopVO> findMainCat();  //메인카테고리

    public List<ShopVO> findSubCat(String mainCat); //서브 카테고리

    public List<ShopVO> findColor();    //옷의 색상

    public List<ShopVO> findSize();     //옷의 사이즈

    public List<ShopVO> findProduct(ShopPageMaker shopPageMaker);  //제품 정보

    public List<ShopVO> findDiscount(); //제품 할인

    public List<ShopVO> findNewProduct();//신 제품 확인

    public List<ShopVO> findOutOfStock();//품절 확인

    public int productCount();

    public String[][] subCategory();    //각 카테고리 모아서 가져가기 위해 사용
}
