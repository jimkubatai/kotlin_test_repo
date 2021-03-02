fun <T> stackOf(vararg elements: T): Stack<T> {
    if(elements.isEmpty()) return Stack()

    val myStack: Stack<T> = Stack()
    for(el: T in elements) {
        myStack.push(el)
    }

    return myStack
}

fun <T> queueOf(vararg elements: T): Queue<T> {
    if(elements.isEmpty()) return Queue()

    val myQueue: Queue<T> = Queue()
    for(el: T in elements) {
        myQueue.enqueue(el)
    }

    return myQueue
}