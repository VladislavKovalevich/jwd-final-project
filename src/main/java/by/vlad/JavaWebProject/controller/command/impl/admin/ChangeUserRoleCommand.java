package by.vlad.JavaWebProject.controller.command.impl.admin;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class ChangeUserRoleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
