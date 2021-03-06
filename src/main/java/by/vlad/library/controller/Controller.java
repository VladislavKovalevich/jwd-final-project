package by.vlad.library.controller;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.CommandType;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

import static by.vlad.library.controller.command.AttributeAndParamsNames.COMMAND;

@WebServlet(name = "controllerServlet", value = {"/controller", "*.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class Controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");

        String commandStr = request.getParameter(COMMAND);
        Command command = CommandType.getCommandType(commandStr).getCommand();

        Router router;

        try {
            router = command.execute(request);

            switch (router.getType()) {
                case FORWARD -> {
                    request.getRequestDispatcher(router.getPage()).forward(request, response);
                }
                case REDIRECT -> {
                    response.sendRedirect(router.getPage());
                }
                default -> {
                    response.sendError(500);
                }
            }
        } catch (CommandException e) {
            response.sendError(500, e.getMessage());
        }
    }

    public void destroy() {
    }
}