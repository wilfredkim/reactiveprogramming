package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoServicesTest {
    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @org.junit.jupiter.api.Test
    void vehiclesFlux() {
        var vehiclesFlex = fluxAndMonoServices.vehiclesFlux();
        StepVerifier.create(vehiclesFlex).expectNext("Toyota", "Peugeot", "Mazda", "Mercedes").verifyComplete();
    }
    @org.junit.jupiter.api.Test
    void vehiclesFluxMap() {
        var vehiclesFlex = fluxAndMonoServices.vehiclesFluxMap();
        StepVerifier.create(vehiclesFlex).expectNext("TOYOTA", "PEUGEOT", "MAZDA", "MERCEDES").verifyComplete();
    }

    @org.junit.jupiter.api.Test
    void vehiclesMono() {
        var vehiclesMono = fluxAndMonoServices.vehiclesMono();
        StepVerifier.create(vehiclesMono).expectNext("Peugeot").verifyComplete();
    }

    @Test
    void vehiclesFluxFilter() {
        var vehiclesFlex = fluxAndMonoServices.vehiclesFluxFilter(10);
        StepVerifier.create(vehiclesFlex).expectNext("").verifyComplete();
    }

    @org.junit.jupiter.api.Test
    void vehiclesFluxFlatMap() {
        var vehiclesFlex = fluxAndMonoServices.vehiclesFluxFlatMap();
        StepVerifier.create(vehiclesFlex).expectNextCount(17).verifyComplete();
    }
    @org.junit.jupiter.api.Test
    void vehiclesFluxFlatMapAsync() {
        var vehiclesFlex = fluxAndMonoServices.vehiclesFluxFlatMapAsync();
        StepVerifier.create(vehiclesFlex).expectNextCount(1).verifyComplete();
    }

    @Test
    void vehiclesMonoFlatMapMany() {
        var vehiclesFlex = fluxAndMonoServices.vehiclesMonoFlatMapMany();
        StepVerifier.create(vehiclesFlex).expectNextCount(4).verifyComplete();
    }

    @Test
    void vehiclesFluxTransform() {
        var vehiclesFlex = fluxAndMonoServices.vehiclesFluxTransform(4);
        StepVerifier.create(vehiclesFlex).expectNextCount(4).verifyComplete();
    }
}