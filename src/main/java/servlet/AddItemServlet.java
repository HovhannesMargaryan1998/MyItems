package servlet;


import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100

)
public class AddItemServlet extends HttpServlet {
    private CategoryManager categoryManager = new CategoryManager();
    private ItemManager itemManager = new ItemManager();
    public static final String IMAGE_PATH = "C:\\Users\\User\\IdeaProjects\\MyItems\\itemsImages";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryManager.getAll();
        req.setAttribute("categories", categoryList);
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        Double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Part picUrl = req.getPart("picUrl");

        if (picUrl != null) {
            long nanoTime = System.nanoTime();
            String fileName = nanoTime + "_" + picUrl.getSubmittedFileName();
            String fileNames = IMAGE_PATH + File.separator + fileName;
            picUrl.write(fileNames);
            Item item = Item.builder()
                    .title(title)
                    .price(price)
                    .category(categoryManager.getById(categoryId))
                    .picUrl(fileName)
                    .user(user)
                    .build();
            itemManager.add(item);
            resp.sendRedirect("/myItem");
        }
    }
}