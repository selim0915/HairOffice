package kr.or.bit.dto;

public class RentalHistoryDto {
	private String userId;
	private int spaceId;
	private int rentId;
	private String baseDate;
	private double rentalRevenue;
	private double discount;
	private String payMethod;

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

	public double getRentalRevenue() {
		return rentalRevenue;
	}

	public void setRentalRevenue(double rentalRevenue) {
		this.rentalRevenue = rentalRevenue;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	@Override
	public String toString() {
		return "RentalHistory [userId=" + userId + ", spaceId=" + spaceId + ", rentId=" + rentId + ", baseDate="
				+ baseDate + ", rentalRevenue=" + rentalRevenue + ", discount=" + discount + ", payMethod=" + payMethod
				+ "]";
	}

}
