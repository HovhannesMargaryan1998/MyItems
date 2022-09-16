<%@ page import="model.Item" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Item</title>
</head>
<body>
<%
    Item item = (Item) request.getAttribute("item");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<User> users = (List<User>) request.getAttribute("users");

%>
Please update items' data:
<form action="/items/edit" method="post">
    <input type="hidden" name="itemId" value="<%=item.getId()%>">
    <input type="text" name="title" value="<%=item.getTitle()%>"/><br>
    <input type="number" name="price" value="<%=item.getPrice()%>"/><br>

    <select name="categoryId">
        <%
            for (Category category : categories) {
                if (category.equals(item.getCategory())) {
        %>
        <option selected value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <%} else {%>
        <option value="<%=category.getId()%>"><%=category.getName()%>
                <% }}%>
    </select>
    <select name="userId">
        <%
            for (User user : users) {
                if (user.equals(item.getUser())) {
        %>
        <option selected value="<%=user.getId()%>"><%=user.getName()%>
        </option>
        <%} else {%>
        <option value="<%=user.getId()%>"><%=user.getName()%>
                <% }}%>
    </select>
    <input type="submit" value="Update">
</form>

</body>
</html>
