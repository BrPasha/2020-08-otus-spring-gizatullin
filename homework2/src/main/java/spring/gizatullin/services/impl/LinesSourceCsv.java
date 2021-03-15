package spring.gizatullin.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import spring.gizatullin.domain.Question;
import spring.gizatullin.domain.QuestionType;
import spring.gizatullin.exceptions.AppException;
import spring.gizatullin.services.LinesSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author pgizatullin
 */
@Service
public class LinesSourceCsv implements LinesSource {

    private static String DELIMETER = ";";
    @Value("${questions}")
    private Resource csvSource;

    @Override
    public List<String> getLines() throws AppException{
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(csvSource.getInputStream()));
            String lineWithQuestionInfo = br.readLine();
            while (!StringUtils.isEmpty(lineWithQuestionInfo)) {
                lines.add(lineWithQuestionInfo);
                lineWithQuestionInfo = br.readLine();
            }
        }
        catch (IOException ex) {
            throw new AppException("Resource error",ex);
        }
        return lines;
    }
}
