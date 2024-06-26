package hu.webuni.airport.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DelayService {

    private Random random = new Random();
    public int getDelay(long flightId) {
        System.out.println("getDelay called");
        return random.nextInt(0,1800);
    }
}
