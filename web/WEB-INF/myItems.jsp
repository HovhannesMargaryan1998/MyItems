<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Im item</title>
</head>
<body>
<%
    List<Item> items = (List<Item>) request.getAttribute("items");
    User user = (User) session.getAttribute("user");
%>
<%
    for (Item item : items) {


%>
<tr>
    <td>
        <%if (item.getPicUrl() == null || item.getPicUrl().length() == 0) {%>
        <img src="\images\default-placeholder.png" width="100"/>
        <%} else {%>
        <img src="/getImage?pic_url=<%=item.getPicUrl()%> " width="100"/>
        <%}%></td>

    <td><%=item.getId()%>
    </td>
    <td><%=item.getTitle()%>
    </td>
    <td><%=item.getPrice()%>
    </td>
    <td><%=item.getCategory().getName()%>
    <td>

        <a href="items/remove?itemId=<%=item.getId()%>">Remove</a> |
        <a href="items/edit?itemId=<%=item.getId()%>">Edit</a>

    </td>
</tr>
<%
    }
%>
</body>
</html>
