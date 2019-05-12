package kr.or.bit.dto;

import java.util.Date;

public class RentContractDto {
	private String userId;
	private int spaceId;
	private int rentId;
	private Date startDate;
	private Date endDate;
	private double deposit;
	private double monthlyrental;
	private double discountAmount;
	private String payMethod;
	private Date createDate;
	private Date updateDate;

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

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getMonthlyrental() {
		return monthlyrental;
	}

	public void setMonthlyrental(double monthlyrental) {
		this.monthlyrental = monthlyrental;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
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

	@Override
	public String toString() {
		return "RentContractDto [userId=" + userId + ", spaceId=" + spaceId + ", rentId=" + rentId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", deposit=" + deposit + ", monthlyrental=" + monthlyrental
				+ ", discountAmount=" + discountAmount + ", payMethod=" + payMethod + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}

}
