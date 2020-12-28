import com.xing.service.UserService;
import com.xing.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //动态代理代理的是一个接口
        UserService userService = (UserService) context.getBean("userService");

        userService.add();
    }
}
