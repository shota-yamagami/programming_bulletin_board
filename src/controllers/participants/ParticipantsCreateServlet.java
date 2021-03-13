package controllers.participants;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Participant;
import models.validators.ParticipantValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class ParticipantsCreateServlet
 */
@WebServlet("/participants/create")
public class ParticipantsCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantsCreateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// データベースにアクセス
		EntityManager em = DBUtil.createEntityManager();

		// Participantクラスのインスタンスの作成
		Participant p = new Participant();

		// Participantクラスから新規登録画面で入力されたname,mail,passwordのレコードを取得
		p.setName(request.getParameter("name"));
		p.setMail(request.getParameter("mail"));
		p.setPassword(
			// パスワードのハッシュ化
			EncryptUtil.getPasswordEncrypt(
				request.getParameter("password"),
					(String)this.getServletContext().getAttribute("pepper")
				)
			);

		// 取得したデータにエラーがないか確認
		List<String> errors = ParticipantValidator.validate(p, true, true);
		if(errors.size() > 0) {
			// エラーがあればデータベースを閉じる
			em.close();

			// 取得したトークン、入力内容とエラー内容をリクエストスコープでセットする
			request.setAttribute("_token", request.getSession().getId());
			request.setAttribute("participant", p);
            request.setAttribute("errors", errors);

            // 新規投稿画面へ戻る
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/participants/signUp.jsp");
            rd.forward(request, response);
		} else {
			// 登録内容が正常であればトランザクション処理を開始し、情報を登録し、フラッシュメッセージをセットしてデータベースを閉じる
			em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "登録が完了しました。 " + p.getName() + "さんPGハウスへようこそ!");
            em.close();

            // セッションスコープにログイン情報を置く
            request.getSession().setAttribute("login_participant", p);

            // Myページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/tweets/myPage");
		}
	}

}
