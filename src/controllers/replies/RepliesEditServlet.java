package controllers.replies;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Participant;
import models.Reply;
import utils.DBUtil;

/**
 * Servlet implementation class RepliesEditServlet
 */
@WebServlet("/replies/edit")
public class RepliesEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepliesEditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		Reply r = em.find(Reply.class, Integer.parseInt(request.getParameter("reply_id")));

		em.close();

		Participant login_participant = (Participant)request.getSession().getAttribute("login_participant");
		if(r != null && login_participant.getParticipant_id() == r.getParticipant_id().getParticipant_id()) {
			request.setAttribute("reply", r);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("reply_id", r.getReply_id());
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/replies/edit.jsp");
        rd.forward(request, response);
	}

}
