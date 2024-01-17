import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;




public class AllBooksEndpointTests {


    private static Response response;



    @BeforeAll
    public static void setup(){

       response = given().get(Consts.URL+Consts.BOOKS_ENDPOINT);
        System.out.println(response.asString());
    }

   @Test
   public void  getAllBooksResponseCodeTest(){

       response.then().statusCode(200);

   }
@Test
    public void getAllBooksNumOfResultsTest(){

    response.then().body("total", notNullValue());
    response.then().body("total", greaterThan(0));
    response.then().body("total", equalTo(3));

}
@Test
    public void getAllBookResultsTest(){

        response.then().body("docs._id", notNullValue());
        response.then().body("docs._id", hasItem("5cf5805fb53e011a64671582"));
        response.then().body("docs.name", hasItem("The Fellowship Of The Ring"));

        response.then().body("docs.name", hasItems("The Fellowship Of The Ring", "The Two Towers", "The Return Of The King"));

}
    @Test
    public void getAllBookResultsContainsTest(){

response.then().body("docs.name",contains("The Fellowship Of The Ring", "The Two Towers", "The Return Of The King") );
response.then().body("docs.name",containsInAnyOrder( "The Two Towers","The Fellowship Of The Ring","The Fellowship Of The Ring","The Fellowship Of The Ring" ,"The Return Of The King") );
}
@Test
    public void getAllBooksPerformanceTest(){

     response.then().time(lessThan(2000L)) ;
}


}
