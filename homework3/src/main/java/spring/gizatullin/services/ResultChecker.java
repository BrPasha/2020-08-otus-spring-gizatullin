package spring.gizatullin.services;

import spring.gizatullin.domain.Question;

import java.util.List;

/**
 * @author pgizatullin
 */
public interface ResultChecker {
    boolean check(List<Question> questions, List<String> answers);
}
