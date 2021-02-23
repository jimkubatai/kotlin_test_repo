interface ITask {
    fun work()
}


class SlowTask : ITask {

    override fun work() {
        println("Doing slooooow task")
    }
}

class FastTask : ITask {

    override fun work() {
        println("Doing fast task")
    }
}