package com.example.teoria.temas;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class tema1 {
    public static void main(String[] args) {
        /*
            Repaso de funciones lambda en Java
            Expresiones lambda y sintaxis
            Uso de Function<T, R>, Consumer<T>, Supplier<T> y Predicate<T>
            Referencias a métodos (::) y funciones de orden superior
         */


        /** 1. Introducción a las Lambdas en Java **/
        // Las expresiones lambda permiten escribir funciones de forma concisa sin necesidad de crear clases anónimas.
        // Suelen usarse con interfaces funcionales (interfaces con un solo método abstracto).

        // Forma tradicional (clase anónima)
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hola desde una Runnable!");
            }
        };

        // Usando lambda
        Runnable tareaLambda = () -> System.out.println("Hola desde una lambda!");

        tarea.run();
        tareaLambda.run();

        /** 2. Uso de Interfaces Funcionales Comunes **/
        // En Java, existen varias interfaces funcionales en java.util.function.

        // Function<T, R> (Recibe un argumento y devuelve un resultado
        Function<String, Integer> longitud = s -> s.length();
        System.out.println(longitud.apply("WebFlux")); // Output: 7

        // Consumer<T> (Recibe un argumento y no devuelve nada.
        Consumer<String> imprimir = s -> System.out.println("Mensaje: " + s);
        imprimir.accept("Hola desde WebFlux!");

        // Supplier<T> (No recibe argumentos, solo devuelve un valor)
        Supplier<Double> aleatorio = () -> Math.random();
        System.out.println(aleatorio.get()); // Devuelve un número aleatorio

        // Predicate<T> (Recibe un argumento y devuelve true o false)
        Predicate<Integer> esPar = num -> num % 2 == 0;
        System.out.println(esPar.test(4)); // Output: true
        System.out.println(esPar.test(5)); // Output: false



        /** 3. Referencias a Métodos (::) **/
        // Podemos simplificar aún más las expresiones lambda usando referencias a métodos.

        Function<String, Integer> longitud2= String::length;
        System.out.println(longitud2.apply("Lambda")); // Output: 6

        Consumer<String> imprimir2 = System.out::println;
        imprimir2.accept("Hola desde una referencia a método!");

        Function<Integer, Integer> elevarAlCuadrado = Util::cuadrado;
        System.out.println(elevarAlCuadrado.apply(5)); // Output: 25
    }
}
