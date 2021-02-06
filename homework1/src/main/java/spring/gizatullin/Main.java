package spring.gizatullin;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.gizatullin.services.QuizService;

/**
 * @author pgizatullin
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService service = context.getBean(QuizService.class);
        service.startQuiz();
    }
}
