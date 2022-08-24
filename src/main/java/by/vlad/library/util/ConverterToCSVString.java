package by.vlad.library.util;

import by.vlad.library.entity.AbstractEntity;

import java.util.List;

public class ConverterToCSVString {
    private static ConverterToCSVString instance;
    private static final char COMMA = ',';

    public static ConverterToCSVString getInstance(){
        if (instance == null){
            instance = new ConverterToCSVString();
        }

        return instance;
    }

    private ConverterToCSVString(){
    }

    public String convertEntityList(List<? extends AbstractEntity> entityList){
        StringBuilder result = new StringBuilder();

        for (AbstractEntity entity: entityList) {
            result.append(entity.getId())
                  .append(COMMA);
        }

        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }
}
