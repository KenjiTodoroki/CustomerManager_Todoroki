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
 * Servlet implementation class CustomerList
 */
@WebServlet("/customer-list")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// 転送用パスを格納
		String url = "customer-list.jsp";
		// CustomerDAOをインスタンス化
		CustomerDAO dao = new CustomerDAO();
		
		try {
			// daoのgetAllCustomerメソッドを実行してList型変数に格納
			List<CustomerBean> customerList = dao.getAllCustomer();
			// リクエストスコープに値をセット
			request.setAttribute("customerList", customerList);
		} catch (ClassNotFoundException | SQLException e) {
			// 例外が発生した時に履歴を表示する
			e.printStackTrace();
		}
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// 転送用パスを格納
		String url = "customer-list.jsp";
		// CustomerDAOをインスタンス化
		CustomerDAO dao = new CustomerDAO();
		
		try {
			// daoのgetAllCustomerメソッドを実行してList型変数に格納
			List<CustomerBean> customerList = dao.getAllCustomer();
			// リクエストスコープに値をセット
			request.setAttribute("customerList", customerList);
		} catch (ClassNotFoundException | SQLException e) {
			// 例外が発生した時に履歴を表示する
			e.printStackTrace();
		}
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
