package com.wang.bus.vo;


import com.wang.bus.domain.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsVo extends Goods {

	/*
	 *  
	 */
	private static final long serialVersionUID = 1L;

	private Integer page = 1;
	private Integer limit = 10;

}
