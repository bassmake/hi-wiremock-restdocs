package sk.bsmk.hi.wiremock_restdocs.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(stagedBuilder = true)
@JsonSerialize(as = ImmutableUserDetailResponse.class)
@JsonDeserialize(as = ImmutableUserDetailResponse.class)
public interface UserDetailResponse {

  String name();

  String about();

  Long createdAt();

}
