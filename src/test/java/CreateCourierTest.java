import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {
    CourierClient courierClient;
    Courier courier;
    private int id;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourier();
    }
    @After
    public void cleanUp() {
        if (id != 0) {
            courierClient.delete(id);
        }
    }

    @Test
    @DisplayName("Creating new courier")
    @Description("Creating new courier with correct credentials and check the positive creating courier")
    public void testCreateCourier201(){
        ValidatableResponse responseCreate = courierClient.create(courier).statusCode(201);
        responseCreate.assertThat().body("ok", equalTo(true));
    }
    @Test
    @DisplayName("Creating an identical courier")
    @Description("Checking the response body and status code when trying to create an identical courier")
    public void testCreateIdenticalCourier409(){
        courierClient.create(courier);
        ValidatableResponse responseCreate = courierClient.create(courier).statusCode(409);
        responseCreate.assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
    @Test
    @DisplayName("Creating courier without login")
    @Description("Checking the response body and status code when trying to create an courier without login")
    public void testCreateCourierWithoutLogin400(){
        courier.setLogin("");
        ValidatableResponse responseCreate = courierClient.create(courier).statusCode(400);
        responseCreate.assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Creating courier without password")
    @Description("Checking the response body and status code when trying to create an courier without password")
    public void testCreateCourierWithoutPassword400(){
        courier.setPassword("");
        ValidatableResponse responseCreate = courierClient.create(courier).statusCode(400);
        responseCreate.assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
