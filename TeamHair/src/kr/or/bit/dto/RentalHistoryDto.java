package kr.or.bit.dto;

public class RentalHistoryDto {
	private String userId;
	private int spaceId;
	private int rentId;
	private String baseDate;
	private int rentalRevenue;
	private int discount;
	private String patMethod;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}
	public int getRentId() {
		return rentId;
	}
	public void setRentId(int rentId) {
		this.rentId = rentId;
	}
	public String getBaseDate() {
		return baseDate;
	}
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}
	public int getRentalRevenue() {
		return rentalRevenue;
	}
	public void setRentalRevenue(int rentalRevenue) {
		this.rentalRevenue = rentalRevenue;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getPatMethod() {
		return patMethod;
	}
	public void setPatMethod(String patMethod) {
		this.patMethod = patMethod;
	}
	
	@Override
	public String toString() {
		return "RentalHistoryDto [userId=" + userId + ", spaceId=" + spaceId + ", rentId=" + rentId + ", baseDate="
				+ baseDate + ", rentalRevenue=" + rentalRevenue + ", discount=" + discount + ", patMethod=" + patMethod
				+ "]";
	}
}
