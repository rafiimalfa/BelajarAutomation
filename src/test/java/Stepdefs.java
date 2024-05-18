

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static junit.framework.Assert.assertEquals;

public class Stepdefs {

    public int statuscode;
    RequestSpecification httpRequest;
    Response response;
    public int responseCode;
    public ResponseBody body;
    public JSONObject requestParam = new JSONObject();
    public JsonPath jsonPath;



    @Given("I hit the url of create new user api endpoint")
    public void iHitTheUrlOfCreateNewUserApiEndpoint() {
        RestAssured.baseURI = "https://reqres.in/api/";
    }
    @When("I pass the url in the request api user")
    public void iPassTheUrlInTheRequestApiUser() {
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        }


    @Then("I receive the response code create user as {int}")
    public void iReceiveTheResponseCodeCreateUserAs(int arg0) {
        responseCode = response.getStatusCode();
        assertEquals(responseCode, 201);
    }


    @And("I pass the request body of new user name {}")
    public void iPassTheRequestBodyOfNewUserNameUserName(String name) {
        requestParam.put("name", name);
    }


    @And("I pass the request body of new user job {}")
    public void iPassTheRequestBodyOfNewUserJobUserJob(String job) {
        requestParam.put("job", job);
        httpRequest.body(requestParam. toJSONString());
        response = httpRequest.post("api/users");
    }


    @And("I verify the response contains the user name {}")
    public void iVerifyTheResponseContainsTheUserNameUserName(String resultName) {
        body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

        String responseBody = body.asString();
        jsonPath = response.jsonPath();
        String name = jsonPath.getString("name");
        assertEquals(resultName, name);

    }


    @And("I verify the response contains the user job {}")
    public void iVerifyTheResponseContainsTheUserJobUserJob(String resultJob) {
        String job = jsonPath.getString("job");
        assertEquals(resultJob, job);
    }
}

