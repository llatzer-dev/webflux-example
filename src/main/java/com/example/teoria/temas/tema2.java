package com.example.teoria.temas;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class tema2 {
    public static void main(String[] args) {
        /*
            Fundamentos de la Programación Reactiva en Java
            Introducción a Reactor: Mono y Flux
            Operadores clave (map, flatMap, filter, etc.)
         */

        /** 1. Fundamentos de la Programación Reactiva en Java (Reactor) **/

        // Mono y Flux: Los Dos Tipos de Flujos
        // Mono<T> → Para representar 0 o 1 elemento
        // Flux<T> → Para representar 0, 1 o muchos elementos
        Mono<String> mono = Mono.just("Hola WebFlux!");
        mono.subscribe(System.out::println); // Output: Hola WebFlux!

        Flux<String> flux = Flux.just("Java", "Spring", "WebFlux");
        flux.subscribe(System.out::println);

        /**  2. Operadores Básicos en Reactor **/

        // map() → Transforma cada elemento
        Flux<Integer> numeros = Flux.just(1, 2, 3, 4)
                .map(n -> n * 2);
        numeros.subscribe(System.out::println); // Output: 2, 4, 6, 8

        Flux<Integer> numeros2 = Flux.just(1, 2, 3, 4)
                .map(Util::cuadrado);
        numeros2.subscribe(System.out::println); // Output: 2, 4, 6, 8

        // flatMap() → Transforma cada elemento en otro Mono o Flux
        Flux<String> flux2 = Flux.just("uno", "dos", "tres")
                .flatMap(palabra -> Flux.just(palabra.toUpperCase()));

        flux2.subscribe(System.out::println);

        // filter() → Filtra elementos según una condición
        Flux<Integer> numeros3 = Flux.just(1, 2, 3, 4, 5)
                .filter(n -> n % 2 == 0);

        numeros3.subscribe(System.out::println); // Output: 2, 4

        // doOnNext(): Ejecución de Acciones Secundarias
        // El operador doOnNext() permite ejecutar una acción antes de que un elemento sea emitido al suscriptor.
        // Se usa para logging, depuración, métricas o efectos secundarios.
        // Se ejecuta antes de que el elemento se envíe al subscribe().
        Flux<String> flujo = Flux.just("A", "B", "C")
                .doOnNext(elemento -> System.out.println("Procesando: " + elemento));

        flujo.subscribe(System.out::println);

        // handle(): Transformación + Filtrado en un Solo Paso
        // El operador handle() combina map() y filter(),
        // permitiendo transformar elementos y descartar otros dentro de una única función.
        Flux<Integer> flujo2 = Flux.just(1, 2, 3, 4, 5)
                .handle((valor, sink) -> {
                    if (valor % 2 == 0) {  // Solo procesamos los pares
                        sink.next(valor * 10); // Transformamos el valor
                    }
                });

        flujo2.subscribe(System.out::println);

        Flux<Integer> flujo3 = Flux.just(1,2,3)
                .handle((valor, sink)-> {
                    if (valor<0) sink.error(new RuntimeException("Error"));
                    else sink.next(valor);
                });

        flujo3.subscribe(System.out::println);

        // switchIfEmpty(): Proveer un Valor Alternativo si el Flujo Está Vacío
        // switchIfEmpty() se usa cuando un Flux o Mono no emite elementos y quieres proveer un valor de respaldo.
        Mono<Object> flujoVacio = Mono.empty()
                .switchIfEmpty(Mono.just("Valor por defecto"));

        flujoVacio.subscribe(System.out::println);


        // doOnSuccess() – Acción cuando un Mono completa con éxito
        // Este operador se ejecuta cuando el Mono emite un valor con éxito (es decir, no está vacío y no hubo errores).
        // Ojo: doOnSuccess solo existe en Mono, no en Flux. Si necesitas algo similar en Flux, puedes usar doOnComplete.
        // Para Mono y Flux
        Mono<String> mono2 = Mono.just("OK")
                .doOnSuccess(valor -> System.out.println("Finalizado con éxito: " + valor));

        mono2.subscribe();


        // doOnError() – Acción cuando ocurre un error
        // Este operador permite manejar efectos secundarios cuando hay un error, útil para logs, métricas, enviar alertas, etc.
        // Para Mono y Flux
        Mono<Object> mono3 = Mono.error(new RuntimeException("¡Error crítico!"))
                .doOnError(e -> System.out.println("Se ha producido un error: " + e.getMessage()));

        mono3.subscribe(
                value -> System.out.println("Valor: " + value),
                error -> System.out.println("Suscriptor recibió error: " + error.getMessage())
        );


        // doOnSubscribe() – Acción al momento de la suscripción
        // Este operador ejecuta una acción cuando el suscriptor se conecta al flujo.
        // Ideal para logs, monitoreo, impresión de auditorías o incluso validaciones.
        // Para Mono y Flux
        Mono<String> mono4 = Mono.just("Inicio del proceso")
                .doOnSubscribe(sub -> System.out.println("Suscripción iniciada"));

        mono4.subscribe(System.out::println);
    }
}
