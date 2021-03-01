interface INode {
    val owner: String
    val name: String
    val type: String

    fun setTask(task : ITask) {
        println("Starting work $type node $name - $owner")
        task.work()
    }
}

class UnixNode(override val owner: String, override val name: String) : INode {

    override val type = "Unix"

}

class WindowsNode(override val owner: String, override val name: String) : INode {

    override val type = "Windows"

}

class MacNode(override val owner: String, override val name: String) : INode {

    override val type = "Mac"

}