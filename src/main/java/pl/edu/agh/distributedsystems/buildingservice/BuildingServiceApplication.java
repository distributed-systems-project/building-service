package pl.edu.agh.distributedsystems.buildingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.distributedsystems.buildingservice.building.Building;
import pl.edu.agh.distributedsystems.buildingservice.building.BuildingRepository;
import pl.edu.agh.distributedsystems.buildingservice.building.address.Address;
import pl.edu.agh.distributedsystems.buildingservice.room.Room;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BuildingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildingServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDummyData(BuildingRepository buildingRepository) {
        List<Building> buildings = createBuildings(6);

        return (args) -> {
            buildingRepository.saveAll(buildings);
        };
    }

    private List<Building> createBuildings(int numOfBuildings) {
        ArrayList<Building> buildings = new ArrayList<>();
        for (int i = 0; i < numOfBuildings; i++) {
            Building building = generateBuilding(i + 1);
            buildings.add(building);
        }
        return buildings;
    }

    private Building generateBuilding(int counter) {
        Building building = new Building("Hotel " + counter, 5);
        Address address = new Address("1", "Street", "111-22", "Krakow", "Poland");
        building.setAddress(address);
        address.setBuilding(building);
        building.setRooms(generateRoomsForAllFloors(building, 5));

        return building;
    }

    private List<Room> generateRoomsForAllFloors(Building building, int roomsPerFloor) {
        List<Room> rooms = new ArrayList<>();
        int roomNumber = 1;
        int numOfFloors = building.getNumOfFloors();
        for (int i = 0; i < numOfFloors; i++) {
            for (int j = 0; j < roomsPerFloor; j++, roomNumber++) {
                Room room = new Room(roomNumber, i + 1);
                room.setBuilding(building);
                room.setRoomType(Room.RoomType.SINGLE);
                rooms.add(room);
            }
        }
        return rooms;
    }
}
