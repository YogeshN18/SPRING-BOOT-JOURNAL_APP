package TestFeatures;

import com.pratice.JournalApp_SpringBoot_Project.JournalAppSpringBootProjectApplication;
import com.pratice.JournalApp_SpringBoot_Project.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JournalAppSpringBootProjectApplication.class)
public class EmailSendTest {

    @Autowired
    private EmailService emailService;



    @Test
   void  emailTest()
    {

        emailService.sendEmail("yogeshnakade298@gmail.com", "how are you " , "Hii how are you doing");

    }
}
