# ProgramacionReactiva
## 1. Adaptacion del entorno:

Añadimos las dependencias de RxJava y reactor.

![alt text](img/Configuración.png "Logo Title Text 1")

## 2.1 Operador RxJava:

Observamos los operadores map, filter, flatMap, merge, zip con RxJava.

### Map:

![alt text](img/MapRx.PNG "Logo Title Text 2")

### Filter:

![alt text](img/FilterRx.PNG "Logo Title Text 3")

### Flat:

![alt text](img/FlatRx.PNG "Logo Title Text 4")

### Merge:

![alt text](img/MergeRx.PNG "Logo Title Text 5")

### Zip:

![alt text](img/ZipRx.PNG "Logo Title Text 6")

## 2.2 Operador Reactor:

Observamos los operadores map, filter, flatMap, merge, zip con Reactor.

### Map:

![alt text](img/MapReactor.PNG "Logo Title Text 7")

### Filter:

![alt text](img/FilterReactor.PNG "Logo Title Text 8")

### Flat:

![alt text](img/FlatReactor.PNG "Logo Title Text 9")

### Merge:

![alt text](img/MergeReactor.PNG "Logo Title Text 10")

### Zip:

![alt text](img/ZipReactor.PNG "Logo Title Text 11")

## Contexto: 

Combinación de diferentes datos de distintas APIs en tiempo real enfocado al clima.

## Operadores usados:

1. `Mono.just` : Se usa para crear un Mono que emite un único objeto Weather.
2. `Flux.fromIterable` : Se usa para crear flujos (Flux) de noticias (News) y cotizaciones de criptomonedas (CryptoQuote).
3. `delayElement` : Introducen retrasos en las emisiones para simular latencia de APIs externas.
4. `flatMapMany` : Transforma un elemento de un Mono en un Flux, descomponiendo la estructura.
5. `filter` : Filtra las tuplas para incluir solo aquellas donde la categoría de la noticia sea "Technology".
6. `flatMap` : Introduce un procesamiento adicional simulado con un retraso en la emisión de cada mensaje y desempaca las listas de buffer para mantener un flujo continuo.
7. `switchIfEmpty` : Proporciona un Mono alternativo si el flujo original está vacío.
8. `concatWith` : Combina el flujo original con otro Publisher, emitiendo primero el original y luego el adicional.
9. `doOnNext` : Imprime cada mensaje emitido.
10. `onErrorResume` : Proporciona una ruta alternativa en caso de error, imprimiendo el error y continuando con un Mono.empty().

## Salida: 

![alt text](img/Salida.PNG "Logo Title Text 12")