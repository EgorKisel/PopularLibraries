package com.example.popularlibraries.test

import io.reactivex.rxjava3.core.Observable

// операторы, манипулирующие потоком
class Operators {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun createJust() = Observable.just("1", "2", "3", "3")
    }

    class Consumer(val producer: Producer) {
        fun exec() {
            execFilter()
        }

        // Оператор take(count) берёт первые count элементов цепочки
        fun execTake() {
            producer.createJust().take(2).subscribe({ s ->
                println("onNext: $s")
            }, {
                println("onError ${it.message}")
            })
        }

        // skip позволяет пропустить несколько первых элементов
        fun execSkip() {
            producer.createJust().skip(2).subscribe({ s ->
                println("onNext: $s")
            }, {
                println("onError: ${it.message}")
            })
        }

        // оператор map преобразует элементы цепочки согласно
        //переданному ему правилу
        fun execMap() {
            producer.createJust().map { it + it }
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        // Оператор distinct, как следует из его названия, отсеивает дубликаты
        fun execDistinct() {
            producer.createJust().distinct().subscribe({ s ->
                println("onNext: $s")
            }, {
                println("onError: ${it.message}")
            })
        }
        // оператор filter фильтрует строки по определенному правилу
        fun execFilter() {
            producer.createJust().filter() { it.toInt() > 1 }
                .subscribe({s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }
    }
}