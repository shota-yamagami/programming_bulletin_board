package controllers.replies;

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

import models.Participant;
import models.Reply;
import models.Tweet;
import models.validators.ReplyValidator;
import utils.DBUtil;

/**
 * Servlet implementation class RepliesCreateServlet
 */
@WebServlet("/replies/create")
public class RepliesCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepliesCreateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();

			Tweet t = em.find(Tweet.class, Integer.parseInt((String)request.getSession().getAttribute("tweet_id")));

			Reply r = new Reply();

			r.setTweet_id(t);
			r.setParticipant_id((Participant)request.getSession().getAttribute("login_participant"));
			r.setContent(request.getParameter("content"));

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setCreated_at(currentTime);
            r.setUpdated_at(currentTime);

            List<String> errors = ReplyValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("reply", r);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/replies/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(r);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                request.getSession().removeAttribute("tweet_id");

                response.sendRedirect(request.getContextPath() + "/tweets/show?tweet_id=" + t.getTweet_id());
            }
		}
	}
}
