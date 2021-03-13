package controllers.tweets;

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
import models.Reply;
import models.Tweet;
import utils.DBUtil;

/**
 * Servlet implementation class TweetsShowServlet
 */
@WebServlet("/tweets/show")
public class TweetsShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsShowServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		Participant login_participant = (Participant)request.getSession().getAttribute("login_participant");

		Tweet t = em.find(Tweet.class, Integer.parseInt(request.getParameter("tweet_id")));

		List<Reply> replys = em.createNamedQuery("getReplysForTweet", Reply.class)
							   .setParameter("tweet_id", t)
                    		   .getResultList();

		long like = (long)em.createNamedQuery("getLikeTweetCount", Long.class)
							   .setParameter("tweet_id", t)
							   .setParameter("participant_id", login_participant)
							   .getSingleResult();

		em.close();

		request.getSession().setAttribute("tweet_id", t);
		request.setAttribute("tweet", t);
		request.setAttribute("replys", replys);
		request.setAttribute("_token", request.getSession().getId());
		request.setAttribute("like", like);
		if(request.getSession().getAttribute("flush") != null) {
			request.setAttribute("flush", request.getSession().getAttribute("flush"));
			request.getSession().removeAttribute("flush");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tweets/show.jsp");
		rd.forward(request, response);
	}

}
