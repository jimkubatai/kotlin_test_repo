interface INode {
    val owner: String
    val name: String

    var currentTaskId: Int

    fun checkCurrentStatus(): Int
    fun setTask(task : ITask)
}