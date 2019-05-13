package kr.or.bit.dto;

import java.util.Date;

public class CommentsDto {
	private int commentId;
	private String comments;
	private int photoId;
	private Date createDate;
	private Date updateDate;
	private String wasUser;
	private String userId;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
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
	public String getWasUser() {
		return wasUser;
	}
	public void setWasUser(String wasUser) {
		this.wasUser = wasUser;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "CommentsDto [commentId=" + commentId + ", comments=" + comments + ", photoId=" + photoId
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", wasUser=" + wasUser + ", userId="
				+ userId + "]";
	}
}
