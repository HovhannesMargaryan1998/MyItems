<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items page</title>
</head>
<body>
<%

    User user = (User) session.getAttribute("user");
%>
<div style="margin:0 auto ;width: 1000px; height: 1000px; border: 1px solid black">
    <div>WELCOME <%=user.getName()%>|<a href="/myItems">My Items</a> |
        <a href="/items/add">Add Items</a> | <a href="/logout">Logout</a></div>
    <a href="/users/edit?userId=<%=user.getId()%>">Edit User Data</a>
</div>

</body>
</html>
