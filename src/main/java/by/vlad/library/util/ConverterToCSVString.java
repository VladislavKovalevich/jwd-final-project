package by.vlad.library.util;

import by.vlad.library.entity.AbstractEntity;

import java.util.List;

public class ConverterToCSVString {
    private static ConverterToCSVString instance;

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
                  .append(',');
        }

        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }
}
