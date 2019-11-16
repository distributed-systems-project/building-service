package pl.edu.agh.distributedsystems.buildingservice.building;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import pl.edu.agh.distributedsystems.buildingservice.building.address.Address;
import pl.edu.agh.distributedsystems.buildingservice.room.Room;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Buildings")
@Data
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;
    private int numOfFloors;
    private double floorArea;
    private String description;

    @OneToMany(mappedBy = "building", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Room> rooms;

    protected Building() {
    }

    public Building(String name, int numOfFloors) {
        this.name = name;
        this.numOfFloors = numOfFloors;
    }
}
