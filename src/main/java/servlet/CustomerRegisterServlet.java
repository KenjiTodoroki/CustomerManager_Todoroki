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

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/customer-register")
public class CustomerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerRegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// 空のリストを作成(jspを介して地区名をブラウザに表示させるため)
		List<AreaBean> areas = null;
		// リクエストパラメーターの取得
		String customerName = request.getParameter("customerName");
		String customerNameKana = request.getParameter("customerNameKana");
		String postCode = request.getParameter("postCode");
		String areaCode = request.getParameter("areaCode");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String phoneNumber = request.getParameter("phoneNumber");
		// 転送用パスを格納する変数
		String url = null;
		// 各DAOクラスをインスタンス化
		CustomerDAO customerDAO = new CustomerDAO();
		AreaDAO areaDAO = new AreaDAO();

		try {
			// 全ての項目が入力済みの場合
			if (customerName != null && customerNameKana != null && postCode != null && areaCode != null
					&& gender != null && birthday != null && phoneNumber != null) {

				customerDAO.registerCustomer(customerName, customerNameKana, postCode, areaCode, gender, birthday,
						phoneNumber);
				// 顧客一覧のページに遷移する為のURLを変数に格納
				url = "customer-list";
				// いずれか未入力の場合(最初にページが遷移した時も同様)
			} else {
				// 変数に地区一覧を格納
				areas = areaDAO.getAllAreas();
				// 地区一覧をセット
				request.setAttribute("areas", areas);
				// 登録失敗なので同じページのURLを変数に格納
				url = "customer-register.jsp";
			}
			// 例外処理
		} catch (ClassNotFoundException | SQLException e) {
			// エラーメッセージ
			String errorMessage = "顧客登録に失敗しました。もう一度入力して下さい。";
			// エラー履歴
			e.printStackTrace();
			// 例外発生のため、登録ページを表示
			url = "customer-register.jsp";
			// 同じページに戻る処理(エラーメッセージを表示)
			try {
				areas = areaDAO.getAllAreas();
			} catch (RuntimeException | ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			// エラーメッセージと地区一覧をセット
			request.setAttribute("areas", areas);
			request.setAttribute("errorMessage", errorMessage);
		}
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
