package io.swagger.petstore.petstoreinfo;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.testbase.TestBase;
import io.swagger.petstore.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTestWithSteps extends TestBase {
    static String username = "software";
    static String firstname = "Testing";
    static String lastname = "selenium";
    static String email = "soft1"+ TestUtils.getRandomValue()+"@gmail.com";
    static String password="Tomtpom";
    static String phone="08765434677";
    static int userstatus=0;
    static  int userID;
    @Steps
    UserSteps userSteps;
    @Title("This will create a new user")
    @Test
    public void test001() {

        ValidatableResponse response = userSteps.createUser(username,firstname,lastname,email,password,phone,userstatus);
        response.log().all().statusCode(200);

    }
    @Title("verify if user added to the application")
    @Test
    public void test002() {

        HashMap<String, Object> userMap = userSteps.getUserInfoByUsername(username);
        Assert.assertThat(userMap, hasValue(username));

    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003(){
        username=username+"_updated";
        userSteps.updateUser(userID,username,firstname,lastname,email,password,phone,userstatus).log().all().statusCode(200);
        HashMap<String, Object> userMap = userSteps.getUserInfoByUsername(username);
        Assert.assertThat(userMap, hasValue(username));
    }

    @Title("Delete the user and verify if the user is deleted!")
    @Test
    public void test004() {
        userSteps.deleteUser(username).statusCode(200);
        userSteps.getUserById(username) .statusCode(200);

    }


}
