package spring.gizatullin.services;

import spring.gizatullin.domain.Question;
import spring.gizatullin.exceptions.AppException;

import java.util.List;

/**
 * @author pgizatullin
 */
public interface LinesSource {
    List<String> getLines() throws AppException;
}
