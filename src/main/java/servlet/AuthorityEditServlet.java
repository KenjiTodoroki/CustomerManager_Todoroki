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

import model.dao.AuthorityDAO;
import model.dao.UserDAO;
import model.entity.AuthorityBean;
import model.entity.UserBean;

/**
 * Servlet implementation class AuthorityEditServlet
 */
@WebServlet("/authority-edit")
public class AuthorityEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorityEditServlet() {
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
		// 空のリストを作成(jspを介してユーザー名と権限名をブラウザに表示させるため)
		List<UserBean> users = null;
		List<AuthorityBean> authorities = null;
		// リクエストパラメーターの取得
		String userId = request.getParameter("userId");
		String authorityCode = request.getParameter("authorityCode");
		// 転送用のパスを格納する変数
		String url = null;
		// DAOのインスタンス化
		UserDAO userDAO = new UserDAO();
		AuthorityDAO authorityDAO = new AuthorityDAO();

		try {
			// 全ての項目が入力済みの場合
			if (userId != null && authorityCode != null) {
				// authorityDAOからeditAuthorityメソッドを呼び出し、データベースを更新
				authorityDAO.editAuthority(userId, authorityCode);
				// メニューページに遷移する為のURLを変数に格納
				url = "menu.jsp";
				// それ以外
			} else {
				// 変数にユーザーと権限の一覧を格納
				users = userDAO.getAllUsers();
				authorities = authorityDAO.getAllAuthorities();
				// ユーザーと権限の一覧をセット
				request.setAttribute("users", users);
				request.setAttribute("authorities", authorities);
				// 編集失敗なので同じページのURLを変数に格納
				url = "authority-edit.jsp";
			}
			// 例外処理
		} catch (ClassNotFoundException | SQLException e) {
			// エラーメッセージ
			String errorMessage = "権限編集に失敗しました。";
			// エラー履歴
			e.printStackTrace();
			// 例外発生のため、編集ページを表示
			url = "authority-edit.jsp";
			// 同じページに戻る処理(エラーメッセージを表示)
			try {
				users = userDAO.getAllUsers();
				authorities = authorityDAO.getAllAuthorities();
			} catch (RuntimeException | ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			// エラーメッセージとユーザー、権限の一覧をセット
			request.setAttribute("users", users);
			request.setAttribute("authorities", authorities);
			request.setAttribute("errorMessage", errorMessage);
		}
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
