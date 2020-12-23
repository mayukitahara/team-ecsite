package jp.co.internous.eagle.model.domain.dto;

import java.sql.Timestamp;

public class PurchaseHistoryDto {
	
//購入履歴の出力内容
	private Timestamp purchasedAt;
	private String productName;
	private int price;
	private int productCount;
	private String familyName;
	private String firstName;
	private String address;
	
	
	//getter,setter
	public Timestamp getPurchasedAt() {
		return purchasedAt;
	}
	
	public void setPurchasedAt(Timestamp purchasedAt) {
		this.purchasedAt = purchasedAt;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getProductCount() {
		return productCount;
	}
	
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	public String getFamilyName( ) {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

}
