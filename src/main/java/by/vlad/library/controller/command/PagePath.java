package by.vlad.library.controller.command;

/**
 * {@code PagePath} class represent container for .jsp pages paths.
 * The class contains only constants with pages path
 */
public class PagePath {
    public static final String INDEX_PAGE = "index.jsp";
    public static final String LOGIN_PAGE = "jsp/pages/common/login.jsp";
    public static final String SHOW_BOOKS_LIST_PAGE = "jsp/pages/common/show_books_list.jsp";
    public static final String SHOW_BOOK_INFO_PAGE = "jsp/pages/common/show_book_info.jsp";
    public static final String CREATE_NEW_ACCOUNT_PAGE = "jsp/pages/common/create_new_account.jsp";
    public static final String CHANGE_PASSWORD_PAGE ="jsp/pages/common/change_account_password.jsp";
    public static final String CHANGE_ACCOUNT_DATA_PAGE ="jsp/pages/common/update_account_data.jsp";
    public static final String USERS_LIST_PAGE = "jsp/pages/admin/users_list.jsp";
    public static final String ADD_NEW_BOOK_PAGE = "jsp/pages/admin/add_book.jsp";
    public static final String UPDATE_BOOK_DATA_PAGE = "jsp/pages/admin/update_book.jsp";
    public static final String ADD_BOOK_COMPONENTS_PAGE = "jsp/pages/admin/add_book_components.jsp";
    public static final String UPDATE_BOOK_COMPONENTS_PAGE = "jsp/pages/admin/update_book_components.jsp";
    public static final String ORDERS_LIST_BY_USER_ID_PAGE = "jsp/pages/common/show_orders_by_user_id.jsp";
    public static final String ORDERS_LIST_PAGE = "jsp/pages/admin/show_orders.jsp";
    public static final String BOOKS_BY_ORDER_ID_PAGE = "jsp/pages/common/order_info.jsp";

    private PagePath(){}
}