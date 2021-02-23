fun main() {
       val myManager : IManager = Manager()

       myManager.addNode(UnixNode("Lenovo", "V1"))
       myManager.addNode(UnixNode("IBM", "5255020"))
       myManager.addNode(WindowsNode("Lenovo", "V2"))
       myManager.addNode(WindowsNode("Microsoft", "Smart Table"))

       myManager.doTask(SlowTask())
       myManager.doTask(FastTask())

       myManager.removeNode("Lenovo", "V1")
       myManager.doTask(SlowTask())
    }