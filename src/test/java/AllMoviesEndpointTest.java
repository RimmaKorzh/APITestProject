import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AllMoviesEndpointTest {
    private static Response response;



    @BeforeAll
    public static void setup(){

        response = given().auth().oauth2(Consts.TOKEN).get(Consts.URL+Consts.MOVIES_ENDPOINT);
        System.out.println(response.asString());

//        response = given().auth().oauth2(Consts.TOKEN).contentType("application/json").get(Consts.URL+Consts.MOVIES_ENDPOINT);
//        System.out.println(response.asString());

    }

    @Test
    public void  getAllBooksResponseCodeTest(){

        response.then().statusCode(200);

    }

}
