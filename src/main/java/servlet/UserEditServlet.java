package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/users/edit")
public class UserEditServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = userManager.getUserById(userId);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/editUser.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String name = req.getParameter("name");
        String surName = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = User.builder()
                .id(userId)
                .name(name)
                .surname(surName)
                .email(email)
                .password(password)
                .build();
        userManager.edit(user);
        resp.sendRedirect("/myItem");
    }
}
