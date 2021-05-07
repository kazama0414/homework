<%@ page import="kr.mjc.jeongjuyeong.web.dao.Article" %>
<!DOCTYPE html>
<% Article article = (Article) session.getAttribute("ARTICLE"); %>
<html>
<body>
<h2><%= article.getTitle() %> </h2>
<p> 유저번호:<%= article.getUserId() %> | 이름:<%= article.getName() %> |
    작성일자:<%= article.getCdate()%> | 수정일자:<%= article.getUdate()%>
</p>
<p> --------------------------------------------------------------------------------------------------------<br>
    <%= article.getContent()%>
</p>
</body>
</html>
