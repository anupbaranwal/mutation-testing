package in.anupbaranwal.mutation.controller;

import in.anupbaranwal.mutation.model.User;
import in.anupbaranwal.mutation.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/registrations")
public class RegistrationController {

  private final RegistrationService registrationService;

  public RegistrationController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @PostMapping
  public void registerUser(@RequestBody User user) {
    registrationService.register(user);
  }

  @GetMapping
  public ResponseEntity<User> getUser(@RequestParam("uanNumber") final Integer uanNumber) {
    User user = registrationService.getRegisteredUser(uanNumber);
    if(user == null) {
      throw new IllegalArgumentException("No registered user found for "+ uanNumber + " uan");
    }
    return ResponseEntity.ok(user);
  }
}
