open interface IManager {
    fun isAnyNodeFree() : Boolean
    fun isMyTaskDone(id : Int) : Boolean
    fun addTaskToQueue( task : ITask) : Int
    fun addNode(node : INode)
}

class Manager : IManager {

    val nodes = mutableListOf<INode>()

    override fun isAnyNodeFree() : Boolean {
       for(node in nodes) if(node.checkCurrentStatus() == 0) {
           return true
       }
       return false
    }

    override fun isMyTaskDone(id : Int) : Boolean {
       for(node in nodes) if(node.currentTaskId == id) {
           return node.checkCurrentStatus() == 2
       }
       return false
    }

    override fun addTaskToQueue(task : ITask) : Int {
       for(node in nodes) if(node.checkCurrentStatus() == 0) {
          node.setTask(task)
          return task.id
       }

       return -1
    }

    override  fun addNode(node : INode) {
       nodes.add(node)
    }
}