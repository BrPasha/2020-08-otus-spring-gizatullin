package spring.gizatullin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import spring.gizatullin.exceptions.AppException;
import spring.gizatullin.services.QuizService;

/**
 * @author pgizatullin
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        QuizService service = ctx.getBean(QuizService.class);
        service.doQuiz();
    }
}
