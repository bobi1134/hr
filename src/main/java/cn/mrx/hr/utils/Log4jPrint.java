package cn.mrx.hr.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: xialiangbo
 * Date: 2017/8/6 14:40
 * Description:
 */
public class Log4jPrint {

    public static void  println(String tip, Object result){
        Logger logger = LoggerFactory.getLogger(Log4jPrint.class);
        logger.info(getStr(result));
        System.out.println(tip + result);
        System.out.println(getStr(result));
    }

    public static void  println(Class<?> clazz, String use, Object result){
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(getStr(result));
        System.out.println("*调用："+clazz.getName());
        System.out.println("*作用："+use);
        System.out.println("*结果："+result);
        System.out.println(getStr(result));
    }

    public static String getStr(Object result){
        String str = "";
        if(result!=null && result.toString().length()>0){
            Integer length = result.toString().length()+10;
            for (int i=0; i<length; i++)    str+="*";
        }else
            str = "*********************************************************************************************************************************************";
        return str;
    }
}
