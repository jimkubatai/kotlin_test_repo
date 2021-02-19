interface INode {
    var Owner: String
    var Name: String
    var CurrentTaskId: Int

    fun CheckCurrentStatus(): Int
    fun SetTask(task : ITask)
}