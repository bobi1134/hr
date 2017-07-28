package test.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: xialiangbo
 * Date: 2017/7/27 17:47
 * Description:
 */
public class MybatisGenerator {

    /**
     * 逆
     * @throws Exception
     */
    public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ClassLoader classLoader = MybatisGenerator.class.getClassLoader();
        //配置文件位置
        InputStream configFile = classLoader.getResourceAsStream("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    /**
     * main方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            MybatisGenerator mybatisGenerator = new MybatisGenerator();
            mybatisGenerator.generator();
            System.out.println("生成成功！");
        } catch (Exception e) {
            System.out.println("生成失败！");
            e.printStackTrace();
        }
    }
}
