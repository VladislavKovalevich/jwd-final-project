package by.vlad.JavaWebProject.entity;

public enum Genre {
    FANTASY,
    FICTION,
    DETECTIVES,
    LOVE_NOVELS,
    THRILLERS,
    HORROR,
    ADVENTURES,
    PROSE,
    POETRY;

    public static Genre getRole(String genreStr){
        return Genre.valueOf(genreStr.toUpperCase());
    }
}
