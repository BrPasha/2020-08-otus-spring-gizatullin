package spring.gizatullin.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.gizatullin.exceptions.AppException;
import spring.gizatullin.services.InputService;
import spring.gizatullin.services.LocalizationService;
import spring.gizatullin.services.NameService;

/**
 * @author pgizatullin
 */
@Service
@AllArgsConstructor
public class ConsoleNameService implements NameService {

    private final InputService inputService;
    private final LocalizationService localizationService;

    public String getName() throws AppException {
        String s = localizationService.getMessage("input.name");
        System.out.println(localizationService.getMessage("input.name"));
        return inputService.getString();
    }
}
