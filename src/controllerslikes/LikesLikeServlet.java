package controllerslikes;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Like;
import models.Participant;
import models.Tweet;
import utils.DBUtil;

/**
 * Servlet implementation class LikesServlet
 */
@WebServlet("/likes/like")
public class LikesLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikesLikeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		Like l = new Like();

		Participant login_participant = (Participant)request.getSession().getAttribute("login_participant");

		l.setParticipant_id((Participant)request.getSession().getAttribute("login_participant"));

		Tweet tw = (Tweet)request.getSession().getAttribute("tweet_id");
		l.setTweet_id(tw);

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        l.setCreated_at(currentTime);

		long likes_count = (long)em.createNamedQuery("getLikeTweetCount", Long.class)
				.setParameter("participant_id", login_participant)
				.setParameter("tweet_id", tw)
				.getSingleResult();

		em.getTransaction().begin();

        if(likes_count > 0) {
        	Like like = em.createNamedQuery("getLikeTweet", Like.class)
 				   .setParameter("participant_id", login_participant)
 				   .setParameter("tweet_id", tw)
 				   .getSingleResult();
        	em.remove(like);
        	em.remove(l);

        }else {
    		em.persist(l);
        }

        em.getTransaction().commit();
    	em.close();

		response.sendRedirect(request.getContextPath() + "/tweets/show?tweet_id=" + tw.getTweet_id());
	}
}
