import kotlin.concurrent.thread

class main {

    fun main() {

       val thread1 = thread(true, false, null, "Thread") { println("This is thread") }



    }

}