package lesson9.lesson9

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@SpringBootApplication
class Lesson9Application
	fun main(args: Array<String>) {
		runApplication<Lesson9Application>(*args)
	}

	@RestController
	@RequestMapping("/cars")
	class CarResource(var carsData: CarsData = CarsData()) {
		@GetMapping("/all")
		fun index(): List<Car> = carsData.getAll()

		@GetMapping("/{id}")
		fun getById(@PathVariable id: Int): Car? = carsData.getCarById(id) ?: throw ResourceNotFoundException()
	}

	@RestController
	@RequestMapping("/fuelCards")
	class FuelCardResource(var cardsData: FuelCardsData = FuelCardsData()) {
		@GetMapping("/all")
		fun getAll(): List<FuelCard> = cardsData.getAll()

		@GetMapping("/{id}")
		fun getById(@PathVariable id: Int): FuelCard? = cardsData.getCardById(id) ?: throw ResourceNotFoundException()
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Запись не найдена")
	class ResourceNotFoundException : Exception()



