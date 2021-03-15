package spring.gizatullin.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.gizatullin.exceptions.AppException;
import spring.gizatullin.services.InputService;
import spring.gizatullin.services.NameService;

/**
 * @author pgizatullin
 */
@Service
@AllArgsConstructor
public class ConsoleNameService implements NameService {

    private final InputService inputService;

    public String getName() throws AppException {
        System.out.println("Input your name, please:");
        return inputService.getString();
    }
}
