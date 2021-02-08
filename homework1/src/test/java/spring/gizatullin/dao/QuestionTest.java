package spring.gizatullin.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author pgizatullin
 */
@DisplayName("Question class tests")
public class QuestionTest {

    @Test
    @DisplayName("Check creation of Question")
    void checkConstructor() {
        String text = "Question?";
        List<String> answers = Arrays.asList("Answer 1", "Answer 2", "Answer 3");
        Question question = new Question(QuestionType.WITH_CHOICE, text, answers);
        assertThat(question.getType()).isEqualTo(QuestionType.WITH_CHOICE);
        assertThat(question.getText()).isEqualTo(text);
        assertThat(question.getAnswers()).isEqualTo(answers);
    }
}
