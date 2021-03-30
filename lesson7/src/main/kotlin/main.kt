import java.sql.Time
import kotlin.concurrent.thread

fun main() {
    exercise1()
    exercise2()
    exercise3()
}

fun exercise1() {
    // thread
    val thread1 = ThreadTest()
    thread1.start()

    // runnable
    val thread2 = RunnableTest()
    thread2.run()

    // через dsl?
    thread { println("This is через dsl") }

    // поток демон
    var n = 0

    thread(start = true, isDaemon = true, contextClassLoader = null, name = "daemon", priority = -1) {
        n += 1
        println(n)
    }

    // поток с другим приоритетом
    thread(start = true, isDaemon = false, contextClassLoader = null, name = "notdaemon1", priority = 1) {
        n += 1
        println(n)
    }

    thread(start = true, isDaemon = false, contextClassLoader = null, name = "notdaemon2", priority = 5) {
        Thread.sleep(1000)
        n += 2
        println(n)
    }

    thread(start = true, isDaemon = false, contextClassLoader = null, name = "notdaemon3", priority = 10) {
        n += 1
        println(n)
    }
}

fun exercise2() {
    // поток демон
    var n = 0
    var exit = false
    thread(start = true, isDaemon = true, contextClassLoader = null, name = "daemon", priority = -1) {
        while (!exit) {
            n += 1
            Thread.sleep(500)
        }
    }

    thread(start = true, isDaemon = true, contextClassLoader = null, name = "daemon", priority = -1) {
        var lastValue = n
        while (!exit) {
            lastValue = check(lastValue, n)
            Thread.sleep(700)
        }
    }

    thread(start = true, isDaemon = true, contextClassLoader = null, name = "daemon", priority = -1) {
        var lastValue = n
        while (!exit) {
            lastValue = check(lastValue, n)
            Thread.sleep(350)
        }
    }

    Thread.sleep(1000 * 10)

    exit = true
}

fun check(lastValue : Int, newValue: Int) : Int {
    if(lastValue != newValue) {
        println("Value n was changed! New Value = $newValue")
        return newValue
    }
    return  lastValue
}

fun exercise3() {
    val test1 = createAndCalculatePull(10, "test10")
    val test2 = createAndCalculatePull(20, "test20")
    val test3 = createAndCalculatePull(30, "test30")

    val results: Map<String, Int> = mapOf("test 10 threads" to test1, "test 20 threads" to test2, "test 30 threads" to test3)

    println(results.toSortedMap(compareByDescending { it }))

}

fun createAndCalculatePull(count : Int, name:String) : Int {
    var n = 0
    var value = 0
    var exit = false
    while (count != n) {
        thread(start = true, isDaemon = true, contextClassLoader = null, name = "$name $n", priority = -1) {
            for (i in 1..1000000) {
               value += 1

                if(exit) return@thread
            }
        }
        n += 1
    }

    Thread.sleep(1000 * 15)
    exit = true

    return value
}