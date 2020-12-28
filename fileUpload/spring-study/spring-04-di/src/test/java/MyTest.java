import com.xing.pojo.Student;
import com.xing.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }

    @Test
    public void Test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("UserBean.xml");
        User user = (User)context.getBean("user2");
        System.out.println(user);
    }
}
