class Stack<T> {
    private val objectsList = mutableListOf<T>()

    fun push(t: T) {
        objectsList.add(t)
    }

    fun popOrNull(): T? {
        if(objectsList.isEmpty()) return null

        return pop()
    }

    fun pop(): T {
        val obj = objectsList[objectsList.size - 1]
        objectsList.removeAt(objectsList.size - 1)

        return obj
    }
}