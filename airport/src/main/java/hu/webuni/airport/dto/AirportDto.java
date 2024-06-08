package hu.webuni.airport.dto;

import jakarta.validation.constraints.Size;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirportDto {
	
	private long id;
	@Size(min = 3, max = 20)
	private String name;
	private String iata;

	private AddressDto address;
	private List<FlightDto> departures;
	private List<FlightDto> arrivals;
}
