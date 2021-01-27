package com.example.rxjava.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rx.Observable;
import rx.Subscriber;

@SpringBootApplication
public class RxjavaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RxjavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		maneraSimplificada();
	}

	/**
	 * Crear Observable, Suscriptor y suscribirse al evento (Nativo)
	 */
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public void maneraClasica() {

		/**
		 * Paso 1: Crear Observable con emisión de flujo de datos númerico
		 */
		Observable<Subscriber> observable = Observable
				.create(new Observable.OnSubscribe<Subscriber>() {

			// El método call por defecto llama el método start() para el Observable
			@Override
			public void call(Subscriber subscriber) {
				// Emisión de datos (Enteros)
				subscriber.onNext(1);
				subscriber.onNext(2);
				subscriber.onNext(3);
				subscriber.onNext(4);
				subscriber.onNext(5);
				// Finalización del observable
				subscriber.onCompleted();
			}
		});

		/**
		 * PASO 2: Crear Subscriber el cual escuchará los eventos del Observable
		 */
		Subscriber subscriber = new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted!");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("Error" + e.getMessage());
			}

			@Override
			public void onNext(Integer t) {
				/*if (t == 3) {
					throw new Error("No se permite el número 3");
				}*/
				System.out.println("Valor: " + t);
			}
		};

		/**
		 * Paso 3: subscribirse al Observable
		 */
		observable.subscribe(subscriber);
	}

	/**
	 * Manera simplificada para crear Observable y suscribirse a el
	 */
	public void maneraSimplificada() {
		// Con el just se genera el observable con el flujo de datos a emitir
		Observable.just(1, 2, 3, 4, 5)
		.subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted!");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("Error" + e.getMessage());
			}

			@Override
			public void onNext(Integer t) {
				/*if (t == 3) {
					throw new Error("No se permite el número 3");
				}*/
				System.out.println("Valor: " + t);
			}
		});
	}

}
