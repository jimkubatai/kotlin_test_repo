interface ITask {
    var time : Int

    fun work()
}


class SlowTask(override var time : Int) : ITask {

    override fun work() {
        println("Doing slooooow task - time $time")
    }
}

open class FastTask(override var time : Int)  : ITask {

    override fun work() {
        val factTime = time / 2
        println("Doing fast task - time $factTime")
    }
}

class SoFastTask(time: Int) : FastTask(time) {
    init {
        this.time = time / 2
    }
}