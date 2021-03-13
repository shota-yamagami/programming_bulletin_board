package controllers.replies;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Reply;
import models.Tweet;
import utils.DBUtil;

/**
 * Servlet implementation class RepliesDestroyServlet
 */
@WebServlet("/replies/destroy")
public class RepliesDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepliesDestroyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();

			Tweet t =  (Tweet) request.getSession().getAttribute("tweet_id");

			Reply r = em.find(Reply.class, (Integer)(request.getSession().getAttribute("reply_id")));

			em.getTransaction().begin();
			em.remove(r);
			em.getTransaction().commit();
			em.close();
			request.getSession().setAttribute("flush", "削除が完了しました。");
			request.getSession().removeAttribute("reply_id");

			response.sendRedirect(request.getContextPath() + "/tweets/show?tweet_id=" + t.getTweet_id());
		}
	}

}
