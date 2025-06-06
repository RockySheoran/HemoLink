package com.project.hemolink.matching_service.config;

import com.project.hemolink.matching_service.dto.BloodRequestDto;
import com.project.hemolink.matching_service.dto.PointDTO;
import com.project.hemolink.matching_service.entities.BloodRequest;
import com.project.hemolink.matching_service.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper =  new ModelMapper();

        mapper.typeMap(PointDTO.class, Point.class).setConverter(context -> {
            PointDTO pointDTO = context.getSource();
            return GeometryUtil.createPoint(pointDTO);
        });
        mapper.typeMap(Point.class, PointDTO.class).setConverter(context ->{
            Point point = context.getSource();
            double[] coordinates = {
                    point.getX(),
                    point.getY()
            };
            return new PointDTO(coordinates);
        });

        // Configure BloodRequest to BloodRequestDto mapping
        mapper.typeMap(BloodRequest.class, BloodRequestDto.class)
                .addMappings(modMapper -> {
                    modMapper.map(BloodRequest::getId, BloodRequestDto::setId);
                    modMapper.map(BloodRequest::getHospitalId, BloodRequestDto::setHospitalId);
                });
        return mapper;
    }
}
