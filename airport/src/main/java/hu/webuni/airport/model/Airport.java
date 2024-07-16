package hu.webuni.airport.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Cacheable
@Audited
public class Airport {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include()
	private long id;

	@Size(min = 3, max = 20)
	private String name;
	private String iata;

	@ManyToOne(fetch = FetchType.LAZY)
	private Address address;

	@OneToMany(mappedBy = "takeoff"/*,fetch = FetchType.EAGER*/)
	//@Fetch(FetchMode.SUBSELECT)
	private Set<Flight> departures;
	@OneToMany(mappedBy = "landing")
	private Set<Flight> arrivals;
	@OneToMany
	@JoinColumn(name = "airport_id")
	private Set<Image> images;

	public Airport(String name, String iata) {
		this.name = name;
		this.iata = iata;
	}
}
