package spring.gizatullin.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.gizatullin.domain.Question;
import spring.gizatullin.domain.QuestionType;
import spring.gizatullin.exceptions.AppException;
import spring.gizatullin.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author pgizatullin
 */
@Service
@AllArgsConstructor
public class QuizServiceConsole implements QuizService {

    private final QuestionsSource source;
    private final InputService inputService;
    private final NameService nameService;
    private final ResultChecker resultChecker;
    private final LocalizationService localizationService;

    public boolean doQuiz() throws AppException {
        String name = nameService.getName();
        List<Question> questions = source.getQuestions();
        int questionCount = 1;
        List<String> answers = new ArrayList<>();
        for (Question question : questions) {
            System.out.println(String.format(localizationService.getMessage("output.question", questionCount, questions.size())));
            printQuestion(question);
            if (question.getType() == QuestionType.WITH_CHOICE) {
                System.out.println(localizationService.getMessage("input.answer"));
                int answer = inputService.getInteger(localizationService.getMessage("input.number.error"));
                answers.add(Integer.toString(answer));
            }
            else {
                answers.add(inputService.getString());
            }
            questionCount++;
        }
        boolean result = resultChecker.check(questions,answers);
        if (result) {
            System.out.println(localizationService.getMessage("input.result.success", name));
        }
        else {
            System.out.println(localizationService.getMessage("input.result.failed", name));
        }
        return result;
    }

    private void printQuestion(Question question) {
        System.out.println(question.getText());
        if (question.getType() == QuestionType.WITH_CHOICE) {
            int answerNumber = 1;
            for (String answer : question.getAnswers()) {
                System.out.println(String.format("%d. %s",answerNumber, answer));
                answerNumber++;
            }
        }
    }
}
