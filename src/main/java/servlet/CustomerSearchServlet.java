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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		// 空のリストを作成
		List<CustomerBean> customerSearch = null;
		
		try {
			// 検索された文字を引数に入れSearchCustomerメソッドを使い変数に格納
			customerSearch = dao.SearchCustomer(searchWord);
			// 検索に該当する氏名がない場合の処理
			if (customerSearch == null || customerSearch.isEmpty()) {
				// 該当しない時のメッセージ
				noCustomer = "検索結果はありません";
			}
			
			request.setAttribute("customerSearch", customerSearch);
			request.setAttribute("noCustomer", noCustomer);			
			
		} catch (ClassNotFoundException | SQLException e) {
			// エラーメッセージ
			String errorMessage = "SQL操作時に例外が発生しました。";
			// 例外の履歴
			e.printStackTrace();
			// エラーメッセージをセット
			request.setAttribute("errorMessage", errorMessage);
		}
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
