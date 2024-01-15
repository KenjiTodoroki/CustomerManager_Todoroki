package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.AuthorityBean;

public class AuthorityDAO {
	// 一覧表示
	public List<AuthorityBean> getAllAuthorities() throws ClassNotFoundException, SQLException {
		// 空のリストを作成
		List<AuthorityBean> authorities = new ArrayList<>();
		// SQL文を変数に格納
		String sql = "SELECT * FROM m_authority";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// SQL文を実行
			ResultSet res = pstmt.executeQuery();
			// SQL文が一致する所まで繰り返す
			while (res.next()) {
				// AreaBeanをインスタンス化
				AuthorityBean authority = new AuthorityBean();
				// 各カラムの値をセット
				authority.setAuthorityCode(res.getString("authority_code"));
				authority.setAuthorityName(res.getString("authority_name"));
				// リストに加える
				authorities.add(authority);
			}
		}
		return authorities;
	}
	
	//権限編集
	public void editAuthority(String userId, String authorityCode) throws ClassNotFoundException, SQLException {
		// SQL文
		String sql = "UPDATE\n"
				+ "  m_user\n"
				+ "SET\n"
				+ "  authority_code = ?\n"
				+ "WHERE\n"
				+ "  user_id = ?";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダーに値をセット
			pstmt.setString(1, authorityCode);
			pstmt.setString(2, userId);
			// SQL文の実行
			pstmt.executeUpdate();
		}
	}
}
