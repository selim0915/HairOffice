package kr.or.bit.dto;

import java.util.Date;

public class ReservationDto {
	private int reserveId;
	private String serviceType;
	private Date startDateTime;
	private Date endDateTime;
	private String cancelYn;
	private Date createDate;
	private Date updateDate;
	private String userId;
	private int spaceId;
	private int photoId;

	public int getReserveId() {
		return reserveId;
	}

	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
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

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	@Override
	public String toString() {
		return "ReservationDto [reserveId=" + reserveId + ", serviceType=" + serviceType + ", startDateTime="
				+ startDateTime + ", endDateTime=" + endDateTime + ", cancelYn=" + cancelYn + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", userId=" + userId + ", spaceId=" + spaceId
				+ ", photoId=" + photoId + "]";
	}

}
