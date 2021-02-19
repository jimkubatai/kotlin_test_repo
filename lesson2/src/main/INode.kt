interface INode {
    val Owner: String
    val Name: String

    var CurrentTaskId: Int

    fun CheckCurrentStatus(): Int
    fun SetTask(task : ITask)
}