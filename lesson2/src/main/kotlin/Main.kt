fun main() {
       val myManager : IManager = Manager()

       myManager.addNode(UnixNode("Lenovo", "V1"))
       myManager.addNode(WindowsNode("Microsoft", "Smart Table"))
       myManager.addNode(MacNode("Apple", "Smart Table"))

       myManager.doTask(SlowTask(50))
       myManager.doTask(FastTask(50))

       myManager.removeNode("Lenovo", "V1")
       myManager.doTask(SoFastTask(50))
    }