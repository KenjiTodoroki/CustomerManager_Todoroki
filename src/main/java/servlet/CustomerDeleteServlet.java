package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AreaDAO;
import model.dao.CustomerDAO;
import model.entity.AreaBean;
import model.entity.CustomerBean;

/**
 * Servlet implementation class CustomerDeleteServlet
 */
@WebServlet("/customer-delete")
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerDeleteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// 空のリストを作成
		List<CustomerBean> customers = null;
		List<AreaBean> areas = null;
		// リクエストパラメーターの取得
		String customerIdParam = request.getParameter("customerId");
		String button = request.getParameter("button");
		// String型からint型に変換
		int customerId = Integer.parseInt(customerIdParam);
		// 転送用パスを格納する変数
		String url = "";
		// DAOクラスjをインスタンス化
		CustomerDAO customerDAO = new CustomerDAO();
		AreaDAO areaDAO = new AreaDAO();
		// 処理
		try {
			// DAOクラスのメソッドで取得した該当IDのデータをリストに加える
			customers = customerDAO.getCustomerDetail(customerId);
			areas = areaDAO.getAreaName(customerId);
			// 上記リストをセット
			request.setAttribute("customers", customers);
			request.setAttribute("areas", areas);
			// クリックしたボタンの名称によって処理を変える
			switch (button) {
			// 顧客詳細画面から削除をクリック時
			case "削除" -> {
				// 削除するIDをセット
				request.setAttribute("customerId", customerId);
				// 削除確認画面に遷移するため変数に格納
				url = "customer-delete.jsp";
			}
			// 削除確認画面から削除確定をクリック時
			case "削除確定" -> {
				// DAOクラスのdeleteCustomerメソッドを呼び出す
				customerDAO.deleteCustomer(customerId);
				// 顧客一覧画面に遷移するため変数に格納
				url = "customer-list";
			}
			}
			// 例外発生時
		} catch (ClassNotFoundException | SQLException e) {
			// エラーメッセージ
			String errorMessage = "SQL操作時に例外が発生しました。";
			// エラー履歴
			e.printStackTrace();
			// 例外が発生したので詳細画面に戻る
			url = "customer-detail.jsp";
			// エラーメッセージをセット
			request.setAttribute("errorMessage", errorMessage);
		}
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
