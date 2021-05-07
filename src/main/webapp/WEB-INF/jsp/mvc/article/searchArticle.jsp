<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>게시글 번호 검색</h3>
<form action="getArticle" method="post">
  <p><input type="text" name="articleId" placeholder="글 번호" required autofocus/></p>
  <p><button type="submit">조회</button></p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
    .orElse("")%>
</p>
</body>
</html>
