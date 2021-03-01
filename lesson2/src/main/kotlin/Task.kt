interface ITask {
    var time: Int
    var taskName: String

    fun work() {
        println("Doing $taskName task - time $time")
    }
}


class SlowTask(override var time : Int) : ITask {

    override var taskName = "slow"

    override fun work() {
        println("thinking...")
        println("thinking...")
        println("thinking...")
        super.work()
    }
}

open class FastTask(final override var time : Int)  : ITask {

    override var taskName = "fast"

    init {
        this.time = time / 2
    }

}

class SoFastTask(time: Int) : FastTask(time) {

    override var taskName = "so fast"

    init {
        this.time = time / 2
    }
}