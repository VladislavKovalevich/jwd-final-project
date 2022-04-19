package by.vlad.JavaWebProject.controller;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.CommandType;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet(name = "controllerServlet", value = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String commandStr = request.getParameter("command");//TODO параметр в константу

        Command command = CommandType.define(commandStr);
        Router router;

        try {
            router = command.execute(request);

            switch (router.getType()) {
                case FORWARD: {
                    request.getRequestDispatcher(router.getPage()).forward(request, response);
                    break;
                }
                case REDIRECT: {
                    response.sendRedirect(router.getPage());
                    break;
                }
            }
        } catch (CommandException e) {
            //response.sendError(500); // 1
            throw new ServletException(e); // 2
            //request.setAttribute("error_msg", e.getCause()); // 3
            //request.getRequestDispatcher("pages/error/error_500.jsp").forward(request, response); // 3
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    public void destroy() {
    }
}