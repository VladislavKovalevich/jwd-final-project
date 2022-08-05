package by.vlad.library.util.secrectkeygenerator;

public interface PasswordCodeGenerator {
    int MIN_VALUE = 10_000;
    int MAX_VALUE = 99_999;

    int generate();
}
