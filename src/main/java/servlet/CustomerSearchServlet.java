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

import model.dao.CustomerDAO;
import model.entity.CustomerBean;

/**
 * Servlet implementation class CustomerSearchServlet
 */
@WebServlet("/customer-search")
public class CustomerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerSearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// エラーメッセージ
		String errorMessage = "エラー発生したため、登録に失敗しました。";
		// エラーメッセージをセット
		request.setAttribute("errorMessage", errorMessage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// CustomerDAOをインスタンス化
		CustomerDAO dao = new CustomerDAO();
		// 検索された文字を取得し変数に格納
		String searchWord = request.getParameter("searchWord");
		// 転送用パス
		String url = "customer-list.jsp";
		// 空の文字列を変数に格納
		String noCustomer = null;

		try {
			// 検索された文字を引数に入れSearchCustomerメソッドを使いリストに格納
			List<CustomerBean> customerSearch = dao.searchCustomer(searchWord);
			// 検索に該当する氏名がない場合の処理
			if (customerSearch == null || customerSearch.isEmpty()) {
				// 該当しない時のメッセージ
				noCustomer = "検索結果はありません";
			}
			// リクエストスコープに値をセット
			request.setAttribute("customerSearch", customerSearch);
			request.setAttribute("noCustomer", noCustomer);

		} catch (ClassNotFoundException | SQLException e) {
			// 例外の履歴
			e.printStackTrace();
			// エラー処理をdoGetに移して画面表示の処理をする
			doGet(request, response);
		}
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
