package spring.gizatullin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.gizatullin.domain.Question;
import spring.gizatullin.domain.QuestionType;
import spring.gizatullin.services.*;
import spring.gizatullin.services.impl.QuizServiceConsole;
import spring.gizatullin.services.impl.ResultCheckerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class QuizServiceTest {

	@ComponentScan(includeFilters =
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {QuizServiceConsole.class}))
	@Configuration
	static class TestConfiguration{
	}

	@MockBean
	private NameService nameService;
	@MockBean
	private QuestionsSource questionsSource;
	@MockBean
	InputService inputService;
	@MockBean
	LocalizationService localizationService;
	@Autowired
	QuizService quizService;
	@MockBean
	ResultChecker checker;

	@Test
	@DisplayName("Удачное прохождение тестирования")
	void quizPass() {
		Mockito.doReturn("Ivan").when(nameService).getName();
		List<Question> questions = new ArrayList();
		questions.add(new Question(QuestionType.WITH_CHOICE, "Question 1?", "1", Arrays.asList("1", "2", "3")));
		questions.add(new Question(QuestionType.WITH_CHOICE, "Question 2?", "2", Arrays.asList("1", "2", "3")));
		questions.add(new Question(QuestionType.WITH_CHOICE, "Question 3?", "3", Arrays.asList("1", "2", "3")));
		Mockito.doReturn(questions).when(questionsSource).getQuestions();

		List<Integer> answers = Arrays.asList(1, 1, 3);
		Mockito.doReturn(true).when(checker).check(questions,
				answers.stream().map(i -> Integer.toString(i)).collect(Collectors.toList()));

		Mockito.doAnswer(new Answer() {
			private int count = -1;
			public Object answer(InvocationOnMock invocation) {
				count++;
				return answers.get(count);
			}
		}).when(inputService).getInteger(anyString());

		Mockito.doReturn("").when(localizationService).getMessage(anyString(), Matchers.<String>anyVararg());

		assertThat(quizService.doQuiz()).isEqualTo(true);
	}

}