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

import models.Reply;
import models.Tweet;
import models.validators.ReplyValidator;
import utils.DBUtil;

/**
 * Servlet implementation class RepliesUpdateServlet
 */
@WebServlet("/replies/update")
public class RepliesUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepliesUpdateServlet() {
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

			r.setContent(request.getParameter("content"));
			r.setUpdated_at(new Timestamp(System.currentTimeMillis()));

			List<String> errors = ReplyValidator.validate(r);
			if(errors.size() > 0) {
				em.close();

				request.setAttribute("_token", request.getSession().getId());
				request.setAttribute("reply", r);
				request.setAttribute("errors", errors);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/replies/edit.jsp");
				rd.forward(request, response);
			} else {
				em.getTransaction().begin();
				em.getTransaction().commit();
				em.close();
				request.getSession().setAttribute("flush", "更新が完了しました。");
				request.getSession().removeAttribute("reply_id");

				response.sendRedirect(request.getContextPath() + "/tweets/show?tweet_id=" + t.getTweet_id());
			}
		}
	}

}
