
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client{
    private final static String PATH = "/api/v1/courier";
    private final static String LOGIN_PATH = "/api/v1/courier/login";
    private final static String DELETE_PATH = "/api/v1/courier";

    @Step("Create new courier with login:{courier.login} password:{courier.password} firstName:{courier.firstName}")
    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(PATH)
                .then();
    }
    @Step("Delete courier with id")
    public ValidatableResponse delete(int id){
        return given()
                .spec(getSpec())
                .when()
                .delete(DELETE_PATH + id)
                .then();
    }

    @Step("Login courier with login:{credentials.login} password:{credentials.password}")
    public ValidatableResponse login(Credential credential){
        return given()
                .spec(getSpec())
                .body(credential)
                .when()
                .post(LOGIN_PATH)
                .then();
    }
}
