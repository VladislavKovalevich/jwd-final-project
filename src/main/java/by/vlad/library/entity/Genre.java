package by.vlad.library.entity;

public enum Genre {
    FANTASY,
    FICTION,
    DETECTIVE,
    LOVE_NOVELS,
    THRILLERS,
    ADVENTURES,
    PROSE,
    POETRY;

    public static Genre getGenre(String genreStr){
        return Genre.valueOf(genreStr.toUpperCase());
    }
}
