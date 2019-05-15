package kr.or.bit.dto;

public class SearchSpaceDto {
	private String branchName;
	private int spaceId;
	private String spaceName;
	private String code;
	private String codeName;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	@Override
	public String toString() {
		return "SearchSpaceDto [branchName=" + branchName + ", spaceId=" + spaceId + ", spaceName=" + spaceName
				+ ", code=" + code + ", codeName=" + codeName + "]";
	}

}
