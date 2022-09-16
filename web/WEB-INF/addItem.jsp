<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add item</title>
</head>
<body>
<% List<Category> categories = (List<Category>) request.getAttribute("categories");%>


Please input items' data:
<form action="/items/add" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="please input title"/><br>
    <input type="number" name="price" placeholder="please input price"/><br>
    <select name="categoryId">
        <%for (Category category : categories) { %>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <% }%>
    </select><br>
    Profile Picture:
    <input type="file" name="picUrl">
    <input type="submit" value="Add">
</form>

</body>
</html>
