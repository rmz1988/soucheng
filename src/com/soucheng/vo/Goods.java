package com.soucheng.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 商品
 *
 * @author lichen
 */
public class Goods implements Serializable {

	private static final long serialVersionUID = 3178175063304397618L;
	private int id;
	private String name;
	private String price;
	//库存量
	private String stockBalance;
	//已售量
	private String sellCount;
	private Date sellDate;

	private int imageSource;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStockBalance() {
		return stockBalance;
	}

	public void setStockBalance(String stockBalance) {
		this.stockBalance = stockBalance;
	}

	public String getSellCount() {
		return sellCount;
	}

	public void setSellCount(String sellCount) {
		this.sellCount = sellCount;
	}

	public int getImageSource() {
		return imageSource;
	}

	public void setImageSource(int imageSource) {
		this.imageSource = imageSource;
	}

	public Date getSellDate() {
		return sellDate != null ? (Date) sellDate.clone() : null;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate != null ? (Date) sellDate.clone() : null;
	}

	public String getSellDateString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getSellDate());
	}
}
