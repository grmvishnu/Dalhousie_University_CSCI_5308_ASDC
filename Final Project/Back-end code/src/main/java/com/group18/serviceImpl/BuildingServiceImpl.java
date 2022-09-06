package com.group18.serviceImpl;

import com.group18.entity.Building;
import com.group18.entity.dto.request.BuildingRequest;
import com.group18.entity.dto.response.BuildingResponse;
import com.group18.exception.NotFoundException;
import com.group18.repository.BuildingRepository;
import com.group18.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BuildingServiceImpl(BuildingRepository buildingRepository, ModelMapper modelMapper) {
        this.buildingRepository = buildingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BuildingResponse putBuilding(BuildingRequest buildingRequest) {
        Building building = modelMapper.map(buildingRequest, Building.class);
        Building savedBuilding = buildingRepository.save(building);
        BuildingResponse buildingResponse = modelMapper.map(savedBuilding, BuildingResponse.class);
        return buildingResponse;
    }

    @Override
    public BuildingResponse getBuildingByUser(Long userId) {
        Building building = buildingRepository.findBuildingByUserId(userId);
        if (building == null) {
            return null;
        }
        BuildingResponse buildingResponse = modelMapper.map(building, BuildingResponse.class);
        return buildingResponse;
    }
}
