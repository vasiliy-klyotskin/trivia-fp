package org.kyotskin.trivia
package networking

import org.scalatest.funsuite.AnyFunSuite
import domain.Question
import cats.effect.IO
import cats.effect.unsafe.implicits.global

class QuestionRemoteMappingTests extends AnyFunSuite {
  test("No incorrect answers in dto") {
    val textDto = QuestionTextRemoteDTO(text = "some question")
    val dto = QuestionRemoteDTO(
      id = "any",
      question = textDto,
      correctAnswer = "correct answer",
      incorrectAnswers = List.empty
    )
    val model = Question(
      text = "some question",
      possibleAnswers = List("correct answer shuffled"),
      correctAnswer = "correct answer"
    )
    val result = map(dto, shuffle).unsafeRunSync()

    assert(result == model)
  }

  test("There are incorrect answers in dto") {
    val textDto = QuestionTextRemoteDTO(text = "some question")
    val dto = QuestionRemoteDTO(
      id = "any",
      question = textDto,
      correctAnswer = "correct answer",
      incorrectAnswers = List("incorrect 0", "incorrect 1", "incorrect 2")
    )
    val result = map(dto, shuffle).unsafeRunSync()

    assert(result.text == "some question")
    assert(result.correctAnswer == "correct answer")
    assert(result.possibleAnswers.contains("correct answer shuffled"))
    assert(result.possibleAnswers.contains("incorrect 0 shuffled"))
    assert(result.possibleAnswers.contains("incorrect 1 shuffled"))
    assert(result.possibleAnswers.contains("incorrect 2 shuffled"))
    assert(result.possibleAnswers.length == 4)
  }

  private def shuffle(list: List[String]): IO[List[String]] = {
    IO.pure(list.map(_ + " shuffled"))
  }
}