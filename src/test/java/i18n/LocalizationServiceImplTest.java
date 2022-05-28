package i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;
import static org.junit.jupiter.api.Assertions.*;


public class LocalizationServiceImplTest {

    @Test
    public void testLocaleEnglish() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String res = localizationService.locale(Country.BRAZIL);

        assertEquals(res, "Welcome");
    }

    @Test
    public void testLocateRussia() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String res = localizationService.locale(Country.RUSSIA);

        assertEquals(res, "Добро пожаловать");
    }
}
