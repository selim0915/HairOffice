package kr.or.bit.dto;

public class LikeListDto {
	private int likeCount;
	private int photoId;
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	
	@Override
	public String toString() {
		return "LikeListDto [likeCount=" + likeCount + ", photoId=" + photoId + "]";
	}
}
