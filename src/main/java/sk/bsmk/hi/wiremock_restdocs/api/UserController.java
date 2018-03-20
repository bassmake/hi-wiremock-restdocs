package sk.bsmk.hi.wiremock_restdocs.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.OffsetDateTime;

@RestController
public class UserController {

  public static final String PATH = "/api/v1/users";

  @PostMapping(PATH)
  public UserDetailResponse create(@RequestBody @Valid CreateUserRequest request) {
    return ImmutableUserDetailResponse
      .builder()
      .name(request.getName())
      .about(request.getAbout())
      .createdAt(System.currentTimeMillis())
      .build();
  }

}
