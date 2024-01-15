package model.entity;

import java.io.Serializable;

public class UserBean implements Serializable {
	/**
	 * ユーザID
	 */
	private String userId;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * 権限コード
	 */
	private String authorityCode;

	/**
	 * デフォルトコンストラクタ
	 */
	public UserBean() {

	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return authorityCode
	 */
	public String getAuthorityCode() {
		return authorityCode;
	}

	/**
	 * @param authorityCode セットする authorityCode
	 */
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

}