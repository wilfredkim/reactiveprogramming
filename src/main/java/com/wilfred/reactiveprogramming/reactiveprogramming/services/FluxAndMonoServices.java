package com.wilfred.reactiveprogramming.reactiveprogramming.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoServices {

    public Flux<String> vehiclesFlux() {
        return Flux.fromIterable(List.of("Toyota", "Peugeot", "Mazda", "Mercedes")).log();
    }

    public Flux<String> vehiclesFluxMap() {
        return Flux.fromIterable(List.of("Toyota", "Peugeot", "Mazda", "Mercedes")).map(String::toUpperCase).log();
    }

    public Flux<String> vehiclesFluxFilter(int length) {
        return Flux.fromIterable(List.of("Toyota", "Peugeot", "Mazda", "Mercedes")).
                filter(s -> s.length() >= length).log();
    }

    public Flux<String> vehiclesFluxFlatMap() {
        return Flux.fromIterable(List.of("Toyota", "Peugeot", "Mazda", "Mercedes")).
                flatMap(s -> Flux.just(s.split(""))).log();
    }

    public Flux<String> vehiclesFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Toyota", "Peugeot", "Mazda", "Mercedes")).
                flatMap(s -> Flux.just(s.split("")).delayElements(Duration.ofMillis(new Random().nextInt(1000)))).log();
    }

    public Mono<String> vehiclesMono() {
        return Mono.just("Peugeot").log();
    }

    public Flux<String> vehiclesMonoFlatMapMany() {
        return Mono.just("Peugeot")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }
    public Flux<String> vehiclesFluxTransform(int length) {
        Function<Flux<String>, Flux<String>> filterData =
                data->data.filter(s->s.length()>length);
        return Flux.fromIterable(List.of("Toyota", "Peugeot", "Mazda", "Mercedes")).
                transform(filterData).log();
    }
    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();
        fluxAndMonoServices.vehiclesFlux().subscribe(s -> System.out.println("Vehicles:Flux:::::::::" + s));
        fluxAndMonoServices.vehiclesMono().subscribe(s -> System.out.println("Vehicles::Mono:::::::" + s));
    }
}
