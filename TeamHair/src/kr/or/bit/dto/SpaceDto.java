package kr.or.bit.dto;

public class SpaceDto {
	private int spaceId;
	private String spaceName;
	private String spaceType;
	private int minNumber;
	private int maxNumber;
	private int branchId;

	public int getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getSpaceType() {
		return spaceType;
	}

	public void setSpaceType(String spaceType) {
		this.spaceType = spaceType;
	}

	public int getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "SpaceDto [spaceId=" + spaceId + ", spaceName=" + spaceName + ", spaceType=" + spaceType + ", minNumber="
				+ minNumber + ", maxNumber=" + maxNumber + ", branchId=" + branchId + "]";
	}

}
