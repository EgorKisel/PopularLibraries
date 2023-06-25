package com.example.popularlibraries.test

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class BackPressure {

    /*
     Flowable аналогичен Observable и отличается от него поддержкой механизма BackPressure.
Назначение BackPressure — корректная обработка значений, выдаваемых источником, когда их
настолько много, что код не успевает их обработать. Например, если мы получаем частые сообщения
из сетевого сокета. Такая ситуация может привести к тому, что неуспевающие обработчики «съедят»
всю память, а приложение упадёт с OutOfMemoryException. BackPressure позволяет распорядиться
теми значениями, которые выдаёт источник, когда обработка предыдущих ещё не завершилась.
    Есть несколько стратегий, применяемых через соответствующие операторы:
1. onBackpressureBuffer() — сохранять значения и обрабатывать по мере возможности. В
качестве аргумента обычно передаётся максимальный размер буфера.
2. onBackpressureDrop() — выбрасывать лишние значения.
3. onBackpressureLatest() — выбрасывать все значения, кроме последнего.
      */

    fun exec() {
        val producer = Producer()
        val consumer = Consumer(producer)
        consumer.consume()
    }

    class Producer {
        fun noBackPressure() = Observable.range(0, 10000000)
            .subscribeOn(Schedulers.io())

        fun backPressure() = Flowable.range(0, 10000000)
            .subscribeOn(Schedulers.io()).onBackpressureLatest()
    }

    class Consumer(private val producer: Producer) {

        fun consume() {
            //consumeNoBackPressure()
            consumeBackPressure()
        }

        private fun consumeNoBackPressure() {
            producer.noBackPressure().observeOn(Schedulers.computation()).subscribe({
                Thread.sleep(1000)
                println(it.toString())
            }, {
                println("onError: ${it.message}")
            })
        }

        private fun consumeBackPressure() {
            producer.backPressure().observeOn(Schedulers.computation(), false, 1)
                .subscribe({
                    Thread.sleep(1000)
                    println(it.toString())
                }, {
                    println("onError: ${it.message}")
                })
        }
    }
}