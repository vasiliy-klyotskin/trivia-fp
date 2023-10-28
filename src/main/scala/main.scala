package org.kyotskin.trivia

@main
def main(): Unit = {
  println("Hello world!")
}

enum Difficulty {
  case Easy
  case Medium
  case Hard
}

case class Answer(isCorrect: Boolean, time: Double, wordsCountInQuestion: Int)

def score(answers: List[Answer]): Int = {
  answers.foldLeft(0)((ac, answer) =>
    if (isCorrectAndFast(answer)) {
      scoreForCorrectAndFast(answer)
    } else {
      0
    }
  )
}

def scoreForCorrectAndFast(answer: Answer) = {
  val remainingTimeUntilAnswerIsNotFast = maxTimeThatIsConsideredFast(answer) - answer.time
  (3 * remainingTimeUntilAnswerIsNotFast + 3).toInt
}

def isCorrectAndFast(answer: Answer): Boolean = {
  answer.isCorrect && isFast(answer)
}

def isFast(answer: Answer): Boolean = {
  answer.time <= maxTimeThatIsConsideredFast(answer)
}

def maxTimeThatIsConsideredFast(answer: Answer): Double = {
  answer.wordsCountInQuestion.toDouble / 4 + 3
}