package com.drivingschool.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;


public class CustomMapper {
    private static ModelMapper modelMapper;


//
//    public static List<?> mapList(List<?> source, Class<?> distClass) {
//        Type type = new TypeToken<>(){}.getType();
//        modelMapper = ApplicationContext.
//         modelMapper.map(source, distClass);
//         return null;
//    }
}
