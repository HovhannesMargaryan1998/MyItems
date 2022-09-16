package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/items/edit")
public class ItemEditServlet extends HttpServlet {
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        Item item = itemManager.getById(itemId);
        req.setAttribute("categories", categoryManager.getAll());
        req.setAttribute("users", userManager.getAll());
        req.setAttribute("item", item);
        req.getRequestDispatcher("/WEB-INF/editItem.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        Item item = Item.builder()
                .id(itemId)
                .title(title)
                .price(price)
                .category(categoryManager.getById(categoryId))
                .user(userManager.getUserById(userId))
                .build();
        itemManager.edit(item);
        resp.sendRedirect("/imitem");
    }
}
