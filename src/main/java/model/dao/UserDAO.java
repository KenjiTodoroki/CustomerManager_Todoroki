package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserBean;

public class UserDAO {
	/**
	 * 入力されたuserId、passwordがデータベースに登録されているかチェック
	 * @param id ユーザID
	 * @param password パスワード
	 * @return UserBean ユーザ情報
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserBean checkLogin(String userId, String password) throws ClassNotFoundException, SQLException {
		// ユーザ情報を格納する変数
		UserBean user = null;

		// プレースホルダー2つのSQL文
		String sql = "SELECT * FROM m_user WHERE user_id = ? AND password = ?";

		// try-with-resourcesを使用し、データベース接続確立とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダに値をセット
			pstmt.setString(1, userId);
			pstmt.setString(2, password);

			// SQL文の実行
			ResultSet res = pstmt.executeQuery();

			// id、passwordが一致する情報がデータベースにあれば、UserBeanをインスタンス化し、各カラムの値をインスタンスにセット
			if (res.next()) {
				user = new UserBean();
				user.setUserId(res.getString("user_id"));
				user.setPassword(res.getString("password"));
				user.setAuthorityCode(res.getString("authority_code"));
			}
		}
		return user;
	}
	
	// 一覧表示
	public List<UserBean> getAllUsers() throws ClassNotFoundException, SQLException {
		// 空のリストを作成
		List<UserBean> users = new ArrayList<>();
		// SQL文を変数に格納
		String sql = "SELECT user_id FROM m_user";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// SQL文を実行
			ResultSet res = pstmt.executeQuery();
			// SQL文が一致する所まで繰り返す
			while (res.next()) {
				// AreaBeanをインスタンス化
				UserBean user = new UserBean();
				// 各カラムの値をセット
				user.setUserId(res.getString("user_id"));
				// リストに加える
				users.add(user);
			}
		}
		return users;
	}
}