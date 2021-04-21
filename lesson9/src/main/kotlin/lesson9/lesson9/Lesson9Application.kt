package lesson9.lesson9

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
class Lesson9Application
	fun main(args: Array<String>) {
		runApplication<Lesson9Application>(*args)
	}

	@RestController
	@RequestMapping("/fuelCards")
	class FuelCardResource(var cardsData: FuelCardsData = FuelCardsData()) {
		@GetMapping("/all")
		fun getAll(): List<FuelCard> = cardsData.getAll()

		@GetMapping("/{id}")
		fun getById(@PathVariable id: Int): ResponseEntity<FuelCard> {
			val card = cardsData.getCardById(id) ?: return ResponseEntity.notFound().build()
			return ResponseEntity.ok(card)
		}

		@PostMapping("/{brand}/{number}")
		fun addNew(@PathVariable brand: String, @PathVariable number: String): ResponseEntity<Int> {
			val card = FuelCard(0, brand, number)
			return ResponseEntity.ok(cardsData.addCard(card))
		}

		@PostMapping("/{brand}/{number}")
		fun update(@PathVariable brand: String, @PathVariable number: String): ResponseEntity<Int> {
			val card = FuelCard(0, brand, number)
			return ResponseEntity.ok(cardsData.updateCard(card))
		}

		@DeleteMapping("/{id}")
		fun update(@PathVariable id: Int): ResponseEntity<Int> {
			return ResponseEntity.ok(cardsData.removeCard(id))
		}

	}



