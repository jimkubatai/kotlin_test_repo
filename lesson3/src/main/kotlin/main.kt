fun main() {
    val myStack: Stack<String> = stackOf("Первый", "Второй", "Третий")
    println(myStack.pop())
    println(myStack.pop())
    myStack.push("Четвертый")
    println(myStack.pop())

    val myQueue: Queue<String> = queueOf("Первый", "Второй", "Третий")
    println(myQueue.dequeue())
    println(myQueue.dequeue())
    myQueue.enqueue("Четвертый")
    println(myQueue.dequeue())
}
