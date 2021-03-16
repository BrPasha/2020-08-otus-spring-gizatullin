package spring.gizatullin.services.impl;

import org.springframework.stereotype.Service;
import spring.gizatullin.services.InputService;

import java.util.Scanner;

/**
 * @author pgizatullin
 */
@Service
public class ConsoleInputService implements InputService {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int getInteger(String errorMessage) {
        while (!scanner.hasNextInt()) {
            System.out.println("Wrong input. Type answer number:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    @Override
    public String getString() {
        return scanner.next();
    }
}
