package spring.gizatullin.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.gizatullin.domain.Question;
import spring.gizatullin.services.ResultChecker;

import java.util.List;

/**
 * @author pgizatullin
 */
@Service
public class ResultCheckerImpl implements ResultChecker {

    @Value("${min.percentage.correct.answers}")
    private double minPercentageCorrectAnswers;

    @Override
    public boolean check(List<Question> questions, List<String> answers) {
        int correctAnswers = 0;
        for(int i=0; i<answers.size(); i++) {
            if (questions.get(i).getCorrectAnswer().equals(answers.get(i))) {
                correctAnswers++;
            }
        }
        return (double)correctAnswers/questions.size() >= minPercentageCorrectAnswers;
    }
}
