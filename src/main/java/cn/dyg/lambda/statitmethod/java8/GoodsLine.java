package cn.dyg.lambda.statitmethod.java8;

import lombok.Data;

/**
 * GoodsLine 类是
 *
 * @author dongyinggang
 * @date 2020-10-12 11:19
 **/
@Data
public class GoodsLine implements HasGoodsUuid {
    private String goodsUuid;
    private String goodsCode;

}