interface IManager {
    fun doTask(task : ITask)
    fun addNode(node : INode)
    fun removeNode(name : String, owner : String)
}

class Manager : IManager {
    private val nodes = mutableListOf<INode>()

    override fun doTask(task : ITask) {
       for(node in nodes) {
          node.setTask(task)
       }
    }

    override fun addNode(node : INode) {
       nodes.add(node)
    }

    override fun removeNode(name: String, owner: String,) {
        for(node in nodes) if(name == node.name && owner == node.owner) {
            nodes.remove(node)
        }
    }
}