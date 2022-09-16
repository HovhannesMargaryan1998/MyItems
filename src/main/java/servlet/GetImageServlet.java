package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

@WebServlet(urlPatterns = "/getImage")
public class GetImageServlet extends HttpServlet {
    public static final String IMAGE_PATH = "C:\\Users\\User\\IdeaProjects\\MyItems\\itemsImages";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("pic_url");
        if (path == null || path.length() == 0) {
            resp.sendRedirect("/home");
        }
        File file = new File(IMAGE_PATH + File.separator + path);
        if (!file.exists()) {
            resp.sendRedirect("/home");
        } else {
            resp.setContentType("image/jpeg");
            resp.setHeader("Content-disposition", "attachment; filename=" + path);

            try (InputStream in = Files.newInputStream(file.toPath());
                 OutputStream out = resp.getOutputStream()) {
                byte[] buffer = new byte[1048];
                int numBytesRead;
                while ((numBytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, numBytesRead);
                }
            }
        }
    }
}
