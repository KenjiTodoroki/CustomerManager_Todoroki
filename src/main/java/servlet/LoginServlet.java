package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* @see HttpServlet#HttpServlet()
	*/
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// エラーメッセージを表示
		request.setAttribute("errorMessage", "エラー発生したため、ログインに失敗しました。");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメータの取得
		String userId = request.getParameter("userId"); // ユーザID
		String password = request.getParameter("password"); // パスワード
		// 転送用パスを格納する変数
		String url = "login.jsp";
		// UserDAOクラスをインスタンス化
		UserDAO dao = new UserDAO();
		// try-catchで例外処理
		try {
			// UserDAOクラスのcheckLoginメソッドを呼び出してユーザ情報を取得
			UserBean user = dao.checkLogin(userId, password);
			// idとpasswordがデータベースに登録されていた場合
			if (user != null) {
				// メニュー画面のパス
				url = "menu.jsp";
				/**
				 * 以下、権限によって顧客登録、権限編集のボタンを表示する変数を定義
				 * ログインしたIDの権限コードを取得
				*/ 
				String authorityCode = user.getAuthorityCode();
				// セッションオブジェクト取得し、セッションスコープに値をセット
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("authorityCode", authorityCode);
				// idとpasswordがデータベースに登録されていなかった場合
			} else {
				// リクエストスコープに表示したい文字をセット
				request.setAttribute("errorMessage", "ログインに失敗しました。もう一度入力して下さい。");
			}
		// 例外キャッチ
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