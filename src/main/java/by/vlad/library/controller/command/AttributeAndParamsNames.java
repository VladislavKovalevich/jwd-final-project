package by.vlad.library.controller.command;

/**
 * {@code AttributeAndParamsNames} class represent container for attributes and params names
 * The class contains only constants
 */
public class AttributeAndParamsNames {
    private AttributeAndParamsNames(){
    }

    //session attributes names
    public static final String BOOK_FORM_DATA = "book_form_data";

    public static final String TITLE_FORM = "title_form";
    public static final String AUTHOR_FORM = "author_form";
    public static final String GENRE_FORM = "genre_form";
    public static final String PUBLISHER_FORM = "publisher_form";
    public static final String COPIES_NUMBER_FORM = "copies_number_form";
    public static final String RELEASE_YEAR_FORM = "release_year_form";
    public static final String PAGES_COUNT_FORM = "pages_count_form";
    public static final String DESCRIPTION_FORM = "description_form";

    public static final String WRONG_TITLE_EXISTS_FORM = "wrong_title_exists_form";
    public static final String WRONG_TITLE_FORM = "wrong_title_form";
    public static final String WRONG_COPIES_NUMBER_FORM = "wrong_copies_number_form";
    public static final String WRONG_RELEASE_YEAR_FORM = "wrong_release_year_form";
    public static final String WRONG_PAGES_COUNT_FORM = "wrong_pages_count_form";
    public static final String WRONG_DESCRIPTION_FORM = "wrong_description_form";

    public static final String BOOK_COMPONENTS_FORM_DATA = "book_components_form_data";

    public static final String AUTHOR_NAME_FORM = "author_name_form";
    public static final String AUTHOR_SURNAME_FORM = "author_surname_form";

    public static final String WRONG_AUTHOR_NAME_FORM = "wrong_author_name_form";
    public static final String WRONG_AUTHOR_SURNAME_FORM = "wrong_author_surname_form";
    public static final String WRONG_AUTHOR_EXISTS_FORM = "wrong_author_exists_form";

    public static final String PUBLISHER_NAME_FORM = "publisher_name_form";
    public static final String WRONG_PUBLISHER_NAME_FORM = "wrong_publisher_name_form";
    public static final String WRONG_PUBLISHER_EXISTS_FORM = "wrong_publisher_exists_form";

    public static final String GENRE_NAME_FORM = "genre_name_form";
    public static final String WRONG_GENRE_NAME_FORM = "wrong_genre_name_form";
    public static final String WRONG_GENRE_EXISTS_FORM = "wrong_genre_exists_form";

    public static final String WRONG_EMAIL_OR_PASS = "wrong_email_or_pass";
    public static final String NOT_FOUND_USER = "not_found_user";
    public static final String USER_IS_BANNED = "user_is_banned";

    public static final String USER_FORM_DATA = "user_form_data";

    public static final String WRONG_NAME_FORM = "wrong_name_form";
    public static final String WRONG_SURNAME_FORM = "wrong_surname_form";
    public static final String WRONG_LOGIN_FORM = "wrong_login_form";
    public static final String WRONG_PHONE_NUMBER_FORM = "wrong_phone_number_form";
    public static final String WRONG_SERIAL_NUMBER_FORM = "wrong_serial_number_form";
    public static final String WRONG_EMAIL_FORM = "wrong_email_form";
    public static final String WRONG_PASSWORD_FORM = "wrong_pass_form";
    public static final String WRONG_PASSWORD_VALUE = "wrong_pass_value";
    public static final String WRONG_EMAIL_EXISTS_FORM = "wrong_email_exists_form";
    public static final String WRONG_REPEAT_PASSWORD_FORM = "wrong_repeat_pass_form";
    public static final String WRONG_NEW_PASSWORD_FORM = "wrong_new_pass_form";
    public static final String WRONG_NEW_REPEAT_PASSWORD_FORM = "wrong_new_repeat_pass_form";

    public static final String NAME_FORM = "name_form";
    public static final String SURNAME_FORM = "surname_form";
    public static final String LOGIN_FORM = "login_form";
    public static final String PHONE_NUMBER_FORM = "phone_number_form";
    public static final String SERIAL_NUMBER_FORM = "serial_number_form";
    public static final String EMAIL_FORM = "email_form";
    public static final String PASSWORD_FORM = "pass_form";
    public static final String REPEAT_PASSWORD_FORM = "repeat_pass_form";
    public static final String NEW_PASSWORD_FORM = "new_pass_form";
    public static final String NEW_REPEAT_PASSWORD_FORM = "new_repeat_pass_form";

    public static final String PAGINATION_DATA = "pagination_data";
    public static final String CURRENT_PAGE_NUM = "current_page_num";
    public static final String PAGES_NUMBER = "pages_number";
    
    public static final String USER_ID = "user_id";
    public static final String USER_LOGIN = "user_login";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_ROLE = "user_role";

    public static final String LOCALE = "locale";

    public static final String CURRENT_PAGE = "current_page";

    public static final String ADD_BOOK_MSG = "add_book_msg";

    public static final String PUBLISHER_OPERATION_FEEDBACK = "publisher_operation_feedback";
    public static final String AUTHOR_OPERATION_FEEDBACK = "author_operation_feedback";
    public static final String GENRE_OPERATION_FEEDBACK = "genre_operation_feedback";
    public static final String BOOK_OPERATION_FEEDBACK = "book_operation_feedback";
    public static final String ORDER_OPERATION_FEEDBACK = "order_operation_feedback";

    public static final String BOOK_IS_ALREADY_EXISTS = "book_is_already_exists";

    public static final String FILTER_DATA = "filter_data";

    public static final String USER_ORDERS = "user_orders";

    public static final String CREATE_ACCOUNT_FEEDBACK = "create_account_feedback";

    public static final String ORDER_STATUS = "order_status";
    public static final String ORDER_TYPES = "order_types";

    public static final String BOOK_ORDER_DATA = "book_order_data";
    public static final String BOOK_LIMIT_IN_ORDER = "book_limit_in_order";
    public static final String ORDERS_LIMIT = "orders_limit";

    ////request params names
    public static final String PAGE_DIRECTION = "page_direction";

    public static final String COMMAND = "command";
    public static final String LANGUAGE = "language";

    public static final String USER_ID_REQ_PARAM = "user_id";
    public static final String USER_STATUS = "user_status";

    public static final String ORDER_BOOKS = "order_books";
    public static final String ORDER_TYPE = "order_type";
    public static final String ORDER_STATUS_ID = "order_status_id";

    //user params
    public static final String EMAIL = "email";
    public static final String PASSWORD = "pass";
    public static final String LOGIN = "login";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String SERIAL_NUMBER = "serial_number";
    public static final String REPEATED_PASSWORD = "repeated_pass";
    public static final String NEW_PASSWORD = "new_pass";
    public static final String NEW_REPEAT_PASSWORD = "new_repeat_pass";

    //book params
    public static final String BOOK_ID = "book_id";
    public static final String IMAGE_ID = "image_id";

    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String GENRE = "genre";
    public static final String PUBLISHER = "publisher";
    public static final String COPIES_NUMBER = "copies_number";
    public static final String RELEASE_YEAR = "release_year";
    public static final String PAGES_COUNT = "pages_count";
    public static final String DESCRIPTION = "description";

    public static final String IS_EXISTS = "is_exists";

    //book components params
    public static final String AUTHOR_NAME = "author_name";
    public static final String AUTHOR_SURNAME = "author_surname";
    public static final String PUBLISHER_NAME = "publisher_name";
    public static final String GENRE_NAME = "genre_name";

    public static final String ORDER_ID = "order_id";

    public static final String IMAGE = "image";

    ////request attributes names

    public static final String USERS_LIST = "users_list";

    public static final String BOOK = "book";
    public static final String BOOKS_LIST = "books_list";

    public static final String AUTHORS = "authors";
    public static final String PUBLISHERS = "publishers";
    public static final String GENRES = "genres";

    public static final String ORDERS = "orders";

    public static final String ORDER = "order";
}