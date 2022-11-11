
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderParametrizedTest {

    private final List<String> color;
    private final Matcher<Object> expected;

    public CreateOrderParametrizedTest(List<String> color, Matcher<Object> expected) {
        this.color = color;
        this.expected = expected;
    }

    @Parameterized.Parameters()
    public static Object[][] getColorData() {
        return new Object[][]{
                {List.of("BLACK", "GREY"), notNullValue()},
                {List.of("BLACK"), notNullValue()},
                {List.of("GREY"), notNullValue()},
                {null, notNullValue()}
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
                .body("track", expected);
    }
}