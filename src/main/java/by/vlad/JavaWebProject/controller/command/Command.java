package by.vlad.JavaWebProject.controller.command;

import by.vlad.JavaWebProject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}