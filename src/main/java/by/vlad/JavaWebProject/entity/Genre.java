package by.vlad.JavaWebProject.entity;

public enum Genre {
    FANTASY,
    FICTION,
    DETECTIVES,
    LOVE_NOVELS,
    THRILLERS,
    ADVENTURES,
    PROSE,
    POETRY;

    public static Genre getGenre(String genreStr){
        return Genre.valueOf(genreStr.toUpperCase());
    }
}
