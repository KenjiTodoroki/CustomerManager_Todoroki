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
import javax.servlet.http.HttpSession;

import model.dao.AreaDAO;
import model.dao.CustomerDAO;
import model.entity.AreaBean;
import model.entity.CustomerBean;
import model.entity.UserBean;

/**
 * Servlet implementation class CustomerDetailServlet
 */
@WebServlet("/customer-detail")
public class CustomerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDetailServlet() {
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
		// リクエストパラメーターの取得
		String customerIdParam = request.getParameter("customerId");
		// String型からint型に変換
		int customerId = Integer.parseInt(customerIdParam);
		// 転送用パスを格納
		String url = "customer-detail.jsp";
		// 各DAOクラスをインスタンス化
		CustomerDAO customerDAO = new CustomerDAO();
		AreaDAO areaDAO = new AreaDAO();
		
		try {
			// セッションオブジェクト取得
			HttpSession session = request.getSession();
			// ログイン時にセッションにセットしたuserをUserBean型で取得し変数に格納
			UserBean userBean = (UserBean) session.getAttribute("user");
			// ログインしたIDの権限コードを取得
			String authorityCode = userBean.getAuthorityCode();
			// DAOのメソッドから該当するIDの各カラムのリストを取得してそれぞれのリストに格納する
			List<CustomerBean> customers = customerDAO.getCustomerDetail(customerId);
			List<AreaBean> areas = areaDAO.getAreaName(customerId);
			// セッションオブジェクト取得し、セッションスコープに値をセット
			request.setAttribute("customers", customers);
			request.setAttribute("areas", areas);
			request.setAttribute("authorityCode", authorityCode);
		} catch (ClassNotFoundException | SQLException e) {
			// エラー履歴
			e.printStackTrace();
		}
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
