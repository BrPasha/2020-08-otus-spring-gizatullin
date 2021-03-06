package spring.gizatullin.services;

import spring.gizatullin.dao.Question;
import spring.gizatullin.exceptions.AppException;

import java.util.List;

/**
 * @author pgizatullin
 */
public interface QuestionsSource {
    List<Question> getQuestions() throws AppException;
}
