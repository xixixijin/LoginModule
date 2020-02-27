package ming.jin.passportauth;

import ming.jin.passportauth.constant.AccountType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PassportAuthApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(AccountType.EMAIL);

    }

}
