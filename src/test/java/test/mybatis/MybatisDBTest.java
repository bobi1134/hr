package test.mybatis;

import cn.mrx.hr.model.User;
import cn.mrx.hr.service.IUserService;
import cn.mrx.hr.utils.Log4jPrint;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Author: xialiangbo
 * Date: 2017/7/27 17:47
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-service.xml", "classpath:spring-dao.xml"})
public class MybatisDBTest {

    @Autowired
    private IUserService iUserService;

    @Test
    public void test01(){
        User user = iUserService.selectUser("admin");
        Log4jPrint.println(MybatisDBTest.class, "xxxx", user);
        Log4jPrint.println("ceshi", user);
    }

}
