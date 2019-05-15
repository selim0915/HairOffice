package kr.or.bit.dto;

public class FollowingFollowerListDto {
	private String followerId;
	private String followingId;
	private String photoName;
	private String follower_Yn;

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

	public String getFollower_Yn() {
		return follower_Yn;
	}

	public void setFollower_Yn(String follower_Yn) {
		this.follower_Yn = follower_Yn;
	}

	@Override
	public String toString() {
		return "FollowingFollowerListDto [followerId=" + followerId + ", followingId=" + followingId + ", photoName="
				+ photoName + ", follower_Yn=" + follower_Yn + "]";
	}

}
