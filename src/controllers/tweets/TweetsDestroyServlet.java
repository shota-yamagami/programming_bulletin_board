package controllers.tweets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Like;
import models.Reply;
import models.Tweet;
import utils.DBUtil;

/**
 * Servlet implementation class TweetsDestroyServlet
 */
@WebServlet("/tweets/destroy")
public class TweetsDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsDestroyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();

			Tweet t = em.find(Tweet.class, (Integer)(request.getSession().getAttribute("tweet_id")));

			// この投稿へのコメントを取得する
			List<Reply> replys = em.createNamedQuery("getReplysForTweet", Reply.class)
					   .setParameter("tweet_id", t)
					   .getResultList();

			// この投稿へのいいねを取得する
			List<Like> likes = em.createNamedQuery("getAllTweet", Like.class)
					.setParameter("tweet_id", t)
					.getResultList();

			em.getTransaction().begin();
			// 同時にこの投稿へのコメントを全て削除する
			for(Reply r : replys) {
				em.remove(r);
			}

			// 同時にこの投稿へのいいねを全て削除する
			for(Like l : likes) {
				em.remove(l);
			}

			em.remove(t);
			em.getTransaction().commit();
			em.close();

			request.getSession().setAttribute("flush", "削除が完了しました。");
			request.getSession().removeAttribute("tweet_id");

			response.sendRedirect(request.getContextPath() + "/tweets/myPage");
		}
	}

}
