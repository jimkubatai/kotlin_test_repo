class Queue<T> {
    private val objectsList = mutableListOf<T>()

    fun enqueue(t: T) {
        objectsList.add(t)
    }

    fun dequeueOrNull(): T? {
        if(objectsList.isEmpty()) return null

        return dequeue()
    }

    fun dequeue() : T {
        val obj = objectsList[0]
        objectsList.removeAt(0)

        return obj
    }
}