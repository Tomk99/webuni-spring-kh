package hu.webuni.airport.web;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import hu.webuni.airport.model.HistoryData;
import hu.webuni.airport.repository.AirportRepository;
import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.airport.api.model.AirportDto;
import hu.webuni.airport.mapper.AirportMapper;
import hu.webuni.airport.model.Airport;
import hu.webuni.airport.service.AirportService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@RestController
@RequestMapping("/api/airports")
public class AirportControllerOld {

	private final AirportService airportService;
	private final AirportRepository airportRepository;
	
	private final AirportMapper airportMapper;
	
	
	@GetMapping
	public List<AirportDto> getAll(@RequestParam Optional<Boolean> full, @SortDefault(sort = "id") Pageable pageable){
		boolean isFull = full.orElse(false);
		List<Airport> airports = isFull
				? airportService.findAllWithRelationships(pageable)
//				? airportRepository.findAllWithAddressAndDepartures()
				: airportRepository.findAll(pageable).getContent();
		return isFull ? airportMapper.airportsToDtos(airports) : airportMapper.airportSummariesToDtos(airports);
	}
	
	@GetMapping("/{id}")
	public AirportDto getById(@PathVariable long id) {
		Airport airport = airportService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return airportMapper.airportSummaryToDto(airport);
	}

	@GetMapping("/{id}/history")
	public List<HistoryData<AirportDto>> getHistoryById(@PathVariable long id) {
		List<HistoryData<Airport>> airports = airportService.getAirportHistory(id);

		List<HistoryData<AirportDto>> airportDtosWithHistory = new ArrayList<>();

		airports.forEach(airportHistoryData -> {
			airportDtosWithHistory.add(new HistoryData<>(airportMapper.airportToDto(airportHistoryData.getData()),
					airportHistoryData.getRevType(),
					airportHistoryData.getRevision(),
					airportHistoryData.getDate()
					));
		});

		return airportDtosWithHistory;
	}

	@PostMapping
	public AirportDto createAirport(@RequestBody @Valid AirportDto airportDto) {
		Airport airport = airportService.save(airportMapper.dtoToAirport(airportDto));
		return airportMapper.airportToDto(airport);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AirportDto> modifyAirport(@PathVariable long id, @RequestBody AirportDto airportDto) {
		Airport airport = airportMapper.dtoToAirport(airportDto);
		airport.setId(id);
		try {
			AirportDto savedAirportDto = airportMapper.airportToDto(airportService.update(airport));

			return ResponseEntity.ok(savedAirportDto);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteAirport(@PathVariable long id) {
		airportService.delete(id);
	}
}
