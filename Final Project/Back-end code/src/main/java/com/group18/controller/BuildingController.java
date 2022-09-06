package com.group18.controller;

import com.group18.entity.dto.request.BuildingRequest;
import com.group18.entity.dto.response.BuildingResponse;
import com.group18.exception.NotFoundException;
import com.group18.serviceImpl.BuildingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("building")
public class BuildingController {
    @Autowired
    private BuildingServiceImpl buildingService;

    @PutMapping
    public ResponseEntity<BuildingResponse> updateBuilding(BuildingRequest buildingRequest) {
        BuildingResponse buildingResponse = buildingService.putBuilding(buildingRequest);
        return new ResponseEntity<>(buildingResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<BuildingResponse> getBuildingForUser(@PathVariable String userId) throws NotFoundException {
        BuildingResponse buildingResponse = buildingService.getBuildingByUser(Long.parseLong(userId));
        return new ResponseEntity<>(buildingResponse, HttpStatus.OK);
    }
}
