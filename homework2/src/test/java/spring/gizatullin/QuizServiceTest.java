package spring.gizatullin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import spring.gizatullin.domain.Question;
import spring.gizatullin.domain.QuestionType;
import spring.gizatullin.exceptions.AppException;
import spring.gizatullin.services.*;
import spring.gizatullin.services.impl.QuizServiceConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
/**
 * @author pgizatullin
 */
@DisplayName("Тест сервиса по проведению тестирования")
public class QuizServiceTest {

    @Test
    @DisplayName("Удачное прохождение тестирования")
    void quizPass() throws AppException {
        NameService nameService = Mockito.mock(NameService.class);
        Mockito.doReturn("Ivan").when(nameService).getName();

        QuestionsSource questionsSource = Mockito.mock(QuestionsSource.class);
        List<Question> questions = new ArrayList();
        questions.add(new Question(QuestionType.WITH_CHOICE, "Question 1?", "1", Arrays.asList("1", "2", "3")));
        questions.add(new Question(QuestionType.WITH_CHOICE, "Question 2?", "2", Arrays.asList("1", "2", "3")));
        questions.add(new Question(QuestionType.WITH_CHOICE, "Question 3?", "3", Arrays.asList("1", "2", "3")));
        Mockito.doReturn(questions).when(questionsSource).getQuestions();

        List<Integer> answers = Arrays.asList(1, 1, 3);

        ResultChecker checker = Mockito.mock(ResultChecker.class);
        Mockito.doReturn(true).when(checker).check(questions,
                answers.stream().map(i -> Integer.toString(i)).collect(Collectors.toList()));
        boolean r = checker.check(questions,
                answers.stream().map(i -> Integer.toString(i)).collect(Collectors.toList()));
        InputService inputService = Mockito.mock(InputService.class);

        Mockito.doAnswer(new Answer() {
            private int count = -1;
            public Object answer(InvocationOnMock invocation) {
                count++;
                return answers.get(count);
            }
        }).when(inputService).getInteger("Wrong input. Type answer number:");

        QuizService quizService = new QuizServiceConsole(questionsSource, inputService, nameService, checker);
        assertThat(quizService.doQuiz()).isEqualTo(true);
    }
}
