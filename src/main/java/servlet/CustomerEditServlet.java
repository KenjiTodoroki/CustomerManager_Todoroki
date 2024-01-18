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
 * Servlet implementation class CustomerEditServlet
 */
@WebServlet("/customer-edit")
public class CustomerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerEditServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメーターの取得
		String customerIdParam = request.getParameter("customerId");
		// String型からint型に変換
		int customerId = Integer.parseInt(customerIdParam);
		// AreaDAOのインスタンス化
		AreaDAO areaDAO = new AreaDAO();
		// エラーメッセージ
		String errorMessage = "編集に失敗しました。もう一度入力して下さい。";
		// 同じページに戻る処理(エラーメッセージを表示)
		try {
			List<AreaBean> areas = areaDAO.getAllAreas();
			// エラーメッセージと地区一覧、IDをセット
			request.setAttribute("areas", areas);
			request.setAttribute("customerId", customerId);
			request.setAttribute("errorMessage", errorMessage);
		} catch (RuntimeException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメーターの取得
		String customerIdParam = request.getParameter("customerId");
		String customerName = request.getParameter("customerName");
		String customerNameKana = request.getParameter("customerNameKana");
		String postCode = request.getParameter("postCode");
		String areaCode = request.getParameter("areaCode");
		String gender = request.getParameter("gender");
		String phoneNumber = request.getParameter("phoneNumber");
		// String型からint型に変換
		int customerId = Integer.parseInt(customerIdParam);
		// 転送用のパスを格納する変数
		String url = "customer-edit.jsp";
		// DAOのインスタンス化
		CustomerDAO customerDAO = new CustomerDAO();
		AreaDAO areaDAO = new AreaDAO();

		try {
			// 全ての項目が入力済みの場合
			if (customerIdParam != null && customerName != null && customerNameKana != null && postCode != null
					&& areaCode != null
					&& gender != null && phoneNumber != null) {
				// customerDAOからeditCustomerメソッドを呼び出し、データベースを更新
				customerDAO.editCustomer(customerId, customerName, customerNameKana, postCode, areaCode, gender,
						phoneNumber);
				// 編集結果のページに遷移する為のURLを変数に格納
				url = "customer-editLog";
				// それ以外
			} else {
				// リストにエリア一覧を格納
				List<AreaBean> areas = areaDAO.getAllAreas();
				// リストに顧客の各カラムを格納
				List<CustomerBean> customers = customerDAO.getCustomerDetail(customerId);
				// エリア一覧をセット
				request.setAttribute("areas", areas);
				// 一致するIDを参照する
				request.setAttribute("customerId", customerId);
				// リストをセット
				request.setAttribute("customers", customers);
			}
			// 例外処理
		} catch (ClassNotFoundException | SQLException e) {
			// エラー履歴
			e.printStackTrace();
			// エラー処理をdoGetに移して画面表示の処理をする
			doGet(request, response);
		}
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
