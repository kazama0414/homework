package kr.mjc.jeongjuyeong.web.mvc;

import kr.mjc.jeongjuyeong.web.dao.Article;
import kr.mjc.jeongjuyeong.web.dao.ArticleDao;
import kr.mjc.jeongjuyeong.web.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class ArticleController {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleController(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    /**
     * 게시글 목록 화면
     */
    public void articleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("articleList", articleDao.listArticles(0, 100));

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleList.jsp")
                .forward(request, response);
    }

    /**
     * 게시글 작성 화면
     */
    public void articleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleForm.jsp")
                .forward(request, response);
    }

    /**
     * 게시글 조회 화면
     */
    public void viewArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/viewArticle.jsp")
                .forward(request, response);
    }

    /**
     * 게시글 검색 화면
     */
    public void searchArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/searchArticle.jsp")
                .forward(request, response);
    }

    /**
     * 게시글 수정 화면
     */
    public void articleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleUpdate.jsp")
                .forward(request, response);
    }

    /**
     * 게시글 삭제 화면
     */
    public void articleDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleDelete.jsp")
                .forward(request, response);
    }

    /**
     * 게시글 작성 액션
     */
    public void addArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("USER");
        int userId = user.getUserId();
        String name = user.getName();
        Article article = new Article();

        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setUserId(userId);
        article.setName(name);

        articleDao.addArticle(article);
        response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
    }

    /**
     * 게시글 검색 액션
     */
    public void getArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String articleId = request.getParameter("articleId");

        try {
            Article article = articleDao.getArticle(Integer.parseInt(articleId));
            HttpSession session = request.getSession();
            session.setAttribute("ARTICLE", article);
            response.sendRedirect(request.getContextPath() + "/mvc/article/viewArticle");
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() + "/mvc/article/searchArticle?msg=Wrong articleId");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/searchArticle?msg=Put only numbers in the articleId");
        }
    }

    /**
     * 게시글 수정 액션
     */
    public void updateArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession userSession = request.getSession();
        User user = (User) userSession.getAttribute("USER");
        int userId = user.getUserId();
        String articleId = request.getParameter("articleId");
        Article article = new Article();

        try {
            article.setArticleId(Integer.parseInt(articleId));
            article.setTitle(request.getParameter("title"));
            article.setContent(request.getParameter("content"));
            article.setUserId(userId);

            int updateRows = articleDao.updateArticle(article);
            if (updateRows > 0)
                response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
            else
                response.sendRedirect(request.getContextPath() +
                        "/mvc/article/articleUpdate?msg=You do not have permission or article doesn't exist");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/articleUpdate?msg=Put only numbers in the articleId");
        }
    }

    /**
     * 게시글 삭제 액션
     */
    public void deleteArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String articleId = request.getParameter("articleId");
        HttpSession userSession = request.getSession();
        User user = (User) userSession.getAttribute("USER");
        int userId = user.getUserId();

        try {
            int deletedRows = articleDao.deleteArticle(Integer.parseInt(articleId), userId);
            if (deletedRows > 0)
                response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
            else
                response.sendRedirect(request.getContextPath() +
                        "/mvc/article/articleDelete?msg=You do not have permission or article doesn't exist");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/articleDelete?msg=Put only numbers in the articleId");
        }
    }
}

