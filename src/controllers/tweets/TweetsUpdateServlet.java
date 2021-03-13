package controllers.tweets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tweet;
import models.validators.TweetValidator;
import utils.DBUtil;

/**
 * Servlet implementation class TweetsUpdateServlet
 */
@WebServlet("/tweets/update")
public class TweetsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();

			Tweet t = em.find(Tweet.class, (Integer)(request.getSession().getAttribute("tweet_id")));


			t.setLanguage_flag(Integer.parseInt(request.getParameter("language_flag")));
			t.setTitle(request.getParameter("title"));
			t.setContent(request.getParameter("content"));
			t.setUpdated_at(new Timestamp(System.currentTimeMillis()));

			List<String> errors = TweetValidator.validate(t);
			if(errors.size() > 0) {
				em.close();

				request.setAttribute("_token", request.getSession().getId());
				request.setAttribute("tweet", t);
				request.setAttribute("errors", errors);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tweets/edit.jsp");
				rd.forward(request, response);
			} else {
				// エラーがなければデータベースに新しい情報を保存する ※更新時、em.persist();は必要ない
				em.getTransaction().begin();
				em.getTransaction().commit();
				em.close();
				request.getSession().setAttribute("flush", "更新が完了しました。");
				request.getSession().removeAttribute("tweet_id");

				response.sendRedirect(request.getContextPath() + "/tweets/show?tweet_id=" + t.getTweet_id());
			}
		}
	}

}
