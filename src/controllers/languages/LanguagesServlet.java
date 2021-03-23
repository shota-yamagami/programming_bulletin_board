package controllers.languages;

import java.io.IOException;
import java.util.HashMap;
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
 * Servlet implementation class LanguagesJavaServlet
 */
@WebServlet("/languages")
public class LanguagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LanguagesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		int language_flag = Integer.parseInt(request.getParameter("language_flag"));

		int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Tweet> tweetsLanguages = em.createNamedQuery("getTweetsEachLanguage_flag", Tweet.class)
        		.setParameter("language_flag", language_flag)
				.setFirstResult(15 * (page - 1))
				.setMaxResults(15)
				.getResultList();

		long tweetsLanguage_count = (long)em.createNamedQuery("getTweetsEachLanguage_flagCount", Long.class)
				.setParameter("language_flag", language_flag)
				.getSingleResult();

		// 各投稿へのリプライ数を取得
		HashMap<Integer, Long> reply_counts = new HashMap<Integer, Long>();
		// 各投稿へのいいね数を取得
		HashMap<Integer, Long> like_counts = new HashMap<Integer, Long>();
		for(Tweet i : tweetsLanguages) {
			long reply_count = (long)em.createNamedQuery("getReplysForTweetCount", Long.class)
										.setParameter("tweet_id", i)
										.getSingleResult();
			reply_counts.put(i.getTweet_id(), reply_count);
			long like_count = (long)em.createNamedQuery("getAllLikeTweetsCount", Long.class)
					.setParameter("tweet_id", i)
					.getSingleResult();
			like_counts.put(i.getTweet_id(), like_count);
		}

        em.close();

        String forwardPath = null;
    	for(int i = 0; i < 6; i++ ) {
    		if(language_flag == 0) {
    			forwardPath = "/WEB-INF/views//languages/html,css.jsp";
    		}else if(language_flag == 1) {
    			forwardPath = "/WEB-INF/views//languages/javaScript.jsp";
    		}else if(language_flag == 2) {
    			forwardPath = "/WEB-INF/views//languages/java.jsp";
    		}else if(language_flag == 3) {
    			forwardPath = "/WEB-INF/views//languages/PHP.jsp";
    		}else if(language_flag == 4) {
    			forwardPath = "/WEB-INF/views//languages/ruby.jsp";
    		}else if(language_flag == 5) {
    			forwardPath = "/WEB-INF/views//languages/python.jsp";
    		}else if(language_flag == 6) {
    			forwardPath = "/WEB-INF/views//languages/c.jsp";
    		}else if(language_flag == 7) {
    			forwardPath = "/WEB-INF/views//languages/other.jsp";
    		}
    		request.setAttribute("reply_counts", reply_counts);
    		request.setAttribute("like_counts", like_counts);
			request.setAttribute("tweetsLanguages", tweetsLanguages);
			request.setAttribute("tweetsLanguage_count", tweetsLanguage_count);
			request.setAttribute("page", page);
			request.setAttribute("language_flag", language_flag);
		}
    	RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
	    rd.forward(request, response);
	}
}