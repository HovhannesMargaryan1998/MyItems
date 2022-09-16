package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();
    private final CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("category_id");
        List<Item> items;
        if (categoryId == null || categoryId.equals("")) {
            items = itemManager.getLastTwentyItems();
        } else {
            int categoriesId = Integer.parseInt(categoryId);
            items = itemManager.getLastTwentyItemsByCategory(categoriesId);
        }
        req.setAttribute("items", items);
        req.setAttribute("categories", categoryManager.getAll());
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
