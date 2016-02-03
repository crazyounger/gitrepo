package com.certusnet.icity.mobile.remote;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * <h1>区域信息</h1>
 * 
 * <pre>
 * 区域信息分为四级，每一级包含区域ID和区域名称，第一级即{@link RegionInfo#getOneLevelId()}必存在，
 * 其他级别不一定存在；
 * 但区域包含的站点ID {@link #getGroupId()}也必存在，不能为null。
 * 
 * 
 * </pre>
 * 
 * @author lig
 * @version 1.0
 * @since 1.0 2012-12-20
 */
public class RegionInfo implements Parcelable {
	private String levelOneId;
	private String levelOneName;
	private String levelTwoId;
	private String levelTwoName;
	private String levelThreeId;
	private String levelThreeName;
	private String levelFourId;
	private String levelFourName;
	private String levelId;

	/**
	 * @return 一级ID
	 */
	public String getOneLevelId() {
		return levelOneId;
	}

	public void setOneLevelId(String levelOneId) {
		this.levelOneId = levelOneId;
	}

	/**
	 * @return 一级名称
	 */
	public String getOneLevelName() {
		return levelOneName;
	}

	public void setOneLevelName(String levelOneName) {
		this.levelOneName = levelOneName;
	}

	/**
	 * @return 二级ID
	 */
	public String getTwoLevelId() {
		return levelTwoId;
	}

	public void setTwoLevelId(String levelTwoId) {
		this.levelTwoId = levelTwoId;
	}

	/**
	 * @return 二级名称
	 */
	public String getTwoLevelName() {
		return levelTwoName;
	}

	public void setTwoLevelName(String levelTwoName) {
		this.levelTwoName = levelTwoName;
	}

	/**
	 * @return 三级ID
	 */
	public String getThreeLevelId() {
		return levelThreeId;
	}

	public void setThreeLevelId(String levelThreeId) {
		this.levelThreeId = levelThreeId;
	}

	/**
	 * @return 三级名称
	 */
	public String getThreeLevelName() {
		return levelThreeName;
	}

	public void setThreeLevelName(String levelThreeName) {
		this.levelThreeName = levelThreeName;
	}

	/**
	 * @return 四级Id
	 */
	public String getFourLevelId() {
		return levelFourId;
	}

	public void setFourLevelId(String levelFourId) {
		this.levelFourId = levelFourId;
	}

	/**
	 * @return 四级名称
	 */
	public String getFourLevelName() {
		return levelFourName;
	}

	public void setFourLevelName(String levelFourName) {
		this.levelFourName = levelFourName;
	}

	public String getValidLastID() {
		if (!TextUtils.isEmpty(levelFourId))
			return levelFourId;
		if (!TextUtils.isEmpty(levelThreeId))
			return levelThreeId;
		if (!TextUtils.isEmpty(levelTwoId))
			return levelTwoId;
		return levelOneId;
	}

	public String getValidLastName() {
		if (!TextUtils.isEmpty(levelFourId))
			return levelFourName;
		if (!TextUtils.isEmpty(levelThreeId))
			return levelThreeName;
		if (!TextUtils.isEmpty(levelTwoId))
			return levelTwoName;
		return levelOneName;
	}

	/**
	 * 站点ID ,必存在不可能为null
	 * 
	 * @return
	 */
	public String getGroupId() {
		return levelId;
	}

	public void setGroupId(String groupId) {
		this.levelId = groupId;
	}

	public RegionInfo() {
	}

	public RegionInfo(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		this.levelId = in.readString();
		this.levelOneId = in.readString();
		this.levelOneName = in.readString();
		this.levelTwoId = in.readString();
		this.levelTwoName = in.readString();
		this.levelThreeId = in.readString();
		this.levelThreeName = in.readString();
		this.levelFourId = in.readString();
		this.levelFourName = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.levelId);
		dest.writeString(this.levelOneId);
		dest.writeString(this.levelOneName);
		dest.writeString(this.levelTwoId);
		dest.writeString(this.levelTwoName);
		dest.writeString(this.levelThreeId);
		dest.writeString(this.levelThreeName);
		dest.writeString(this.levelFourId);
		dest.writeString(this.levelFourName);
	}

	public static final Parcelable.Creator<RegionInfo> CREATOR = new Parcelable.Creator<RegionInfo>() {
		public RegionInfo createFromParcel(Parcel in) {
			return new RegionInfo(in);
		}

		public RegionInfo[] newArray(int size) {
			return new RegionInfo[size];
		}
	};
}
