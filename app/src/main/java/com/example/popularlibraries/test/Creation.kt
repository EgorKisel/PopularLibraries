package com.example.popularlibraries.test

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlin.random.Random

class Creation {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        // В оператор just передаётся набор элементов
        fun just(): Observable<String> {
            return Observable.just("1", "2", "3")
        }

        // Оператор fromIterable похож на just, но в него передаётся не набор, а коллекция элементов
        fun fromIterable(): Observable<String> {
            return Observable.fromIterable(listOf("1", "2", "3"))
        }

        // Последовательно выдаст числа от первого аргумента до второго
        fun range() = Observable.range(1, 10)

        //Симуляция длительной операции со случайным исходом
        fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }
        // fromCallable принимает на вход процедуру и возвращает источник, который выдаст
        //возвращаемое значение этой процедуры
        fun fromCallable() = Observable.fromCallable {
            //Симуляция долгих вычислений
            val result = randomResultOperation()
            return@fromCallable result
        }
        // Оператор create предполагает полностью ручное
        //управление событиями, которые выдаёт источник
        fun create() = Observable.create<String> { emitter ->
            try {
                for (i in 0..10) {
                    randomResultOperation().let {
                        if (it) {
                            emitter.onNext("Success: $i")
                        } else {
                            emitter.onError(RuntimeException("Error"))
                            return@create
                        }
                    }
                }
                emitter.onComplete()
            } catch (t: Throwable) {
                emitter.onError(RuntimeException("Error"))
            }
        }
    }

    class Consumer(val producer: Producer) {
        val stringObserver = object : Observer<String> {
            var disposable: Disposable? = null
            override fun onSubscribe(d: Disposable) {
                disposable = d
                println("onSubscribe")
            }

            override fun onError(e: Throwable) {
                println("onError: ${e.message}")
            }

            override fun onComplete() {
                println("onComplete")
            }

            override fun onNext(t: String) {
                println("onNext: $t")
            }

        }

        // Подписка на Observable посредством набора лямбд
        fun execLambda() {
            producer.just().subscribe({ s ->
                println("onNext: $s")
            }, { e ->
                println("onError: ${e.message}")
            }, {
                println("onComplete")
            })
        }

        // Подписка на Observable посредством экземпляра Observer
        fun execJust() {
            producer.just().subscribe(stringObserver)
        }

        fun execFromIterable() {
            producer.fromIterable().subscribe(stringObserver)
        }

        fun execRange() {
            producer.range().subscribe {
                println("onNext: $it")
            }
        }
        // FIXME OnErrorNotImplementedException
        fun execCreate() {
            producer.create().subscribe {
                println("onNext: $it")
            }
        }

        fun exec() {
            execRange()
        }
    }
}