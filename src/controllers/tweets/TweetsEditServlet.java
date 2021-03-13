package controllers.tweets;

import java.io.IOException;

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
 * Servlet implementation class TweetsEditServlet
 */
@WebServlet("/tweets/edit")
public class TweetsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsEditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		Tweet t = em.find(Tweet.class, Integer.parseInt(request.getParameter("tweet_id")));

		em.close();

		Participant login_participant = (Participant)request.getSession().getAttribute("login_participant");
		if(t != null && login_participant.getParticipant_id() == t.getParticipant_id().getParticipant_id()) {
			request.setAttribute("tweet", t);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("tweet_id", t.getTweet_id());
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tweets/edit.jsp");
        rd.forward(request, response);
	}

}
