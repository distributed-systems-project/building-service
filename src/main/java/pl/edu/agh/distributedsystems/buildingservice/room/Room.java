package pl.edu.agh.distributedsystems.buildingservice.room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.distributedsystems.buildingservice.building.Building;

import javax.persistence.*;

@Entity
@Table(name = "Rooms")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private int roomNumber;
    private int floorNumber;
    private double area;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "fk_building")
    @JsonBackReference
    private Building building;

    public Room(int roomNumber, int floorNumber) {
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
    }

    public enum RoomType {
        SINGLE,
        DOUBLE,
        TRIPLE,
        QUAD,
        TWIN,
        STUDIO
    }
}
