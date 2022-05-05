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
        Genre genre;
        try{
            genre = Genre.valueOf(genreStr.toUpperCase());
        }catch (IllegalArgumentException | NullPointerException e){
            //logger
            genre = FICTION;
        }
        return genre;
    }
}
