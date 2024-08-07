package hu.webuni.airport.xmlws;

import hu.webuni.airport.api.model.HistoryDataAirportDto;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface AirportXmlWs {

    public List<HistoryDataAirportDto> getHistoryById(Long id);
}
