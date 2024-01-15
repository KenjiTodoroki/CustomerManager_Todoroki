package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.AreaBean;

public class AreaDAO {
	// 一覧表示
	public List<AreaBean> getAllAreas() throws ClassNotFoundException, SQLException {
		// 空のリストを作成
		List<AreaBean> areas = new ArrayList<>();
		// SQL文を変数に格納
		String sql = "SELECT * FROM m_area ORDER BY area_code";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// SQL文を実行
			ResultSet res = pstmt.executeQuery();
			// SQL文が一致する所まで繰り返す
			while (res.next()) {
				// AreaBeanをインスタンス化
				AreaBean area = new AreaBean();
				// 各カラムの値をセット
				area.setAreaCode(res.getString("area_code"));
				area.setAreaName(res.getString("area_name"));
				// リストに加える
				areas.add(area);
			}
		}
		return areas;
	}

	// 1件の地区名取得
	public List<AreaBean> getAreaName(int customerId) throws ClassNotFoundException, SQLException {
		// 空のリスト作成
		List<AreaBean> areaDetail = new ArrayList<>();
		// SQL文を変数に格納
		String sql = "SELECT\n"
				+ "  area_name\n"
				+ "FROM\n"
				+ "  m_area a\n"
				+ "  LEFT JOIN m_customer c ON a.area_code = c.area_code\n"
				+ "WHERE\n"
				+ "  c.customer_id = ?";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダーに値をセット
			pstmt.setInt(1, customerId);
			// SQL文の実行
			ResultSet res = pstmt.executeQuery();
			// SQL文が一致する所まで繰り返す
			while (res.next()) {
				// CustomerBeanをインスタンス化
				AreaBean area = new AreaBean();
				// 地区名のみ取得しセット
				area.setAreaName(res.getString("area_name"));
				// リストに加える
				areaDetail.add(area);
			}
		}
		return areaDetail;
	}
}
