package pl.edu.agh.distributedsystems.buildingservice.building.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.distributedsystems.buildingservice.building.Building;

import javax.persistence.*;

@Entity
@Table(name = "Addresses")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id
    @Column(name = "address_id")
    private Long id;

    @MapsId
    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Building building;

    private String houseNumber;
    private String street;
    private String zipCode;
    private String city;
    private String country;

    public Address(String houseNumber, String street, String zipCode, String city, String country) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }
}
