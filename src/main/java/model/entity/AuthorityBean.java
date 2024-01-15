package model.entity;

import java.io.Serializable;

public class AuthorityBean implements Serializable {
	
	/**
	 * 権限コード
	 */
	private String authorityCode;
	
	/**
	 * 権限コード
	 */
	private String authorityName;

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

	/**
	 * @return authorityName
	 */
	public String getAuthorityName() {
		return authorityName;
	}

	/**
	 * @param authorityName セットする authorityName
	 */
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	
}
