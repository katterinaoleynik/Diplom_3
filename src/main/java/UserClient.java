import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {

    private static final String USER_PATH = "api/auth";

    @Step("Удаление пользователя")
    public Response deleteAccessToken(String accessToken) {
        Response response = given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_PATH + "/user");
        printResponseBodyToConsole(response, "запрос на удаление пользователя");
        return response;
    }

    @Step("Print response body to console")
    public void printResponseBodyToConsole(Response response, String requestName) {
        System.out.println("Ответ на " + requestName + response.body().asString());
    }
}