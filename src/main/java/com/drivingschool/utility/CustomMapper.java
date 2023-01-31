package com.drivingschool.utility;

import com.drivingschool.configuration.ApplicationContextProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomMapper {

    private static ModelMapper modelMapper;

    public CustomMapper() {
        modelMapper = ApplicationContextProvider.getApplicationContext().getBean(ModelMapper.class);
    }

    public static <T> T map(Object o, Class<T> clazz) {
        return modelMapper.map(o, clazz);
    }

    public static <T> List<T> map(List<?> objects, Class<T> clazz) {
        return objects.stream()
                .map(item -> map(item, clazz))
                .collect(Collectors.toList());
    }

//
//    public static List<?> mapList(List<?> source, Class<?> distClass) {
//        Type type = new TypeToken<>(){}.getType();
//        modelMapper = ApplicationContext.
//         modelMapper.map(source, distClass);
//         return null;
//    }
}
