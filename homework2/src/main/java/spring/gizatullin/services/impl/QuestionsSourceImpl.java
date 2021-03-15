package spring.gizatullin.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.gizatullin.domain.Question;
import spring.gizatullin.domain.QuestionType;
import spring.gizatullin.exceptions.AppException;
import spring.gizatullin.services.LinesSource;
import spring.gizatullin.services.QuestionsSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author pgizatullin
 */
@Service
@AllArgsConstructor
public class QuestionsSourceImpl implements QuestionsSource {

    private static String DELIMETER = ";";
    private final LinesSource linesSource;

    @Override
    public List<Question> getQuestions() throws AppException{
        List<Question> questionList = new ArrayList<>();
        List<String> lines = linesSource.getLines();
        for (String line : lines) {
            questionList.add(createQuestion(line));
        }
        return questionList;
    }

    private Question createQuestion(String lineWithQuestionInfo) throws AppException{
        String[] info = lineWithQuestionInfo.split(DELIMETER);
        if (info.length < 2) {
            throw  new AppException("Wrong question format");
        }
        else if (info.length == 2){
            return new Question(QuestionType.WITHOUT_CHOICE, info[0], info[1], Collections.emptyList());
        }
        else {
            return new Question(QuestionType.WITH_CHOICE, info[0], info[1],
                    Arrays.asList(Arrays.copyOfRange(info, 2, info.length)));
        }
    }
}
