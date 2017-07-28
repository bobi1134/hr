package test.utils;

import org.apache.log4j.net.SMTPAppender;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: xialiangbo
 * Date: 2017/7/28 8:50
 * Description:
 */
public class UtilsTest {

    private static Logger logger = LoggerFactory.getLogger(UtilsTest.class);

    @Test
    public void test01(){
        logger.info("info1");
        logger.info("info2");
        logger.debug("debug1");
        logger.debug("debug2");
        logger.error("error1");
        logger.error("error2");
    }


}
