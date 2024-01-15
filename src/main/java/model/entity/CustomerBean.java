package model.entity;

import java.io.Serializable;

public class CustomerBean implements Serializable {

	/**
	 * 顧客ID
	 */
	private int customerId;

	/**
	 * 顧客名
	 */
	private String customerName;

	/**
	 * 顧客名読み
	 */
	private String customerNameKana;

	/**
	 * 郵便番号
	 */
	private String postCode;

	/**
	 * 地区コード
	 */
	private String areaCode;
	
	/**
	 * 地区名
	 */
//	private String areaName;

	/**
	 * 性別
	 */
	private String gender;

	/**
	 * 生年月日
	 */
	private String birthday;

	/**
	 * 電話番号
	 */
	private String phoneNumber;

	/**
	 * 登録日時
	 */
	private String insertDatetime;

	/**
	 * 更新日時
	 */
	private String updateDatetime;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public CustomerBean() {
		
	}
	
	/**
	 * @return customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	
	/**
	 * @param customerId
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return customerNameKana
	 */
	public String getCustomerNameKana() {
		return customerNameKana;
	}
	
	/**
	 * @param customerNameKana
	 */
	public void setCustomerNameKana(String customerNameKana) {
		this.customerNameKana = customerNameKana;
	}
	
	/**
	 * @return postCode
	 */
	public String getPostCode() {
		return postCode;
	}
	
	/**
	 * @param postCode
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
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
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	
	/**
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return insertDatetime
	 */
	public String getInsertDatetime() {
		return insertDatetime;
	}
	
	/**
	 * @param insertDatetime
	 */
	public void setInsertDatetime(String insertDatetime) {
		this.insertDatetime = insertDatetime;
	}
	
	/**
	 * @return updateDatetime
	 */
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	
	/**
	 * @param updateDatetime
	 */
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
}
