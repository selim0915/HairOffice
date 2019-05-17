package kr.or.bit.dto;

public class FollowingFollowerListDto {
	private String followerId;
	private String followingId;
	private String photoName;
	private String introDuction;
	private String following_Yn;

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getFollowing_Yn() {
		return following_Yn;
	}

	public void setFollowing_Yn(String following_Yn) {
		this.following_Yn = following_Yn;
	}
	
	public String getIntroDuction() {
		return introDuction;
	}

	public void setIntroDuction(String introDuction) {
		this.introDuction = introDuction;
	}

	@Override
	public String toString() {
		return "FollowingFollowerListDto [followerId=" + followerId + ", followingId=" + followingId + ", photoName="
				+ photoName + ", following_Yn=" + following_Yn + "]";
	}

}
