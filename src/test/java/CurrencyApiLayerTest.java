import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CurrencyApiLayerTest {

    private static Response response;




    @BeforeAll
    public static void setup() {

        response = given().get(Consts.URL_CURRENCY +  Consts.API_KEY);

    }

 @Test
 public void performanceTest(){
      response.then().time(lessThan(2000L));

 }
 @Test
 public void responseCodeTest(){
        response.then().statusCode(200);

 }
    @Test
    public void unauthorizedUsersTest(){
        Response  response = given().get(Consts.URL_CURRENCY +  "wrong API Key");
        System.out.println(response.asString());
       // response.then().statusCode(200);
        response.then().statusCode(401);

        response.then().body("message", equalTo("Invalid authentication credentials"));

    }

    @Test
    public void getCurrencySource() {

        System.out.println(response.body());
        response.then().body("success", equalTo(true));
        response.then().body("timestamp", notNullValue());
        response.then().body("source", equalTo("USD"));
        response.then().body("quotes.USDALL", greaterThan(90F));




    }

    @ParameterizedTest
    @ValueSource (strings ={"CAD", "EUR", "RUB"} )
    public void currenciesRateTest(String currency){


 response = given().get(Consts.URL_CURRENCY + Consts.API_KEY + "&source=USD&currencies=" + currency);
 response.then().statusCode(200);
 System.out.println(response.asString());

    }

    @ParameterizedTest
    @ValueSource(strings = {"2018-01-01","2024-01-01"})
    public void historicalTest(String date){

     Response response2 = given().get("https://api.apilayer.com/currency_data/historical?date=" + date +"&apikey=" + Consts.API_KEY );
response2.then().statusCode(200);
        System.out.println(response2.asString());
    }


@Test
    public void historicalDataTest(){

   final String URL ="https://api.apilayer.com/currency_data/historical?date=2018-01-01&apikey=Z6Q5DOJWra6uMKF5YoDqMyYMxyn0sHgL";
     Response response1 = given().get(URL);
     response1.then().statusCode(200);
    System.out.println(response1.asString());

}

}
