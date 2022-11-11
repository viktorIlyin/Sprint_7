import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client{
    private static final String ORDER_PATH = "api/v1/orders/";
    @Step("Create new order using Order")
    public Response createOrder(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDER_PATH);
    }
    @Step("Request a list of orders")
    public ValidatableResponse getOrdersList() {
        return given()
                .spec(getSpec())
                .when()
                .get(ORDER_PATH)
                .then()
                .log().all();
    }

}
