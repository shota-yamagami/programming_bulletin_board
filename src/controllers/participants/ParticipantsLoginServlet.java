package controllers.participants;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Participant;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class ParticipantsLoginServlet
 */
@WebServlet("/participants/login")
public class ParticipantsLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantsLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 // ログイン画面を表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CSRF対策 (トークンをjspに送る準備)
		request.setAttribute("_token", request.getSession().getId());
		// ログインできなかった際のエラーメッセージを表示するかどうかのフラグ。ログインボタンを押した時にエラーを表示するので初期はfalseの状態
        request.setAttribute("hasError", false);
        // ログアウト時のフラッシュメッセージを取得しメッセージをリクエストスコープに入れ直して、セッションスコープのメッセージを消す。
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/participants/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// ログイン処理を実行
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 認証結果を格納する変数
		Boolean check_result = false;

		String name = request.getParameter("name");
		String plain_pass = request.getParameter("password");

		Participant p = null;

		if(name != null && !name.equals("") && plain_pass != null && !plain_pass.equals("")) {
			EntityManager em = DBUtil.createEntityManager();

			String password = EncryptUtil.getPasswordEncrypt(
					plain_pass,
					(String)this.getServletContext().getAttribute("pepper")
					);

			// ニックネームとパスワードが正しいかチェックする
			try {
				p = em.createNamedQuery("checkLoginNameAndPassword", Participant.class)
					  .setParameter("name", name)
					  .setParameter("pass", password)
					  .getSingleResult();
			} catch(NoResultException ex) {}

			em.close();

			if(p != null) {
				check_result = true;
			}
		}

		if(!check_result) {
			 // 認証できなかったらログイン画面に戻る
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true);
            request.setAttribute("name", name);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/participants/login.jsp");
            rd.forward(request, response);
		} else {
			// 認証できたらログイン状態にしてMyページへリダイレクト
            request.getSession().setAttribute("login_participant", p);

            request.getSession().setAttribute("flush", "ログインしました。");
            response.sendRedirect(request.getContextPath() + "/tweets/myPage");
		}
	}

}
