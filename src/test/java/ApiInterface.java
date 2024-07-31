import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;

public interface ApiInterface {



    @Step("Create user")
    default ResponseAuthUser registerUser(CreateUserData createUserData) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api/auth/";
        Response response = given()
                .header("Content-type", "application/json")
                .body(createUserData)
                .post("/register");
        return response.body().as(ResponseAuthUser.class, ObjectMapperType.GSON);
    }

    @Step("Delete user")
    default void  deleteUser (String token) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api/auth/";
        given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete("/user");
    }

    @Step("Create user")
    default CreateUserData createUserData() {
        Random random = new Random();
        String name = "some" + random.nextInt(10000000);
        String email = name + "@yandex.ru";
        return new CreateUserData(email, name, "password");
    }
}
