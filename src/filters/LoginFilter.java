package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Participant;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// コンテキストパス(/programming_bulletin_boad)を変数context_pathへ代入
		String context_path = ((HttpServletRequest)request).getContextPath();

		// サーブレットパス((/programming_bulletin_boad)より下のパス)を変数servlet_pathへ代入
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        // CSS、pdf、jpgフォルダ内は認証処理から除外する
        if(!servlet_path.matches("/css.*") && !servlet_path.matches("/pdf.*") && !servlet_path.matches("/jpg.*")) {
            HttpSession session = ((HttpServletRequest)request).getSession();

            // セッションスコープに保存されたログインユーザ情報を取得
            Participant p = (Participant)session.getAttribute("login_participant");

            // この3つの画面以外について
            if(!servlet_path.equals("/index.html") && !servlet_path.equals("/participants/signUp") && !servlet_path.equals("/participants/create") && !servlet_path.equals("/participants/login")) {

            	// ログアウトしている状態であれば、ログイン画面にリダイレクト
                if(p == null) {
                    ((HttpServletResponse)response).sendRedirect(context_path + "/participants/login");
                    return;
                }

            }
        }
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
