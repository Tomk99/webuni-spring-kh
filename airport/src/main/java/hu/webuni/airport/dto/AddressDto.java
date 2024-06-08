package hu.webuni.airport.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
public class AddressDto {

    private long id;

    private String country;
    private String city;
    private String street;
    private String zip;
}
