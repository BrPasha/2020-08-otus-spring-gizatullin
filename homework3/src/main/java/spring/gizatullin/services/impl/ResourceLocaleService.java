package spring.gizatullin.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.gizatullin.services.LocaleService;

import java.util.Locale;

/**
 * @author pgizatullin
 */
@Service
public class ResourceLocaleService implements LocaleService {

    @Value("${settings.locale}")
    private String locale;

    @Override
    public Locale getLocale() {
        return Locale.forLanguageTag(locale);
    }
}
