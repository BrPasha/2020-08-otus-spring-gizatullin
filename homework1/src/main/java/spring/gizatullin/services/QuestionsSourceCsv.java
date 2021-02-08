package spring.gizatullin.services;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import spring.gizatullin.dao.Question;
import spring.gizatullin.dao.QuestionType;
import spring.gizatullin.exceptions.AppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pgizatullin
 */
@AllArgsConstructor
public class QuestionsSourceCsv implements QuestionsSource{

    private static String DELIMETER = ";";
    private final Resource csvSource;

    @Override
    public List<Question> getQuestions() throws AppException{
        List<Question> questionList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(csvSource.getInputStream()));
            String lineWithQuestionInfo = br.readLine();
            while (!StringUtils.isEmpty(lineWithQuestionInfo)) {
                questionList.add(createQuestion(lineWithQuestionInfo));
                lineWithQuestionInfo = br.readLine();
            }
        }
        catch (IOException ex) {
            throw new AppException("Resource error",ex);
        }
        return questionList;
    }

    private Question createQuestion(String lineWithQuestionInfo) throws AppException{
        String[] info = lineWithQuestionInfo.split(DELIMETER);
        if (info.length == 0) {
            throw  new AppException("Wrong question format");
        }
        else if (info.length == 1){
            return new Question(QuestionType.WITHOUT_CHOICE, info[0], Collections.emptyList());
        }
        else {
            return new Question(QuestionType.WITH_CHOICE, info[0],
                    Arrays.asList(Arrays.copyOfRange(info, 1, info.length)));
        }
    }
}
