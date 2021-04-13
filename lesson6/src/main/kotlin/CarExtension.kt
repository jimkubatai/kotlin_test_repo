fun Car.isWrong() : Boolean {
    if(id == 2)
        throw WrongIdException("Wrong Id!")

    return false
}