package by.vlad.library.controller.command;

import by.vlad.library.controller.command.impl.*;
import by.vlad.library.controller.command.impl.gotopage.*;

public enum CommandType {
    CHANGE_ACCOUNT_PASSWORD(new ChangeAccountPasswordCommand()),
    CHANGE_LOCAL(new ChangeLocalCommand()),
    CREATE_NEW_ACCOUNT(new CreateNewAccountCommand()),
    DEFAULT(new DefaultCommand()),
    GO_TO_MAIN_PAGE(new GoToMainPageCommand()),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand()),
    GO_TO_CREATE_NEW_ACCOUNT_PAGE(new GoToCreateNewAccountPageCommand()),
    GO_TO_UPDATE_ACCOUNT_DATA_PAGE(new GoToUpdateAccountDataPageCommand()),
    GO_TO_CHANGE_PASSWORD_PAGE(new GoToChangeAccountPasswordPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SHOW_BOOKS_LIST(new ShowBooksListCommand()),
    SHOW_BOOK_INFO(new ShowBookByIdCommand()),
    UPDATE_USER_ACCOUNT_DATA(new UpdateUserAccountDataCommand());

    Command command;

    CommandType(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Command define(String commandStr){
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandStr.toUpperCase());
        }catch (IllegalArgumentException | NullPointerException e){
            //logger
            commandType = DEFAULT;
        }

        return commandType.getCommand();
    }
}
