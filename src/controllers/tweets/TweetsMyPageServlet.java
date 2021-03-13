package controllers.tweets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Participant;
import models.Tweet;
import utils.DBUtil;

/**
 * Servlet implementation class TweetsMyPageServlet
 */
@WebServlet("/tweets/myPage")
public class TweetsMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsMyPageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		// セッションスコープからログイン情報を取得
		Participant login_participant = (Participant)request.getSession().getAttribute("login_participant");

		// 開くページ数を取得(デフォルトは1ページ)
		int page;
		try{
			page = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			page = 1;
		}

		// 最大件数と開始位置を指定して自分の投稿を取得
		List<Tweet> tweets = em.createNamedQuery("getAllMyTweets", Tweet.class)
							   .setParameter("participant_id", login_participant)
							   .setFirstResult(15 * (page - 1))
							   .setMaxResults(15)
							   .getResultList();

		// 自分の投稿数を全て取得
		long tweets_count = (long)em.createNamedQuery("getMyTweetsCount", Long.class)
									.setParameter("participant_id", login_participant)
									.getSingleResult();

		// 各投稿へのリプライ数を取得
		HashMap<Integer, Long> count = new HashMap<Integer, Long>();
		// 各投稿へのいいね数を取得
		HashMap<Integer, Long> like_count = new HashMap<Integer, Long>();
		for(Tweet i : tweets) {
			long replys_count = (long)em.createNamedQuery("getReplysForTweetCount", Long.class)
										.setParameter("tweet_id", i)
										.getSingleResult();
			count.put(i.getTweet_id(), replys_count);
			long my_like_tweet_count = (long)em.createNamedQuery("getAllLikeTweetsCount", Long.class)
					.setParameter("tweet_id", i)
					.getSingleResult();
			like_count.put(i.getTweet_id(), my_like_tweet_count);
		}

		em.close();


		// jspへ渡すデータの準備
		request.setAttribute("tweets", tweets);
		request.setAttribute("tweets_count", tweets_count);
		request.setAttribute("count", count);
		request.setAttribute("like_count", like_count);
		request.setAttribute("page", page);
		if(request.getSession().getAttribute("flush") != null) {
			request.setAttribute("flush", request.getSession().getAttribute("flush"));
			request.getSession().removeAttribute("flush");
		}
		if(request.getSession().getAttribute("search_flush") != null) {
			request.setAttribute("search_flush", request.getSession().getAttribute("search_flush"));
			request.getSession().removeAttribute("search_flush");
		}
		// 遷移先の指定
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tweets/myPage.jsp");
        rd.forward(request, response);
	}

}
