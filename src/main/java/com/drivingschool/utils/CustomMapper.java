package com.drivingschool.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class CustomMapper {
    @Autowired
    private static ModelMapper modelMapper;


    public Class<?> map(Object o, Class<?> clazz) {
        return modelMapper.map(o, clazz).getClass();
    }

//
//    public static List<?> mapList(List<?> source, Class<?> distClass) {
//        Type type = new TypeToken<>(){}.getType();
//        modelMapper = ApplicationContext.
//         modelMapper.map(source, distClass);
//         return null;
//    }
}
