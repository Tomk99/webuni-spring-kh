package hu.webuni.airport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Address {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include()
    private long id;

    private String country;
    private String city;
    private String street;
    private String zip;
}
