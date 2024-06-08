package hu.webuni.airport.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.airport.model.Airport;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long>{
	
	Long countByIata(String iata);
	
	Long countByIataAndIdNot(String iata, long id);

//	@Query("select a from Airport a left join fetch a.address")
	@EntityGraph(attributePaths = {"address", "departures"/*, "arrivals"*/})
	@Query("select a from Airport a")
	List<Airport> findAllWithAddressAndDepartures(Pageable pageable);

	@EntityGraph(attributePaths = {"arrivals"})
	@Query("select a from Airport a")
	List<Airport> findAllWithArrivals(Pageable pageable);

	@EntityGraph(attributePaths = {"address"})
	@Query("select a from Airport a")
	List<Airport> findAllWithAddress(Pageable pageable);
	@EntityGraph(attributePaths = {"arrivals"})
	@Query("select a from Airport a where a.id in :ids")
	List<Airport> findByIdWithArrivals(List<Long> ids);

	@EntityGraph(attributePaths = {"departures"})
	@Query("select a from Airport a where a.id in :ids")
	List<Airport> findByIdWithDepartures(List<Long> ids);
}
