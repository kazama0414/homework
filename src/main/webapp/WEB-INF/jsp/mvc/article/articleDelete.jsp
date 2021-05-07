<%@ page import="java.util.Optional" %>
<%@ page import="kr.mjc.jeongjuyeong.web.dao.User" %>
<!DOCTYPE html>
<% User user = (User) session.getAttribute("USER");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/mvc/user/loginForm?msg=Please login");
    }
%>
<html>
<body>
<h3>게시글 삭제</h3>
<form action="deleteArticle" method="post">
    <p><input type="text" name="articleId" placeholder="글 번호" required autofocus/></p>
        <button type="submit">삭제</button>
    </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>
