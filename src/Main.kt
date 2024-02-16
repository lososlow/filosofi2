import java.util.concurrent.Semaphore
import kotlin.random.Random

// Класс, представляющий философа
class Philosopher(private val id: Int, private val leftStick: Semaphore, private val rightStick: Semaphore) : Runnable {
    override fun run() {
        while (true) {
            // Философ пытается взять левую и правую палочку
            leftStick.acquire()
            rightStick.acquire()

            // Философ обедает
            println("Философ $id обедает.")
            Thread.sleep(Random.nextLong(1000, 3000)) // Имитация обеда

            // Философ освобождает палочки
            rightStick.release()
            leftStick.release()

            // Философ думает
            println("Философ $id думает.")
            Thread.sleep(Random.nextLong(1000, 3000)) // Имитация размышлений
        }
    }
}

fun main() {
    val numPhilosophers = 5
    // Создание семафоров для палочек
    val Sticks = List(numPhilosophers) { Semaphore(1) }
    // Создание философов
    val philosophers = List(numPhilosophers) { Philosopher(it, Sticks[it], Sticks[(it + 1) % numPhilosophers]) }

    // Запуск потоков для каждого философа
    philosophers.forEach { Thread(it).start() }
}
