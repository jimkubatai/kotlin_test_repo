class Manager : IManager {

   private var Nodes : MutableList<INode>

    init {
        Nodes = MutableList<INode>
    }

   private override fun IsAnyNodeFree() : Boolean {
       for(node in Nodes) if(node.IsWork() == false) {
           return true
       }
       return false
   }
   private override fun IsMyTaskDone(id : Int) : Boolean {
       for(node in Nodes) if(node.CurrentTaskId == id) {
           return node.CheckCurrentStatus() == 2
       }
       return false
   }
   private override fun AddTaskToQueue(task : ITask) : Int {
       for(node in Nodes) if(node.CheckCurrentStatus() == 0) {
          node.SetTask(task)
          return task.Id
       }

       return -1
   }
}