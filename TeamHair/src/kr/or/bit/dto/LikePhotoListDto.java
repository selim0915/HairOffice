package kr.or.bit.dto;

public class LikePhotoListDto {
	
	private int likecount;
	private int photoid;
	private String filename;
	private String userid;
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getPhotoid() {
		return photoid;
	}
	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "LikePhotoListDto [likecount=" + likecount + ", photoid=" + photoid + ", filename=" + filename
				+ ", userid=" + userid + "]";
	}
}
