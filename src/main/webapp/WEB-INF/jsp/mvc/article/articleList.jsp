<%@ page import="kr.mjc.jeongjuyeong.web.dao.Article" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<body>
<h3>게시글 목록</h3>
<p>
    <a href="./articleForm">게시글 작성 </a> &nbsp <a href="./searchArticle">게시글 조회 </a> &nbsp
    <a href="./articleUpdate">수정 </a> &nbsp <a href="./articleDelete">삭제 </a>
</p>

<% List<Article> articleList = (List<Article>) request.getAttribute("articleList");
    for (Article article : articleList) {%>

<p>
    글번호:<%= article.getArticleId() %> | 제목:<%= article.getTitle() %> |
    유저번호:<%= article.getUserId() %> | 이름:<%= article.getName() %> | 작성일자:<%= article.getCdate() %>
</p>
<%
    }
%>
</body>
</html>
