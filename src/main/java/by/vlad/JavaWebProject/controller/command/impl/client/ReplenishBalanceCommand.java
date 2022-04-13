package by.vlad.JavaWebProject.controller.command.impl.client;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class ReplenishBalanceCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
