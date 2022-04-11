package by.vlad.JavaWebProject.controller.command;

import by.vlad.JavaWebProject.controller.command.impl.*;
import by.vlad.JavaWebProject.controller.command.impl.admin.AddUserCommand;
import by.vlad.JavaWebProject.controller.command.impl.page_command.GoToLoginPageCommand;
import by.vlad.JavaWebProject.controller.command.impl.page_command.GoToMainPageCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    GO_TO_MAIN_PAGE(new GoToMainPageCommand()),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand()),
    SHOW_BOOKS_LIST(new ShowBooksListCommand()),
    SHOW_BOOK_INFO(new ShowBookByIdCommand()),
    DEFAULT(new DefaultCommand());

    Command command;

    CommandType(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Command define(String commandStr){
        CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
        return commandType.getCommand();
    }
}
