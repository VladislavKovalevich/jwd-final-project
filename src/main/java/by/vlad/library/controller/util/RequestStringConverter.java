package by.vlad.library.controller.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class RequestStringConverter {
    private static RequestStringConverter instance;

    public static RequestStringConverter getInstance() {
        if (instance == null){
            instance = new RequestStringConverter();
        }
        return instance;
    }

    private RequestStringConverter(){}

    public void convertStringToIdArray(HttpServletRequest request, String reqParam, Map<String, Long[]> map){
        Enumeration<String> paramEnum = request.getParameterNames();
        List<Long> idList = new ArrayList<>();

        while(paramEnum.hasMoreElements()){
            String reqParamElement = paramEnum.nextElement();
            if (reqParamElement.matches(reqParam + "\\d+")){
                idList.add(Long.valueOf(reqParamElement.split(reqParam)[1]));
            }
        }

        if (!idList.isEmpty()) {
            map.put(reqParam, idList.toArray(new Long[0]));
        }
    }
}
