package sk.bsmk.hi.wiremock_restdocs.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

  public static final String CREATE_PATH = "/api/v1/users";
  public static final String DETAIL_PATH = "/api/v1/users/{userId}";

  @PostMapping(CREATE_PATH)
  public UserDetailResponse create(@RequestBody @Valid CreateUserRequest request) {
    return ImmutableUserDetailResponse
      .builder()
      .name(request.getName())
      .about(request.getAbout())
      .createdAt(System.currentTimeMillis())
      .build();
  }

  @GetMapping(DETAIL_PATH)
  public UserDetailResponse detail(@PathVariable("userId") String userId) {
    return ImmutableUserDetailResponse.builder()
      .name("someone")
      .about("blah")
      .createdAt(System.currentTimeMillis())
      .build();
  }

}
