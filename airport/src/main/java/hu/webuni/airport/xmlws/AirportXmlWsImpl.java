package hu.webuni.airport.xmlws;

import hu.webuni.airport.api.model.HistoryDataAirportDto;
import hu.webuni.airport.mapper.HistoryDataMapper;
import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.HistoryData;
import hu.webuni.airport.service.AirportService;
import hu.webuni.airport.service.DelayService;
import jakarta.xml.ws.AsyncHandler;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.annotations.UseAsyncMethod;
import org.apache.cxf.jaxws.ServerAsyncResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class AirportXmlWsImpl implements AirportXmlWs{

    private final AirportService airportService;
    private final HistoryDataMapper historyDataMapper;
    private final DelayService delayService;

    @Override
    public List<HistoryDataAirportDto> getHistoryById(Long id) {
        List<HistoryData<Airport>> airports = airportService.getAirportHistory(id);

        List<HistoryDataAirportDto> airportDtosWithHistory = new ArrayList<>();

        airports.forEach(airportHistoryData -> {
            airportDtosWithHistory.add(historyDataMapper.airportHistoryDataToDto(airportHistoryData));
        });

        return airportDtosWithHistory;
    }

    @Override
    @UseAsyncMethod
    public int getFlightDelay(long flightId) {
        return 0;
    }

    public Future<Integer> getFlightDelayAsync(long flightId, AsyncHandler<Integer> asyncHandler) {
        ServerAsyncResponse<Integer> serverAsyncResponse = new ServerAsyncResponse<>();
        System.out.println(Thread.currentThread().getName());

        delayService.getDelayAsync(flightId).thenAccept(result -> {
            System.out.println(Thread.currentThread().getName());
            serverAsyncResponse.set(result);
            asyncHandler.handleResponse(serverAsyncResponse);
        });

        return serverAsyncResponse;
    }
}
