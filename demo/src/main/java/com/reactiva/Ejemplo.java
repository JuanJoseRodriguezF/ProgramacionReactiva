package com.reactiva;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

class Weather {
    private final String city;
    private final double temperature;

    public Weather(String city, double temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }
}

class News {
    private final String title;
    private final String category;

    public News(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }
}

class CryptoQuote {
    private final String crypto;
    private final double price;

    public CryptoQuote(String crypto, double price) {
        this.crypto = crypto;
        this.price = price;
    }

    public String getCrypto() {
        return crypto;
    }

    public double getPrice() {
        return price;
    }
}

public class Ejemplo {

    public static void main(String[] args) {

        // Simular APIs externas con retraso
        Mono<Weather> weatherMono = Mono.just(new Weather("New York", 20.5))
                .delayElement(Duration.ofSeconds(1));

        Flux<News> newsFlux = Flux.fromIterable(Arrays.asList(
                new News("Breaking News 1", "Politics"),
                new News("Tech News 1", "Technology"),
                new News("Breaking News 2", "Politics")
        )).delayElements(Duration.ofMillis(500));

        Flux<CryptoQuote> cryptoFlux = Flux.fromIterable(Arrays.asList(
                new CryptoQuote("Bitcoin", 50000),
                new CryptoQuote("Ethereum", 3000)
        )).delayElements(Duration.ofMillis(700));

        // Combinando el clima con noticias y criptomonedas
        Flux<String> combinedFlux = weatherMono.flatMapMany(weather ->
                newsFlux.filter(news -> "Technology".equals(news.getCategory()))
                        .flatMap(news ->
                                cryptoFlux.map(crypto ->
                                        String.format("City: %s, Temperature: %.2f°C, News: %s, Crypto: %s, Price: $%.2f",
                                                weather.getCity(),
                                                weather.getTemperature(),
                                                news.getTitle(),
                                                crypto.getCrypto(),
                                                crypto.getPrice()))
                        )
                        .switchIfEmpty(Flux.just("No technology news available")) // Manejo del caso sin noticias de tecnología
        )
        .concatWith(Flux.just("Additional Info: All data processed!")) // Usar concatWith para agregar el mensaje adicional
        .doOnNext(System.out::println) // Imprimir cada mensaje
        .onErrorResume(e -> {
            System.err.println("Error: " + e.getMessage());
            return Mono.empty(); // Manejar errores
        });

        // Suscribirse al flujo combinado
        combinedFlux.subscribe();

        // Mantener la aplicación en ejecución para observar los resultados
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
