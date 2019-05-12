package kr.or.bit.dto;

public class FollowerDto {
	private String followerId;
	private String userId;

	public String getFollowerId() {
		return followerId;
	}
	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "FollowerDto [followerId=" + followerId + ", userId=" + userId + "]";
	}
}
