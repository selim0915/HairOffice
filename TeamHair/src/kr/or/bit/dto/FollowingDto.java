 package kr.or.bit.dto;

public class FollowingDto {
	private String followingId;
	private String userId;
	
	public String getFollowingId() {
		return followingId;
	}
	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "FollowingDto [followingId=" + followingId + ", userId=" + userId + "]";
	}
}
