package controllers.searches;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tweet;
import utils.DBUtil;

/**
 * Servlet implementation class SearchesSearchServlet
 */
@WebServlet("/searches/search")
public class SearchesSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchesSearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("id");

		if(key != null && !key.equals("")) {
			EntityManager em = DBUtil.createEntityManager();

			List<Tweet> searches = em.createNamedQuery("getTweetsSearch", Tweet.class)
					   .setParameter("i", "%" + key + "%")
					   .getResultList();

			em.close();

			if(searches.size() == 0) {
				request.getSession().setAttribute("search_flush", "お探しのデータはありませんでした");
				response.sendRedirect(request.getContextPath() + "/tweets/myPage");
			} else {
				request.setAttribute("searches", searches);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tweets/search.jsp");
		        rd.forward(request, response);
			}
		} else {
			request.getSession().setAttribute("search_flush", "検索内容を入力してください。");
			response.sendRedirect(request.getContextPath() + "/tweets/myPage");
		}
	}
}
