package org.kyotskin.trivia
import domain.finalScore
import domain.Answer
import domain.Difficulty
import org.scalatest.funsuite.AnyFunSuite

class FinalScoreTests extends AnyFunSuite {
  test("Final score for easy difficulty") {
    assert(finalScore(predefinedAnswers(), Difficulty.Easy) == 495)
  }

  test("Final score for normal difficulty") {
    assert(finalScore(predefinedAnswers(), Difficulty.Medium) == 990)
  }

  test("Final score for hard difficulty") {
    assert(finalScore(predefinedAnswers(), Difficulty.Hard) == 1485)
  }

  private def predefinedAnswers(): List[Answer] = {
    // baseScore: 9 + 7.8 + 19.2 + 0 + 1 + 0 + 6 + 7.8 + 24 + 17.1 = 91.9 => 91
    // finalScore: 91 * 10 * (0.85 / 10^2) * (2 - 10)^2 = 495.04 => 495
    List(
      Answer(isCorrect = true, time = 4, wordsCountInQuestion = 12),
      Answer(isCorrect = true, time = 6, wordsCountInQuestion = 16),
      Answer(isCorrect = true, time = 5, wordsCountInQuestion = 20),
      Answer(isCorrect = false, time = 5, wordsCountInQuestion = 4),
      Answer(isCorrect = true, time = 20, wordsCountInQuestion = 4),
      Answer(isCorrect = false, time = 5, wordsCountInQuestion = 4),
      Answer(isCorrect = true, time = 6, wordsCountInQuestion = 16),
      Answer(isCorrect = true, time = 3, wordsCountInQuestion = 4),
      Answer(isCorrect = true, time = 2, wordsCountInQuestion = 12),
      Answer(isCorrect = true, time = 6, wordsCountInQuestion = 20),
    )
  }
}