import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
    @Mock
    UserRepository userRepository;

    @Mock
    KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    UserService userService;

    @Test()
    public void testUserCreation() throws JsonProcessingException {
        User user = User.builder()
                .name("Something")
                .email("name@email.com")
                .phone("923840928")
                .age(20)
                .build();
        userService.createUser(user);
        verify(userRepository, times(1)).save(any());
        verify(kafkaTemplate, times(1 )).send(eq("user_created"),any());
    }
}
