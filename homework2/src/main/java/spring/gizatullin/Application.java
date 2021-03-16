package spring.gizatullin;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import spring.gizatullin.exceptions.AppException;
import spring.gizatullin.services.QuizService;

/**
 * @author pgizatullin
 */
@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class Application {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        QuizService service = context.getBean(QuizService.class);
        service.doQuiz();
    }
}
