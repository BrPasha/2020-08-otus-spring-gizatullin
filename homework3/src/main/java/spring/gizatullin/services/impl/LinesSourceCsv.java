package spring.gizatullin.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import spring.gizatullin.exceptions.AppException;
import spring.gizatullin.services.LinesSource;
import spring.gizatullin.services.LocaleService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pgizatullin
 */
@Service
public class LinesSourceCsv implements LinesSource {

    private final LocaleService localeService;
    private final ResourceLoader resourceLoader;

    public LinesSourceCsv(LocaleService localeService, ResourceLoader resourceLoader) {
        this.localeService = localeService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<String> getLines() throws AppException{
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(loadResource().getInputStream()));
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

    private Resource loadResource() throws AppException {
            Resource resource = resourceLoader.getResource(String.format("questions_%s.csv", localeService.getLocale().toLanguageTag()));
            if (!resource.exists()){
                resource = resourceLoader.getResource("questions.csv");
            }
            return resource;
    }
}
