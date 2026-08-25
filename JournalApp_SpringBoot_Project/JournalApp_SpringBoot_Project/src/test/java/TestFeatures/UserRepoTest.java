package TestFeatures;


import com.pratice.JournalApp_SpringBoot_Project.Entity.User;
import com.pratice.JournalApp_SpringBoot_Project.JournalAppSpringBootProjectApplication;
import com.pratice.JournalApp_SpringBoot_Project.Repository.UserRepositoryImplemention;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JournalAppSpringBootProjectApplication.class)
public class UserRepoTest {

    @Autowired
    private  UserRepositoryImplemention userRepositoryImplemention;

    @Test
    public void testUSerRepoImpl()
    {
        List<User> userForSA = userRepositoryImplemention.getUserForSA();
        System.out.println(userForSA.toString());
    }

    @Test
    public void testUSerRepoImpl1()
    {
        List<User> userForSA = userRepositoryImplemention.getUserForSA1();
        System.out.println(userForSA.toString());
    }


}
