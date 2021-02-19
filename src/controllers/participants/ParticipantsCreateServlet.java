package controllers.participants;

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
import models.validators.ParticipantValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class ParticipantsCreateServlet
 */
@WebServlet("/participants/create")
public class ParticipantsCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantsCreateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		Participant p = new Participant();

		p.setName(request.getParameter("name"));
		p.setMail(request.getParameter("mail"));
		p.setPassword(
			EncryptUtil.getPasswordEncrypt(
				request.getParameter("password"),
					(String)this.getServletContext().getAttribute("pepper")
				)
			);

		List<String> errors = ParticipantValidator.validate(p, true, true);
		if(errors.size() > 0) {
			em.close();

			request.setAttribute("participant", p);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/participants/signUp.jsp");
            rd.forward(request, response);
		} else {
			em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "登録が完了しました。");
            em.close();

            response.sendRedirect(request.getContextPath() + "/tweets/index");
		}
	}

}
