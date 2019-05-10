package kr.or.bit.dto;

import java.util.Date;

public class ReservationDto {
	private int photoId;
	private int reserveId;
	private Date startDateTime;
	private Date endDateTime;
	private int spaceId;
	private Date createDate;
	private Date updateDate;
	private String userId;
	private String cancelYn;
	
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public int getReserveId() {
		return reserveId;
	}
	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
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
	public int getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
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
	public String getCancelYn() {
		return cancelYn;
	}
	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}
	
	@Override
	public String toString() {
		return "ReservationDto [photoId=" + photoId + ", reserveId=" + reserveId + ", startDateTime=" + startDateTime
				+ ", endDateTime=" + endDateTime + ", spaceId=" + spaceId + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", userId=" + userId + ", cancelYn=" + cancelYn + "]";
	}
}
