package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.UserBean;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
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
		// HttpServletRequestのgetSessionメソッドを実行
		HttpSession session = request.getSession();
		// ログイン時にセッションにセットしたuserをUserBean型で取得し変数に格納
		UserBean userBean = (UserBean) session.getAttribute("user");
		// 変数パスにログアウト画面を格納する
		String url = "logout.jsp";
		// ユーザー名をUserBeanから取得する為の変数
		String userName = userBean.getUserId();
		// ログアウトメッセージを作成
		String logoutMessage = userName + "さん" + " " + "ログアウトしました";
		// セッションを終了
		session.invalidate();
		// ログアウトメッセージをセットする
		request.setAttribute("logoutMessage", logoutMessage);
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
