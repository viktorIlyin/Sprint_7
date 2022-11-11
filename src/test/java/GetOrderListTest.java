import api.order.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class GetOrderListTest {

    @Test
    @DisplayName("api.order.Order list successfully received")
    @Description("Checking that the received order list is not empty")
    public void getOrderListTest() {
        OrderClient order = new OrderClient();
        ValidatableResponse orderListResponse = order.getOrdersList().statusCode(200);
        orderListResponse.assertThat().body("orders.id", notNullValue());
    }
}