package spring.gizatullin.services.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import spring.gizatullin.services.LocaleService;
import spring.gizatullin.services.LocalizationService;

import java.util.Locale;

/**
 * @author pgizatullin
 */
@Service
public class LocalizationServiceImpl implements LocalizationService {
    private final Locale locale;
    private final MessageSource messageSource;

    public LocalizationServiceImpl(LocaleService localeService, MessageSource messageSource) {
        this.locale = localeService.getLocale();
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String messageId, Object... params) {
        return messageSource.getMessage(messageId, params, locale);
    }
}
