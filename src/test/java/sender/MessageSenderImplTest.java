package sender;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageSenderImplTest {
    private final GeoService geoService;
    private final LocalizationService localizationService;

    public MessageSenderImplTest() {
        geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));


        localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
    }

    @Test
    public void testMessageSenderEnglish(){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String res = messageSender.send(headers);

        Location location = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        assertEquals(res,  localizationService.locale(location.getCountry()));
    }

    @Test
    public void testMessageSenderRussia() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String res = messageSender.send(headers);

        Location location = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        assertEquals(res, localizationService.locale(location.getCountry()));
    }
}
