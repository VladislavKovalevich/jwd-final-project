package by.vlad.JavaWebProject.controller.command;

import by.vlad.JavaWebProject.controller.command.impl.*;
import by.vlad.JavaWebProject.controller.command.impl.client.ReplenishBalanceCommand;
import by.vlad.JavaWebProject.controller.command.impl.client.page_command.GoToReplenishBalancePageCommand;
import by.vlad.JavaWebProject.controller.command.impl.page_command.*;

public enum CommandType {
    CREATE_NEW_ACCOUNT(new CreateNewAccountCommand()),
    CHANGE_ACCOUNT_PASSWORD(new ChangeAccountPasswordCommand()),
    DEFAULT(new DefaultCommand()),
    GO_TO_MAIN_PAGE(new GoToMainPageCommand()),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand()),
    GO_TO_CREATE_NEW_ACCOUNT_PAGE(new GoToCreateNewAccountPageCommand()),
    GO_TO_REPLENISH_BALANCE_PAGE(new GoToReplenishBalancePageCommand()),
    GO_TO_UPDATE_ACCOUNT_DATA_PAGE(new GoToUpdateAccountDataPageCommand()),
    GO_TO_CHANGE_PASSWORD_PAGE(new GoToChangeAccountPasswordPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REPLENISH_BALANCE(new ReplenishBalanceCommand()),
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
        CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
        return commandType.getCommand();
    }
}
