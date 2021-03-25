fun Car.isWrong() : Boolean {
    if(this.id == 2)
        throw WrongIdException("Wrong Id!")

    return false
}