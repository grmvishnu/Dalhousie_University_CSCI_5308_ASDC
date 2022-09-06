package com.group18.serviceImpl;

import com.group18.entity.Building;
import com.group18.entity.Role;
import com.group18.entity.User;
import com.group18.entity.dto.request.BuildingRequest;
import com.group18.entity.dto.request.SignupRequest;
import com.group18.entity.dto.response.BuildingResponse;
import com.group18.entity.model.EnumRole;
import com.group18.exception.NotFoundException;
import com.group18.repository.BuildingRepository;
import com.group18.repository.RoleRepository;
import com.group18.service.BuildingService;
import com.group18.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BuildingServiceTest {
    private BuildingServiceImpl buildingService;

    @Mock
    private BuildingRepository buildingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
        buildingService=new BuildingServiceImpl(buildingRepository, modelMapper);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void checkForSuccessfulGetBuildingByUser() throws NotFoundException {
        User mockUser = new User();
        mockUser.setId(1L);

        Building mockBuilding = new Building();
        mockBuilding.setUser(mockUser);
        mockBuilding.setDescription("Lorem ipsum");
        mockBuilding.setId(1L);

        Mockito.when(buildingRepository.findBuildingByUserId(1L)).thenReturn(mockBuilding);
        BuildingResponse actualResponse = buildingService.getBuildingByUser(1L);
        BuildingResponse expectedResponse = new BuildingResponse();
        expectedResponse.setDescription("Lorem ipsum");
        expectedResponse.setId(1L);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void checkForSuccessfulSave() {

        User mockUser = new User();
        mockUser.setId(1L);

        Building mockBuilding = new Building();
        mockBuilding.setUser(mockUser);
        mockBuilding.setDescription("Lorem ipsum");
        mockBuilding.setId(1L);

        BuildingRequest buildingRequest = new BuildingRequest();
        buildingRequest.setUserId(1L);
        buildingRequest.setDescription("Lorem ipsum");
        Mockito.when(buildingRepository.save(Mockito.any(Building.class))).thenReturn(mockBuilding);

        BuildingResponse actualResponse = buildingService.putBuilding(buildingRequest);
        BuildingResponse expectedResponse = new BuildingResponse();
        expectedResponse.setDescription("Lorem ipsum");
        expectedResponse.setId(1L);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void checkForNullWhenNotFound()  {
        assertEquals(null, buildingService.getBuildingByUser(1L));
    }
}
