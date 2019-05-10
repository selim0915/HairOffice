package kr.or.bit.dto;

import java.sql.Date;

public class RentContractDto {
	private String userId;
	private int spaceId;
	private Date startDate;
	private Date endDate;
	private int rentId;
	private int monthlyRental;
	private int discountAmount;
	private Date createDate;
	private Date updateDate;
	private int deposit;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getRentId() {
		return rentId;
	}
	public void setRentId(int rentId) {
		this.rentId = rentId;
	}
	public int getMonthlyRental() {
		return monthlyRental;
	}
	public void setMonthlyRental(int monthlyRental) {
		this.monthlyRental = monthlyRental;
	}
	public int getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	@Override
	public String toString() {
		return "RentContractDto [userId=" + userId + ", spaceId=" + spaceId + ", startDate=" + startDate + ", endDate="
				+ endDate + ", rentId=" + rentId + ", monthlyRental=" + monthlyRental + ", discountAmount="
				+ discountAmount + ", createDate=" + createDate + ", updateDate=" + updateDate + ", deposit=" + deposit
				+ ", payMethod=" + payMethod + "]";
	}
}
