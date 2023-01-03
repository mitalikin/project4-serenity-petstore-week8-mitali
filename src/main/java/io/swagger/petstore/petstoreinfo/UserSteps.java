package io.swagger.petstore.petstoreinfo;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constant.EndPoints;
import io.swagger.petstore.model.UserPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static jnr.posix.WString.path;

public class UserSteps {
    @Step("Creating user with username:{0},firstName:{1},lastName:{2},email:{3},password:{4},phone:{5},userStatus:{6}")
    public ValidatableResponse createUser(String username,
                                          String firstName,
                                          String lastName,
                                          String email,
                                          String password,
                                          String phone,
                                          int userStatus) {
        UserPojo userPojo = new UserPojo();
        userPojo.setUsername(username);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the student information with username:{0}")

    public HashMap<String, Object> getUserInfoByUsername(String username) {

        return SerenityRest.given().log().all()
                .when()
                .pathParam("username",username)
                .get(EndPoints.Get_SINGLE_USER_BY_USERNAME)
                .then()
                .statusCode(200)
                .extract().path("");

    }
    @Step("Updating user with Id{0},username:{1},firstName:{2},lastName:{3},email:{4},password:{5},phone:{6},userStatus:{7}")
    public ValidatableResponse updateUser(int id,
                                          String username,
                                          String firstName,
                                          String lastName,
                                          String email,
                                          String password,
                                          String phone,
                                          int userStatus) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUsername(username);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("username", username)
                .body(userPojo)
                .when()
                .put(EndPoints.Update_SINGLE_USER_BY_USERNAME)
                .then();
    }

    @Step("Deleting user information with id: {0}")

    public ValidatableResponse deleteUser(String username) {
        return SerenityRest.given().log().all()
                .pathParam("username", username)
                .when()
                .delete(EndPoints.Delete_SINGLE_USER_BY_USERNAME)
                .then();
    }
    @Step("Getting student information with studentId: {0}")
    public ValidatableResponse getUserById(String username) {
        return SerenityRest.given().log().all()
                .pathParam("username", username)
                .when()
                .get(EndPoints.Get_SINGLE_USER_BY_USERNAME)
                .then();
    }

}
