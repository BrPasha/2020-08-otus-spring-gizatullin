package spring.gizatullin.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author pgizatullin
 */
@AllArgsConstructor
@Getter
public class Question {

    private final QuestionType type;
    private final String text;
    private final List<String> answers;

}
