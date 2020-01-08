package pl.edu.agh.distributedsystems.buildingservice.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public List<Building> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @PostMapping
    public ResponseEntity<Building> addBuilding(@RequestBody Building building) {
        return new ResponseEntity<>(buildingService.addBuilding(building), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBuilding(@PathVariable("id") Long buildingId) {
        try {
            return new ResponseEntity<>(buildingService.findById(buildingId), HttpStatus.OK);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public void deleteBuilding(Long buildingId) {
        buildingService.deleteBuilding(buildingId);
    }
}
