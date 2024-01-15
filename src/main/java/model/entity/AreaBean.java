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
	
	public String getAreaCode() {
		return areaCode;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
