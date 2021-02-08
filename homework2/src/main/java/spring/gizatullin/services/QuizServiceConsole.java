package spring.gizatullin.services;

import spring.gizatullin.domain.Question;
import spring.gizatullin.domain.QuestionType;
import spring.gizatullin.exceptions.AppException;

import java.util.List;
import java.util.Scanner;

/**
 * @author pgizatullin
 */
public class QuizServiceConsole implements QuizService{

    private final QuestionsSource source;

    public QuizServiceConsole(QuestionsSource source) {
        this.source = source;
    }

    public void startQuiz() {
        try {
            List<Question> questions = source.getQuestions();
            int questionCount = 1;
            Scanner sc = new Scanner(System.in);
            for (Question question : questions) {
                System.out.println(String.format("Question №%d of %d", questionCount, questions.size()));
                printQuestion(question);
                if (question.getType() == QuestionType.WITH_CHOICE) {
                    System.out.println("Type answer number:");
                    while (!sc.hasNextInt()) {
                        System.out.println("Wrong input. Type answer number:");
                        sc.next();
                    }
                    // TODO collect answers
                    sc.nextInt();
                }
                else {
                    System.out.println("Type answer:");
                    // TODO collect answers
                    sc.next();
                }
                questionCount++;
            }
        }
        catch (AppException ex) {
            System.out.println(ex.getMessage());
        }
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
