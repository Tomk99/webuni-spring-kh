package hu.webuni.airport.xmlws;

import hu.webuni.airport.api.model.HistoryDataAirportDto;
import jakarta.jws.WebService;
import jakarta.xml.ws.ResponseWrapper;

import java.util.List;

@WebService
public interface AirportXmlWs {

    public List<HistoryDataAirportDto> getHistoryById(Long id);

    @ResponseWrapper(localName = "getFlightDelayResponse", className = "java.lang.Integer")
    public int getFlightDelay(long flightId);
}
