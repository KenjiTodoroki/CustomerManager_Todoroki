package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CustomerBean;

public class CustomerDAO {

	// 一覧表示
	public List<CustomerBean> getAllCustomer() throws ClassNotFoundException, SQLException {
		// SQL文を変数に格納する
		String sql = "SELECT\n"
				+ "  customer_id,\n"
				+ "  customer_name,\n"
				+ "  customer_name_kana,\n"
				+ "  gender\n"
				+ "FROM\n"
				+ "  m_customer";
		// 空のリストを作成
		List<CustomerBean> customerList = new ArrayList<>();
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// SQL文の実行
			ResultSet res = pstmt.executeQuery();
			// SQL文が一致しなくなる所まで繰り返し実行する
			while (res.next()) {
				// CustomerBeanをインスタンス化して各カラムを取得してセットする
				CustomerBean customer = new CustomerBean();
				customer.setCustomerId(res.getInt("customer_id"));
				customer.setCustomerName(res.getString("customer_name"));
				customer.setCustomerNameKana(res.getString("customer_name_kana"));
				customer.setGender(res.getString("gender"));
				// 各カラムをリストに追加する
				customerList.add(customer);
			}
		}
		// リストを返す
		return customerList;
	}

	// 検索
	public List<CustomerBean> searchCustomer(String searchWord) throws ClassNotFoundException, SQLException {
		// 空のリストを作成
		List<CustomerBean> customerList = new ArrayList<>();
		// SQL文
		String sql = "SELECT\n"
				+ "  customer_id,\n"
				+ "  customer_name,\n"
				+ "  customer_name_kana,\n"
				+ "  gender\n"
				+ "FROM\n"
				+ "  m_customer c\n"
				+ "WHERE\n"
				+ "  c.customer_name LIKE ?";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダーに値セット(検索する為、任意文字列指示子「%」を使う)
			pstmt.setString(1, "%" + searchWord + "%");
			// SQL文を実行
			ResultSet res = pstmt.executeQuery();
			// CustomerBeanをインスタンス化して、検索に該当する値があればリストに加える
			while (res.next()) {
				CustomerBean customer = new CustomerBean();
				customer.setCustomerId(res.getInt("customer_id"));
				customer.setCustomerName(res.getString("customer_name"));
				customer.setCustomerNameKana(res.getString("customer_name_kana"));
				customer.setGender(res.getString("gender"));
				customerList.add(customer);
			}
		}
		return customerList;
	}

	// 登録
	public void registerCustomer(String customerName, String customerNameKana, String postCode, String areaCode,
			String gender, String birthday, String phoneNumber) throws ClassNotFoundException, SQLException {
		// SQL文
		String sql = "INSERT INTO\n"
				+ "  m_customer(\n"
				+ "    customer_name,\n"
				+ "    customer_name_kana,\n"
				+ "    post_code,\n"
				+ "    area_code,\n"
				+ "    gender,\n"
				+ "    birthday,\n"
				+ "    phone_number\n"
				+ "  )\n"
				+ "VALUES\n"
				+ "(?, ?, ?, ?, ?, ?, ?)";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダーに値をセット
			pstmt.setString(1, customerName);
			pstmt.setString(2, customerNameKana);
			pstmt.setString(3, postCode);
			pstmt.setString(4, areaCode);
			pstmt.setString(5, gender);
			pstmt.setString(6, birthday);
			pstmt.setString(7, phoneNumber);
			// SQL文の実行
			pstmt.executeUpdate();
		}
	}

	// 詳細
	public List<CustomerBean> getCustomerDetail(int customerId) throws ClassNotFoundException, SQLException {
		// 空のリスト作成
		List<CustomerBean> customerDetail = new ArrayList<>();
		// SQL文
		String sql = "SELECT\n"
				+ "  customer_id,\n"
				+ "  customer_name,\n"
				+ "  customer_name_kana,\n"
				+ "  post_code,\n"
				+ "  area_code,\n"
				+ "  gender,\n"
				+ "  birthday,\n"
				+ "  phone_number,\n"
				+ "  insert_datetime,\n"
				+ "  update_datetime\n"
				+ "FROM\n"
				+ "  m_customer\n"
				+ "WHERE\n"
				+ "  customer_id = ?";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダーに値をセット
			pstmt.setInt(1, customerId);
			// SQL文の実行
			ResultSet res = pstmt.executeQuery();
			// SQL文が一致する所まで繰り返す
			while (res.next()) {
				// CustomerBeanをインスタンス化して各カラムを取得してセットする
				CustomerBean customer = new CustomerBean();
				customer.setCustomerId(res.getInt("customer_id"));
				customer.setCustomerName(res.getString("customer_name"));
				customer.setCustomerNameKana(res.getString("customer_name_kana"));
				customer.setPostCode(res.getString("post_code"));
				customer.setAreaCode(res.getString("area_code"));
				customer.setGender(res.getString("gender"));
				customer.setBirthday(res.getString("birthday"));
				customer.setPhoneNumber(res.getString("phone_number"));
				customer.setInsertDatetime(res.getString("insert_datetime"));
				customer.setUpdateDatetime(res.getString("update_datetime"));
				// リストに加える
				customerDetail.add(customer);
			}
		}
		return customerDetail;
	}

	// 編集
	public void editCustomer(int customerId, String customerName, String customerNameKana, String postCode,
			String areaCode,
			String gender, String phoneNumber) throws ClassNotFoundException, SQLException {
		// SQL文
		String sql = "UPDATE\n"
				+ "  m_customer\n"
				+ "SET\n"
				+ "  customer_name = ?,\n"
				+ "  customer_name_kana = ?,\n"
				+ "  post_code = ?,\n"
				+ "  area_code = ?,\n"
				+ "  gender = ?,\n"
				+ "  phone_number = ?\n"
				+ "WHERE\n"
				+ "  customer_id = ?";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダーに値をセット
			pstmt.setString(1, customerName);
			pstmt.setString(2, customerNameKana);
			pstmt.setString(3, postCode);
			pstmt.setString(4, areaCode);
			pstmt.setString(5, gender);
			pstmt.setString(6, phoneNumber);
			pstmt.setInt(7, customerId);
			// SQL文の実行
			pstmt.executeUpdate();
		}
	}

	// 削除
	public void deleteCustomer(int customerId) throws ClassNotFoundException, SQLException {
		// SQL文
		String sql = "DELETE FROM m_customer WHERE customer_id = ?";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダーに値をセット
			pstmt.setInt(1, customerId);
			// SQL文の実行
			pstmt.executeUpdate();
		}
	}

	// 編集前データ
	public List<CustomerBean> getCustomerOldDetail() throws ClassNotFoundException, SQLException {
		// 空のリスト作成
		List<CustomerBean> customerOldDetail = new ArrayList<>();
		// SQL文
		String sql = "SELECT\n"
				+ "  customer_id,\n"
				+ "  customer_name,\n"
				+ "  customer_name_kana,\n"
				+ "  post_code,\n"
				+ "  area_code,\n"
				+ "  gender,\n"
				+ "  phone_number\n"
				+ "FROM\n"
				+ "  m_customer_log\n"
				+ "ORDER BY\n"
				+ "  id DESC\n"
				+ "LIMIT\n"
				+ "  1;";
		// データベース接続とプリペアドステートメントを取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// SQL文の実行
			ResultSet res = pstmt.executeQuery();
			// SQL文が一致する所まで繰り返す
			while (res.next()) {
				// CustomerBeanをインスタンス化して各カラムを取得してセットする
				CustomerBean customer = new CustomerBean();
				customer.setCustomerId(res.getInt("customer_id"));
				customer.setCustomerName(res.getString("customer_name"));
				customer.setCustomerNameKana(res.getString("customer_name_kana"));
				customer.setPostCode(res.getString("post_code"));
				customer.setAreaCode(res.getString("area_code"));
				customer.setGender(res.getString("gender"));
				customer.setPhoneNumber(res.getString("phone_number"));
				// リストに加える
				customerOldDetail.add(customer);
			}
		}
		return customerOldDetail;
	}
}
