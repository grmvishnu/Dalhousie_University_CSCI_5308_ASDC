package com.group18.service;

import com.group18.entity.dto.request.BuildingRequest;
import com.group18.entity.dto.response.BuildingResponse;
import com.group18.exception.NotFoundException;

public interface BuildingService {

    BuildingResponse putBuilding(BuildingRequest buildingRequest);

    BuildingResponse getBuildingByUser(Long userId) throws NotFoundException;
}
