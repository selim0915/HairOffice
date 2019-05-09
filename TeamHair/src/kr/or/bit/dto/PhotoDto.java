package kr.or.bit.dto;

import java.util.Date;

public class PhotoDto {
	private int photoId;
	private String fileName;
	private String description;
	private Date createDate;
	private Date updateDate;
	private String userId;
	
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
	@Override
	public String toString() {
		return "PhotoDto [photoId=" + photoId + ", fileName=" + fileName + ", description=" + description
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", userId=" + userId + "]";
	}
}
