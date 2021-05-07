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
<h3>게시글 작성</h3>
<form action="addArticle" method="post">
    <p><input type="text" name="title" placeholder="제목" required autofocus/></p>
    <p><textarea name="content" placeholder="내용" cols="22" rows="7" style="resize: none;" required></textarea></p>
    <p>
        <button type="submit">작성</button>
    </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>
