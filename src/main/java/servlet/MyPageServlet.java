package servlet;

import manager.ItemManager;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/myPage")
public class MyPageServlet extends HttpServlet {
    ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Item> itemsByUserId = itemManager.getItemsByUserId(user.getId());
        req.setAttribute("items", itemsByUserId);
        req.getRequestDispatcher("WEB-INF/myPage.jsp").forward(req, resp);
    }
}
