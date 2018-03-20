package sk.bsmk.hi.wiremock_restdocs.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotBlank;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(stagedBuilder = true)
@JsonSerialize(as = ImmutableCreateUserRequest.class)
@JsonDeserialize(as = ImmutableCreateUserRequest.class)
public interface CreateUserRequest {

  @NotBlank
  String getName();

  @NotBlank
  String getAbout();

}
