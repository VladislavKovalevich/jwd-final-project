package by.vlad.library.util.secrectkeygenerator.impl;

import by.vlad.library.util.secrectkeygenerator.PasswordCodeGenerator;

import java.util.Random;

public class PasswordCodeGeneratorImpl implements PasswordCodeGenerator {
    private static PasswordCodeGeneratorImpl instance;
    private static Random random;

    public static PasswordCodeGeneratorImpl getInstance(){
        if (instance == null){
            instance = new PasswordCodeGeneratorImpl();
        }

        return instance;
    }

    private PasswordCodeGeneratorImpl(){
        random = new Random();
    }

    @Override
    public int generate() {
        return MIN_VALUE + random.nextInt(MAX_VALUE - MIN_VALUE);
    }
}
