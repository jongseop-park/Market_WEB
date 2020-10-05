package com.pbboard.Bh.shop.mapper;

import com.pbboard.domain.ShopVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopMapper {

    public List<ShopVO> findMainCat();  //메인 카테고리

    public List<ShopVO> findSubCat(@Param("MAINCAT") String mainCat);   //서브 카테고리

    public List<ShopVO> findColor();    //제품 색상표??

    public List<ShopVO> findSize();     //제품 사이즈표??
}
