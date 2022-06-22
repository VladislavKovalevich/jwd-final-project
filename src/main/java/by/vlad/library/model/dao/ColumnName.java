package by.vlad.library.model.dao;

public class ColumnName {
    //BOOK TABLE
    public static final String BOOK_ID_COL = "book_id";
    public static final String BOOK_TITLE_COL = "book_title";
    public static final String BOOK_COPIES_NUMBER_COL = "book_copies_number";
    public static final String BOOK_PUBLISH_YEAR_COL = "book_publish_year";
    public static final String BOOK_NUMBER_OF_PAGES_COL = "book_number_of_pages";
    public static final String BOOK_DESCRIPTION_COL = "book_description";

    public static final String GENRES_ID_COL = "genres_id";
    public static final String PUBLISHERS_ID_COL = "publishers_id";
    public static final String AUTHORS_ID_COL = "authors_id";

    //GENRE TABLE
    public static final String GENRE_ID_COL = "genre_id";
    public static final String GENRE_NAME_COL = "genre_name";

    //PUBLISHER TABLE
    public static final String PUBLISHER_ID_COL = "publisher_id";
    public static final String PUBLISHER_NAME_COL = "publisher_name";

    //AUTHOR TABLE
    public static final String AUTHOR_ID_COL = "author_id";
    public static final String AUTHOR_NAME_COL = "author_name";
    public static final String AUTHOR_SURNAME_COL = "author_surname";

    //USER TABLE
    public static final String USER_ID_COL = "user_id";
    public static final String USER_LOGIN_COL = "user_login";
    public static final String USER_EMAIL_COL = "user_email";
    public static final String USER_PASSWORD_COL = "user_password";
    public static final String USER_NAME_COL = "user_name";
    public static final String USER_SURNAME_COL = "user_surname";
    public static final String USER_PASSPORT_SERIAL_NUMBER_COL = "user_passport_serial_number";
    public static final String USER_MOBILE_PHONE_COL = "user_mobile_phone";
    public static final String USER_IS_BANNED_COL = "user_is_banned";

    //ROLE TABLE
    public static final String ROLE_ID_COL = "role_id";
    public static final String ROLE_NAME_COL = "role_name";

    //ORDER TABLE
    public static final String ORDER_ID_COL = "order_id";
    public static final String ORDER_CREATE_DATE_COL = "order_create_date";
    public static final String ORDER_ORDERED_DATE_COL = "order_ordered_date";
    public static final String ORDER_REJECTED_DATE_COL = "order_rejected_date";
    public static final String ORDER_RETURNED_DATE_COL = "order_returned_date";

    //ORDER STATUS TABLE
    public static final String ORDER_STATUS_ID_COL = "order_status_id";
    public static final String ORDER_STATUS_NAME_COL = "order_status_name";

    //ORDER TYPE TABLE
    public static final String ORDER_TYPE_ID_COL = "order_type_id";
    public static final String ORDER_TYPE_NAME_COL = "order_type_name";

    //IMAGE TABLE
    public static final String IMAGE_ID_COL = "image_id";
    public static final String IMAGE_CONTENT_COL = "image_content";

    public static final String COUNT_COL = "count_col";

    private ColumnName(){}
}
