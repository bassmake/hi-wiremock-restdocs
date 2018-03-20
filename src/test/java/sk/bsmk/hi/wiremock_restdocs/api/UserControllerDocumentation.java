package sk.bsmk.hi.wiremock_restdocs.api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import sk.bsmk.hi.wiremock_restdocs.MainClass;

import static com.epages.restdocs.WireMockDocumentation.wiremockJson;
import static com.jayway.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MainClass.class)
//@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public class UserControllerDocumentation {

  @LocalServerPort
  private int port;

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

  private RequestSpecification spec;

  @Before
  public void setUp() {
    spec = new RequestSpecBuilder().setPort(port).addFilter(
      documentationConfiguration(this.restDocumentation))
      .build();
  }


  @Test
  public void createUser() {

    final CreateUserRequest request = ImmutableCreateUserRequest.builder()
      .name("some-name")
      .about("blah blah blah")
      .build();

    given(spec)
      .log().all()
      .filter(
        document("create-user",
          wiremockJson(),
          requestFields(
            fieldWithPath("name").description("Name description"),
            fieldWithPath("about").description("About description")
          ),
          responseFields(
            fieldWithPath("name").description("Name description"),
            fieldWithPath("about").description("About description"),
            fieldWithPath("createdAt").description("CreatedAt description")
          )
        )
      ).when()
      .accept(ContentType.JSON)
      .contentType(ContentType.JSON)
      .body(request)
      .post(UserController.CREATE_PATH)
      .then()
      .assertThat()
      .statusCode(200);

  }

  @Test
  public void userDetail() {

    given(spec)
      .filter(
        document("user-detail",
          wiremockJson(),
          pathParameters(
            parameterWithName("userId").description("Id of user")
          ),
          responseFields(
            fieldWithPath("name").description("Name description"),
            fieldWithPath("about").description("About description"),
            fieldWithPath("createdAt").description("CreatedAt description")
          )
        )
      )
      .log().all()
      .when()
      .accept(ContentType.JSON)
      .get(UserController.DETAIL_PATH, "2345")
      .then()
      .log().all()
      .assertThat()
      .statusCode(200);
  }

}
