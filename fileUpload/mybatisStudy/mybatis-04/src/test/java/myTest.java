import com.xing.dao.TeacherMapper;
import com.xing.pojo.Teacher;
import com.xing.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class myTest {

    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        List<Teacher> teacher = mapper.getTeacher(1);

        System.out.println(teacher);

        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        List<Teacher> teacher = mapper.getTeacher2(1);

        System.out.println(teacher);

        sqlSession.close();
    }
}
