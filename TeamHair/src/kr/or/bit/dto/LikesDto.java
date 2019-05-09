package kr.or.bit.dto;

public class LikesDto {
	private int photoId;
	private String userId;
	private String likeYn;
	private String wasUser;
	
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLikeYn() {
		return likeYn;
	}
	public void setLikeYn(String likeYn) {
		this.likeYn = likeYn;
	}
	public String getWasUser() {
		return wasUser;
	}
	public void setWasUser(String wasUser) {
		this.wasUser = wasUser;
	}
	
	@Override
	public String toString() {
		return "LikesDto [photoId=" + photoId + ", userId=" + userId + ", likeYn=" + likeYn + ", wasUser=" + wasUser
				+ "]";
	}
}
