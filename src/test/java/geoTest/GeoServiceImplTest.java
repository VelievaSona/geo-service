package geoTest;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;


public class GeoServiceImplTest {

    @Test
    public void testGeoServiceEnglish() {
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location res = geoService.byIp(MOSCOW_IP);

        assertEquals(res.getCountry(), Country.RUSSIA);
        assertEquals(res.getCity(), "Moscow");
        assertEquals(res.getStreet(), "Lenina");
        assertEquals(res.getBuiling(), 15);
    }
}
