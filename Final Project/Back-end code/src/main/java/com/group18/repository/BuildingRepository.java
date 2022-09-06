package com.group18.repository;

import com.group18.entity.Building;
import org.springframework.data.repository.CrudRepository;

public interface BuildingRepository extends CrudRepository<Building, Long> {
    Building findBuildingByUserId(Long id);
}
