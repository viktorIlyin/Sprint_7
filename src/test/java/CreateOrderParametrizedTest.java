import api.order.Order;
import api.order.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.*;

@RunWith(Parameterized.class)
public class CreateOrderParametrizedTest {
    static String[] black = {"BLACK"};
    static String[] grey = {"GREY"};
    static String[] blackGrey = {"BLACK", "GREY"};
    private final String[] color;

    public CreateOrderParametrizedTest(String[] color) {
        this.color = color;

    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}")
    public static Object[][] getColorData() {
        return new Object[][]{
                {black},
                {grey},
                {blackGrey},
        };
    }

    @Test
    @DisplayName("Successful order creation")
    @Description("Checking the response body and status code when trying create order with different color list")
    public void CreatingOrderTest() {
        OrderClient orderClient = new OrderClient();
        Order order = new Order(color);
        Response createOrderResponse = orderClient.createOrder(order);
        createOrderResponse.then().assertThat().statusCode(201)
                .and()
                .body("track", is(notNullValue()));
    }
}