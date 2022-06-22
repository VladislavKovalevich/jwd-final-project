package by.vlad.library.controller.command;

import by.vlad.library.controller.command.impl.*;
import by.vlad.library.controller.command.impl.admin.*;
import by.vlad.library.controller.command.impl.admin.ChangeUserStatusCommand;
import by.vlad.library.controller.command.impl.admin.ShowUsersListCommand;
import by.vlad.library.controller.command.impl.admin.gotopage.GoToAddBookComponentsPageCommand;
import by.vlad.library.controller.command.impl.admin.gotopage.GoToAddNewBookPageCommand;
import by.vlad.library.controller.command.impl.admin.gotopage.GoToUpdateBookComponentsPageCommand;
import by.vlad.library.controller.command.impl.admin.gotopage.GoToUpdateBookDataPageCommand;
import by.vlad.library.controller.command.impl.client.AddBookToOrderCommand;
import by.vlad.library.controller.command.impl.client.RemoveBookFromOrderCommand;
import by.vlad.library.controller.command.impl.client.CreateOrderCommand;
import by.vlad.library.controller.command.impl.client.DeleteOrderCommand;
import by.vlad.library.controller.command.impl.gotopage.*;

public enum CommandType {
    ADD_BOOK_TO_ORDER(new AddBookToOrderCommand()),
    ADD_NEW_AUTHOR(new AddNewAuthorCommand()),
    ADD_NEW_BOOK(new AddNewBookCommand()),
    ADD_NEW_GENRE(new AddNewGenreCommand()),
    ADD_NEW_PUBLISHER(new AddNewPublisherCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    CHANGE_ACCOUNT_PASSWORD(new ChangeAccountPasswordCommand()),
    CHANGE_LOCAL(new ChangeLocalCommand()),
    CREATE_NEW_ACCOUNT(new CreateNewAccountCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    DEFAULT(new DefaultCommand()),
    DELETE_ORDER(new DeleteOrderCommand()),
    GET_BOOKS_BY_ORDER_ID(new GetBooksByOrderIdCommand()),
    GET_ORDERS_BY_USER_ID(new GetOrdersByUserIdCommand()),
    GET_ORDERS_LIST(new GetAllOrdersCommand()),
    GO_TO_ADD_BOOK_COMPONENTS_PAGE(new GoToAddBookComponentsPageCommand()),
    GO_TO_ADD_NEW_BOOK_PAGE(new GoToAddNewBookPageCommand()),
    GO_TO_CHANGE_PASSWORD_PAGE(new GoToChangeAccountPasswordPageCommand()),
    GO_TO_CREATE_NEW_ACCOUNT_PAGE(new GoToCreateNewAccountPageCommand()),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand()),
    GO_TO_MAIN_PAGE(new GoToMainPageCommand()),
    GO_TO_UPDATE_ACCOUNT_DATA_PAGE(new GoToUpdateAccountDataPageCommand()),
    GO_TO_UPDATE_BOOK_COMPONENTS_PAGE(new GoToUpdateBookComponentsPageCommand()),
    GO_TO_UPDATE_BOOK_DATA_PAGE(new GoToUpdateBookDataPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REMOVE_BOOK_FROM_ORDER(new RemoveBookFromOrderCommand()),
    SHOW_BOOKS_LIST(new ShowBooksListCommand()),
    SHOW_BOOK_INFO(new ShowBookByIdCommand()),
    SHOW_USERS_LIST(new ShowUsersListCommand()),
    UPDATE_AUTHOR(new UpdateAuthorCommand()),
    UPDATE_PUBLISHER(new UpdatePublisherCommand()),
    UPDATE_GENRE(new UpdateGenreCommand()),
    UPDATE_BOOK_DATA(new UpdateBookDataCommand()),
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

    public static CommandType getCommandType(String commandStr){
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandStr.toUpperCase());
        }catch (IllegalArgumentException | NullPointerException e){
            //logger
            commandType = DEFAULT;
        }

        return commandType;
    }
}
