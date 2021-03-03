interface ITask {
    var time: Int
    var taskName: String

    fun work() {
        println("Doing $taskName task - time $time")
    }
}


class SlowTask(override var time : Int) : ITask {

    override var taskName = "slow"

    private val slower: Int = 2

    override fun work() {
        think()
        doItSlower()
        super.work()
    }

    private fun think() {
        println("thinking...")
        println("thinking...")
        println("thinking...")
    }

    private fun doItSlower(){
        this.time = time * slower
    }

}

open class FastTask(final override var time : Int)  : ITask {

    override var taskName = "fast"

    protected val faster: Int = 2

    init {
        this.time = time - faster
    }

}

class SoFastTask(time: Int) : FastTask(time) {

    override var taskName = "so fast"

    init {
        doItFaster()
        doItFaster()
    }

    private fun doItFaster(){
        this.time = time / faster
    }
}