open interface IManager {
    fun IsAnyNodeFree() : Boolean
    fun IsMyTaskDone(id : Int) : Boolean
    fun AddTaskToQueue( task : ITask) : Int
    fun AddNode(node : INode)
}