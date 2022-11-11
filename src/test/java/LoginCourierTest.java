import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
    private CourierClient courierClient;
    private Courier courier;
    private int id;
    private Credential credential;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourier();
        courierClient.create(courier);
        credential = new Credential(courier.getLogin(), courier.getPassword());
    }
    @After
    public void cleanUp() {
            courierClient.delete(id);
    }

    @Test
    @DisplayName("Successful courier login")
    @Description("Creation of new courier with correct credential and check of successful courier login")
    public void testLoginCourier200(){
        ValidatableResponse responseLogin = courierClient.login(Credential.from(courier)).statusCode(200);
        id = responseLogin.extract().path("id");
        responseLogin.assertThat().body("id", notNullValue());
    }
    @Test
    @DisplayName("Failed courier login with empty password")
    @Description("Checking the response body and status code when create new courier with empty password and trying to login")
    public void testLoginWithoutPassword400(){
        ValidatableResponse responseLogin = courierClient.login(Credential.from(courier));
        id = responseLogin.extract().path("id");
        Credential withoutPassword = new Credential(courier.getLogin(), "");
        ValidatableResponse responsePassError = courierClient.login(withoutPassword).statusCode(400);
        responsePassError.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Failed courier login with empty login")
    @Description("Checking the response body and status code when create new courier with empty login and trying to login")
    public void testLoginWithoutLogin400(){
        ValidatableResponse responseLogin = courierClient.login(Credential.from(courier));
        id = responseLogin.extract().path("id");
        Credential withoutLogin = new Credential("", courier.getPassword());
        ValidatableResponse responseLoginError = courierClient.login(withoutLogin).statusCode(400);
        responseLoginError.assertThat().body("message",equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Failed courier login with empty login and password")
    @Description("Checking the response body and status code when create new courier with empty login and password and trying to login")
    public void testLoginWithoutLoginAndPassword400(){
        ValidatableResponse responseLogin = courierClient.login(Credential.from(courier));
        id = responseLogin.extract().path("id");
        Credential withoutLogin = new Credential("", "");
        ValidatableResponse responseCredentialError = courierClient.login(withoutLogin).statusCode(400);
        responseCredentialError.assertThat().body("message",equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Failed courier login with wrong login")
    @Description("Checking the response body and status code when create new courier with wrong login and trying to login")
    public void testLoginWithWrongLogin(){
        ValidatableResponse responseLogin = courierClient.login(Credential.from(courier));
        id = responseLogin.extract().path("id");
        Credential wrongLogin = new Credential(courier.getLogin() + "random", credential.getPassword());
        ValidatableResponse responseWrongLogin = courierClient.login(wrongLogin).statusCode(404);
        responseWrongLogin.assertThat().body("message",equalTo("Учетная запись не найдена"));
    }
    @Test
    @DisplayName("Failed courier login with wrong password")
    @Description("Checking the response body and status code when create new courier with wrong password and trying to login")
    public void testLoginWithWrongPassword(){
        ValidatableResponse responseLogin = courierClient.login(Credential.from(courier));
        id = responseLogin.extract().path("id");
        Credential wrongLogin = new Credential(courier.getLogin(), credential.getPassword()+ "random");
        ValidatableResponse responseWrongLogin = courierClient.login(wrongLogin).statusCode(404);
        responseWrongLogin.assertThat().body("message",equalTo("Учетная запись не найдена"));
    }

}
