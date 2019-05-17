package kr.or.bit.dto;

public class ChartPhotoDto {
	int likeCount;
	int photoId;
	
	public int getLikecount() {
		return likeCount;
	}
	public void setLikecount(int likecount) {
		this.likeCount = likecount;
	}
	public int getPhotoid() {
		return photoId;
	}
	public void setPhotoid(int photoid) {
		this.photoId = photoid;
	}
	
	@Override
	public String toString() {
		return "ChartPhotoDto [likecount=" + likeCount + ", photoid=" + photoId + "]";
	}
	
}
