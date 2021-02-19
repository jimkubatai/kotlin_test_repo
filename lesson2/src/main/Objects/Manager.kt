class Manager : IManager {

   private var Nodes : Array<INode>


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

   }
}