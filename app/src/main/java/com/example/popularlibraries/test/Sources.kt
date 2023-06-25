package com.example.popularlibraries.test

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Sources {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        private fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        // Источник подходит, когда получать значения не требуется, а нас интересует сам факт завершения
        //какой-либо операции, например, запись в файл или базу данных
        fun completable() = Completable.create { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onComplete()
                } else {
                    emitter.onError(RuntimeException("Error"))
                    return@create
                }
            }
        }

        // Источник аналогичен Observable, однако может выдать только одно значение. Single идеально
        //подходит для HTTP-запросов, так как всегда ожидается только один ответ от сервера
        fun single() = Single.fromCallable {
            return@fromCallable "Some string value"
        }

        // Maybe подходит, если нас устраивает как наличие значения, так и его отсутствие.
        //Например, при обработке авторизации с возможностью гостевого доступа.
        fun maybe() = Maybe.create<String> { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onSuccess("Success: $it")
                } else {
                    emitter.onComplete()
                    return@create
                }
            }
        }

        // Горячий Observable отправляет данные независимо от того, подписан кто-нибудь на него или нет
        fun hotObservable() = Observable.interval(1, TimeUnit.SECONDS)
            .publish()

        // Subject представляет собой класс, который расширяет (наследует) Observable и реализует интерфейс
        //Observer. Это одновременно и Observable, и Observer.
        fun publishSubject() = PublishSubject.create<String>().apply {
            Observable.timer(2, TimeUnit.SECONDS)
                .subscribe {
                    onNext("Value from subject")
                }
        }
    }

    class Consumer(private val producer: Producer) {
        private fun execCompletable() {
            producer.completable().subscribe({
                println("onComplete")
            }, { println("onError: ${it.message}") })
        }

        fun execSingle() {
            producer.single().map { it + it }
                .subscribe({ s ->
                    println("onSuccess: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execMaybe() {
            producer.maybe().map { it + it }
                .subscribe({ s ->
                    println("onSuccess: $s")
                }, {
                    println("onError: ${it.message}")
                }, {
                    println("onComplete")
                })
        }

        fun execHotObservable() {
            val hotObservable = producer.hotObservable()
            hotObservable.subscribe() {
                println(it)
            }
            hotObservable.connect()
            Thread.sleep(3000)
            hotObservable.subscribe() {
                println("delayed: $it")
            }
        }

        private fun execPublishSubject() {
            val subject = producer.publishSubject()
            subject.subscribe({
                println("onNext: $it")
            }, {
                println("onError: ${it.message}")
            })
            subject.onNext("from exec")
        }

        fun exec() {
            execPublishSubject()
        }
    }
}