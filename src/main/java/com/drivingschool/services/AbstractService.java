//package com.drivingschool.services;
//
//import com.drivingschool.dto.CityDto;
//import lombok.SneakyThrows;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.List;
//
//public  class AbstractService<T, TDTO> {
//    private ModelMapper modelMapper;
//    private TypeToken<List<? extends TDTO>> entityToDtoType;
//    private Class<TDTO> classInfo;
//    public AbstractService(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//        this.entityToDtoType = new TypeToken<>(){};
//    }
//    @SneakyThrows
//    public List<TDTO> mapToDtoList(List<T> entities) {
//        classInfo = (Class<TDTO>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//        TDTO a = classInfo.newInstance();
//        Type listType = new TypeToken<List<TDTO>>(){}.getType();
//
//        System.out.println(classInfo.getClass());
//        System.out.println(classInfo.getSuperclass());
//        return modelMapper.map(entities, entityToDtoType.getType());
//    }
//}
