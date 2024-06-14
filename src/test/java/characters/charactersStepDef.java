package characters;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class charactersStepDef {
    private String BASE_URI = "https://rickandmortyapi.com/api/character/";
    private Response response;

    @Given("The user wants to search a characters by name in the Rick and Morty API")
    public void theUserWantsToSearchACharactersByNameInTheRickAndMortyAPI() {
        RestAssured.baseURI=BASE_URI;
    }

    @When("The user sends a request to search characters by name {string}")
    public void theUserSendsARequestToSearchCharactersByName(String name) {
        response = given().header("Content-Type", "application/json").queryParam("name",name).when().get();
    }

    @Then("The user receives a list of characters that contain {string}")
    public void theUserReceivesAListOfCharactersThatContain(String name) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertTrue(response.getStatusCode() == 200);
        boolean containsName = jsonPathEvaluator.getList("results.name").stream()
                .anyMatch(characterName -> characterName.toString().toLowerCase().contains(name.toLowerCase()));
        assertTrue(containsName);

    }

    @Then("The list contain every information about the Characters")
    public void theListContainEveryInformationAboutTheCharacters() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String[] fields = {"id", "name", "status", "species", "type", "gender", "origin", "location", "image", "episode", "url", "created"};
        jsonPathEvaluator.getList("results").forEach(character -> {
            for (String field : fields) {
                assertTrue(character.toString().contains(field));
            }
        });
        
    }


    @Then("The user receives a list of recommended characters")
    public void theUserReceivesAListOfRecommendedCharacters() {
        response = given().header("Content-Type", "application/json").when().get();
    }
}
