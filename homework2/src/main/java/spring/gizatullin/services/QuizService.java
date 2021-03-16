package spring.gizatullin.services;

import spring.gizatullin.exceptions.AppException;

/**
 * @author pgizatullin
 */
public interface QuizService {
    boolean doQuiz() throws AppException;
}
