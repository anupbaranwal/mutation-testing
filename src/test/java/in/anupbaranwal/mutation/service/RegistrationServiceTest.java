package in.anupbaranwal.mutation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import in.anupbaranwal.mutation.ProvidentFundRegistrationApplication;
import in.anupbaranwal.mutation.model.User;
import in.anupbaranwal.mutation.service.RegistrationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProvidentFundRegistrationApplication.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class RegistrationServiceTest {

  @Autowired
  private RegistrationService registrationService;

  @Test
  @DisplayName("should give true when user is registered")
  void shouldGiveTrueWhenUserIsRegistered() {
    // Given
    User user = new User(12, "Jhon Snow", "89029929201");
    // When
    registrationService.register(user);
    // Then
    assertThat(registrationService.isUserRegistered(user)).isTrue();
  }

  @Test
  @DisplayName("should throw an exception when uan number is null")
  void shouldThrowAnExceptionWhenUanNumberIsNull() {
    // Given
    User user = new User(null, "Jhon Snow", "89029929201");
    // When -Then
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> registrationService.register(user))
        .withMessage("User uan number is invalid!");
  }

  @Test
  @DisplayName("should throw an exception when uan number is null")
  void shouldThrowAnExceptionWhenUserIsNull() {
    // Given
    User user = null;
    // When -Then
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> registrationService.register(user))
        .withMessage("User data can not be null!");
  }

  @Test
  @DisplayName("should return registered user when uan number is given")
  void shouldReturnRegisteredUserWhenUanNumberIsGiven() {
    // Given
    User user = new User(15, "Tyrion Lannister", "89029929201");
    registrationService.register(user);
    // When
    User actualUser = registrationService.getRegisteredUser(15);
    // Then
    assertThat(actualUser).extracting("uanNumber", "name", "contactNo")
        .contains(user.getUanNumber(), user.getName(), user.getContactNo());
  }

  @Test
  @DisplayName("should return null when wrong uan number is given")
  void shouldReturnNullWhenWrongUanNumberIsGiven() {
    // Given
    User user = new User(15, "Tyrion Lannister", "89029929201");
    registrationService.register(user);
    // When
    User actualUser = registrationService.getRegisteredUser(10);
    // Then
    assertThat(actualUser).isNull();
  }

  @Test
  @DisplayName("should throw an exception when uan number is negative")
  void shouldThrowAnExceptionWhenUanNumberIsNegative() {
    // Given
    User user = new User(-1, "Jhon Snow", "89029929201");
    // When -Then
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> registrationService.register(user))
        .withMessage("User uan number is invalid!");
  }

  @Test
  @DisplayName("should throw an exception when uan number is zero")
  void shouldThrowAnExceptionWhenUanNumberIsZero() {
    // Given
    User user = new User(0, "Jhon Snow", "89029929201");
    // When -Then
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> registrationService.register(user))
        .withMessage("User uan number is invalid!");
  }

  @Test
  @DisplayName("should give false when user is not registered")
  void shouldGiveFalseWhenUserIsNotRegistered() {
    // Given
    User user = new User(129, "Daenerys Targaryen", "89029929201");
    // When - Then
    assertThat(registrationService.isUserRegistered(user)).isFalse();
  }
}
