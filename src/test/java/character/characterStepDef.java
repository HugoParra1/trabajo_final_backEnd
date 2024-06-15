package character;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class characterStepDef {

    private String BASE_URI = "https://rickandmortyapi.com/api/character/";
    private Response response;

    @Given("The user wants to search a characters by id in the Rick and Morty API")
    public void theUserWantsToSearchACharactersByIdInTheRickAndMortyAPI() {
        RestAssured.baseURI = BASE_URI;
    }

    @Then("The list contain every information about the character")
    public void theListContainEveryInformationAboutTheCharacter() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println(jsonPathEvaluator);
        String[] fields = {"id", "name", "status", "species", "type", "gender", "origin", "location", "image", "episode", "url", "created"};
        for (String field : fields) {
            assertTrue(jsonPathEvaluator.get().toString().contains(field));
        }
    }

    @When("The user sends a request to search characters by id {string}")
    public void theUserSendsARequestToSearchCharactersById(String id) {
        response = given().header("Content-Type", "application/json").when().get("/" + id);
    }

    @Then("The user should get the character with id {string}")
    public void theUserShouldGetTheCharacterWithId(String id) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(jsonPathEvaluator.get("id").toString().equals(id));
    }
}
