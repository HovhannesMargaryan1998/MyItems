<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page import="model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%
    List<Item> items = (List<Item>) request.getAttribute("items");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<a href="/users/add">Register</a>
<a href="/login">Login</a>
<div style="margin:0 auto ;width: 1000px; height: 1000px; border: 1px solid black">
    <div>
        <ul style="overflow: hidden">
            <li style="display: inline;margin-left: 40px"><a href="/home">Home</a></li>
            <% for (Category category : categories) {%>
            <li style="display: inline;margin-left: 40px"><a
                    href="/home?category_id=<%=category.getId()%> "><%=category.getName()%>
            </a>
            </li>
            <%}%>
        </ul>
    </div>

    <div>
        <%
            for (Item item : items) {%>
        <div style="width: 105px;float: left;padding-left: 40px" >
            <div>
                <%if (item != null) {%>
                <img src="/getImage?pic_url=<%=item.getPicUrl()%>" width="200" height="200"/>
                <%} else { %>
                <img src="/images/default-placeholder.png" width="200" height="200"/>

                <%}%>
            </div>

            <div>
                <span><%=item.getTitle()%>|<%=item.getPrice()%></span>
            </div>

        </div>

        <%
            }
        %>

    </div>
</div>

</body>
</html>
