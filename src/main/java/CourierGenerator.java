import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public static Courier getRandomCourier() {
        final String courierLogin = RandomStringUtils.randomAlphabetic(7);
        final String courierPassword = RandomStringUtils.randomAlphabetic(7);
        final String courierFirstName = RandomStringUtils.randomAlphabetic(7);
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }
}
