class Queue<T> {
    private val objectsList = mutableListOf<T>()

    fun enqueue(t: T) {
        objectsList.add(t)
    }

    fun dequeue() : T? {
        if(objectsList.isEmpty()) return null
        val obj = objectsList[0]
        objectsList.removeAt(0)
        return obj
    }
}