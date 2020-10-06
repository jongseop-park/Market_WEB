package com.pbboard.domain;

import lombok.Getter;
import lombok.Setter;

public class ShopVO {

    /** 분류 코드 seq **/
    @Getter @Setter
    private Long sortcodeSeq;

    /** 메인 분류 코드 **/
    @Getter @Setter
    private String sortcodeMain;

    /** 서브 분류 코드 **/
    @Getter @Setter
    private String sortcodeSub;

    /** 제품 색상 **/
    @Getter @Setter
    private String colorValue;

    /** 제품 사이즈 **/
    @Getter @Setter
    private String productsizeValue;

    /** 제품 seq **/
    @Getter @Setter
    private Long productSeq;

    /** 제품 명 **/
    @Getter @Setter
    private String productName;

    /** 제품 가격 **/
    @Getter @Setter
    private String productPrice;

    /** 제품 총 개수 **/
    @Getter @Setter
    private int totQty;
}
