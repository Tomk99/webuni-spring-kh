package hu.webuni.airport.web;

import hu.webuni.airport.service.DelayService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class DelayController {

    private final DelayService delayService;

    @GetMapping("/api/flights/{id}/delay")
    @Async
    public CompletableFuture<Integer> getDelayForFlight(@PathVariable long id) {
        System.out.println("DelayController.getDelayForFlight called at thread "+ Thread.currentThread().getName());
        return CompletableFuture.completedFuture(delayService.getDelay(id));
    }
}
