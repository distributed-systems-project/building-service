package pl.edu.agh.distributedsystems.buildingservice.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class BuildingService {

    private BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    void addBuilding(Building building) {
        System.out.println(building);
        buildingRepository.save(building);
    }

    Building findById(Long buildingId) {
        if (buildingId <= 0) throw new IllegalArgumentException("Building id must be number greater than zero");
        return buildingRepository.findById(buildingId).orElseThrow(NoSuchElementException::new);
    }

    void deleteBuilding(long buildingId) {
        buildingRepository.deleteById(buildingId);
    }
}
