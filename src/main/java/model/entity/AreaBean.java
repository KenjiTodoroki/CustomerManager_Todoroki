package model.entity;

import java.io.Serializable;

public class AreaBean implements Serializable {
	
	/**
	 * 地区コード
	 */
	private String areaCode;
	/**
	 * 地区名
	 */
	private String areaName;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public AreaBean() {

	}
	
	/**
	 * @return areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}
	
	/**
	 * @param areaCode
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	/**
	 * @return areaName
	 */
	public String getAreaName() {
		return areaName;
	}
	
	/**
	 * @param areaName
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
