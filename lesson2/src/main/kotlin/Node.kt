interface INode {
    val owner: String
    val name: String

    fun setTask(task : ITask)
}

class UnixNode(override val owner: String, override val name: String) : INode {

    override fun setTask(task : ITask) {
        println("Starting work unix node $name - $owner")
        task.work()
    }
}

class WindowsNode(override val owner: String, override val name: String) : INode {

    override fun setTask(task : ITask) {
        println("Starting work windows node $name - $owner")
        task.work()
    }
}