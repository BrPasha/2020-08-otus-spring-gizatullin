package spring.gizatullin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author pgizatullin
 */
@AllArgsConstructor
@Getter
public class Question {

    private final QuestionType type;
    private final String text;
    private final String correctAnswer;
    private final List<String> answers;
}
