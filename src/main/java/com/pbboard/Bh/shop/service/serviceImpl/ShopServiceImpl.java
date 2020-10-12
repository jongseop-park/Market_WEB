package com.pbboard.Bh.shop.service.serviceImpl;

import com.pbboard.Bh.shop.domain.ShopPageMaker;
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

    //메인 카테고리 가져오기
    public List<ShopVO> findMainCat(){ return shopMapper.findMainCat(); }

    //메인 카테고리 별 서브 카테고리 가져오기
    public List<ShopVO> findSubCat(String mainCat){ return shopMapper.findSubCat(mainCat); }

    //색상 상세 검색을 위해서
    public List<ShopVO> findColor(){ return shopMapper.findColor(); }

    //사이즈 상세 검색을 위해서
    public List<ShopVO> findSize(){ return shopMapper.findSize(); }

    //제품 정보
    public List<ShopVO> findProduct(ShopPageMaker shopPageMaker){ return shopMapper.findProduct(shopPageMaker); }

    //제품 할인
    public List<ShopVO> findDiscount(){ return shopMapper.findDiscount(); }

    //신 제품 확인
    public List<ShopVO> findNewProduct(){ return shopMapper.findNewProduct(); }

    //품절 확인
    public List<ShopVO> findOutOfStock(){ return shopMapper.findOutOfStock(); }

    //등록된 제품 수
    public int productCount(ShopPageMaker shopPageMaker){ return shopMapper.productCount(shopPageMaker); }

    //제품 정보
    public ShopVO productDetail(Long productSeq){ return shopMapper.productDetail(productSeq); }

    //제품 리뷰 총 개수
    public int reviewCount(Long productSeq){ return shopMapper.reviewCount(productSeq); }

    //제품 별점
    public int reviewStar(Long productSeq){ return shopMapper.reviewStar(productSeq); }

    //서브 카테고리 모음
    public String[][] subCategory(){
        List<ShopVO> mainCat = findMainCat();
        List<ShopVO> subCat;

        int mainCatSize = mainCat.size();

        String[][] subCategory = new String[mainCatSize][];

        //각각 메인카테고리를 사용하여 해당하는 서브 카테고리를 가져온 다음 2차원 배열에 대입
        for(int x = 0; x < mainCatSize;x++){

            subCat = findSubCat(mainCat.get(x).getSortcodeMain());
            subCategory[x] = new String[subCat.size()];

            for(int y = 0; y < subCat.size(); y++ ){
                subCategory[x][y] = subCat.get(y).getSortcodeSub();
            }
        }

        return subCategory;
    }

    //현재 화면에 나오는 제품 seq 모음
    public String productList(List<ShopVO> productInfo){
        String seqList = "";

        for(int x = 0; x < productInfo.size(); x++){
            seqList += productInfo.get(x).getProductSeq();
            if(x != productInfo.size() - 1){
                seqList += ',';
            }
        }

        return seqList;
    }

}
