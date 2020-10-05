package com.pbboard.Bh.shop.service.serviceImpl;

import com.pbboard.domain.ShopVO;
import com.pbboard.Bh.shop.mapper.ShopMapper;
import com.pbboard.Bh.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;

    public List<ShopVO> findMainCat(){ return shopMapper.findMainCat(); }   //메인 카테고리 가져오기

    public List<ShopVO> findSubCat(String mainCat){ return shopMapper.findSubCat(mainCat); }    //메인 카테고리 별 서브 카테고리 가져오기

    public List<ShopVO> findColor(){ return shopMapper.findColor(); }   //색상 상세 검색을 위해서

    public List<ShopVO> findSize(){ return shopMapper.findSize(); }     //사이즈 상세 검색을 위해서

    public String[][] subCategory(){
        List<ShopVO> mainCat = findMainCat();
        List<ShopVO> subCat;

        int mainCatSize = mainCat.size();

        String[][] subCategory = new String[mainCatSize][];

        for(int x = 0; x < mainCatSize;x++){

            subCat = findSubCat(mainCat.get(x).getSortcodeMain());
            subCategory[x] = new String[subCat.size()];

            for(int y = 0; y < subCat.size(); y++ ){

                subCategory[x][y] = subCat.get(y).getSortcodeSub();

            }
        }

        return subCategory;
    }
}
