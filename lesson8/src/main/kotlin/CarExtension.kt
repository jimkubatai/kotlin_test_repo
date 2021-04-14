fun Car.isWrong() {
    if(this.id == 2)
        throw WrongIdException("Wrong Id!")
}