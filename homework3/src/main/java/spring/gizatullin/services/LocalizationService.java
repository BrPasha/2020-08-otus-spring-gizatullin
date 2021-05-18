package spring.gizatullin.services;

/**
 * @author pgizatullin
 */
public interface LocalizationService {
    String getMessage(String messageId, Object... params);
}
