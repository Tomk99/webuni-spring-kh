package hu.webuni.airport.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Flight {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private long id;
	
	private String flightNumber;
	private LocalDateTime takeoffTime;

	@ManyToOne
	private Airport takeoff;
	
	@ManyToOne
	private Airport landing;

	@Column(name = "delay")
	private Integer delayInSec;
}
