package mate.academy.spring.controller;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.spring.config.AppConfig;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerTest {
  private static final String BASE_ENDPOINT = "/users";
  private static final String EXPECTED_INJECT_RESPONSE = "Users are injected!";
  private static ObjectMapper objectMapper;
  private static List<User> injectedUsers;
  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeAll
  static void injectUsers() {
    objectMapper = new ObjectMapper();
    injectedUsers = new ArrayList<>();
    injectedUsers.add(createUser("John", "Doe"));
    injectedUsers.add(createUser("Emily", "Stone"));
    injectedUsers.add(createUser("Hugh", "Dane"));
  }

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  public void get_NonExistentUser_notOk() {
    assertThrows(NestedServletException.class, () -> {
      sendGetByIdRequest(1L);
    });
  }

  @Test
  public void get_ExistentUsers_Ok() throws Exception {
    sendInjectRequest();
    List<UserResponseDto> users = getAllUsers();
    for (UserResponseDto expected : users) {
      UserResponseDto actual = sendGetByIdRequest(expected.getId());
      assertEquals(expected.getId(), actual.getId());
      assertEquals(expected.getFirstName(), actual.getFirstName());
      assertEquals(expected.getLastName(), actual.getLastName());
    }
  }

  @Test
  public void getAll_NoUsers_Ok() throws Exception {
    int expected = 0;
    int actual = getAllUsers().size();
    assertEquals(expected, actual);
  }

  @Test
  public void injectMockUsers_Ok() throws Exception {
    String actualInjectResponse = sendInjectRequest();
    assertEquals(EXPECTED_INJECT_RESPONSE, actualInjectResponse);
    List<UserResponseDto> actualUsers = getAllUsers();
    assertEquals(injectedUsers.size(), actualUsers.size());
    assertTrue(validUsers(actualUsers));
  }

  private UserResponseDto sendGetByIdRequest(long id) throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(MockMvcRequestBuilders.get(BASE_ENDPOINT + "/" + id))
        .andReturn();
    String content = mvcResult.getResponse().getContentAsString();
    return objectMapper.readValue(content, new TypeReference<>() {});
  }

  private boolean validUsers(List<UserResponseDto> actualUsers) {
    int validUsersNumber = 0;
    for (User injectedUser : injectedUsers) {
      for (UserResponseDto user : actualUsers) {
        if (nonNull(user.getId())
            && user.getFirstName().equals(injectedUser.getFirstName())
            && user.getLastName().equals(injectedUser.getLastName())) {
          validUsersNumber++;
          break;
        }
      }
    }
    return injectedUsers.size() == validUsersNumber;
  }

  private String sendInjectRequest() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(
        BASE_ENDPOINT + "/inject"
    )).andReturn();
    return mvcResult.getResponse().getContentAsString();
  }

  private List<UserResponseDto> getAllUsers() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(MockMvcRequestBuilders.get(BASE_ENDPOINT))
        .andReturn();
    String content = mvcResult.getResponse().getContentAsString();
    return objectMapper.readValue(content, new TypeReference<>() {});
  }

  private static User createUser(String firstName, String lastName) {
    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    return user;
  }
}
