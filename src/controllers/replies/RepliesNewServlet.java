package controllers.replies;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Reply;

/**
 * Servlet implementation class RepliesIndexServlet
 */
@WebServlet("/replies/new")
public class RepliesNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepliesNewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("_token", request.getSession().getId());


		Reply r = new Reply();
		request.setAttribute("reply", r);
		request.getSession().setAttribute("tweet_id", request.getParameter("tweet_id"));

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/replies/new.jsp");
		rd.forward(request, response);
	}

}
