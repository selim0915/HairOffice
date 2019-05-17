package kr.or.bit.dto;

public class ChartUserDto {
	String userId;
	int likeCount;
	
	public String getUserid() {
		return userId;
	}
	public void setUserid(String userid) {
		this.userId = userid;
	}
	public int getLikecount() {
		return likeCount;
	}
	public void setLikecount(int likecount) {
		this.likeCount = likecount;
	}
	
	@Override
	public String toString() {
		return "ChartUser [userid=" + userId + ", likecount=" + likeCount + "]";
	}
}
