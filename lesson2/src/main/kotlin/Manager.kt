class Manager {
    private val nodes = mutableListOf<INode>()

    fun doTask(task : ITask) {
       for(node in nodes) {
          node.setTask(task)
       }
    }

    fun addNode(node : INode) {
       nodes.add(node)
    }

    fun removeNode(name: String, owner: String,) {
        for(node in nodes) if(name == node.name && owner == node.owner) {
            nodes.remove(node)
        }
    }
}