package com.lc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

//代码生成器
public class CodeGenerator {

    @Test
    public void run() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();

//        String projectPath = System.getProperty("user.dir");

        //设置代码的输出目录,建议设置绝对路径,否则可能会出现问题
        gc.setOutputDir("D:\\project\\guli_parent\\service\\service-edu" + "/src/main/java");

        //设置作者
        gc.setAuthor("kaho");

        //生成后是否打开资源管理器
        gc.setOpen(false);

        //重新生成时文件是否覆盖
        gc.setFileOverride(false);

        //去掉Service接口的首字母I
        gc.setServiceName("%sService");

        //主键策略
        gc.setIdType(IdType.ID_WORKER_STR);

        //定义生成的实体类中日期类型
        gc.setDateType(DateType.ONLY_DATE);

        //开启Swagger2模式
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/guli?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("000000");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("eduservice"); //模块名
        pc.setParent("com.lc");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();

        //设置表的名称
        strategy.setInclude("edu_course" , "edu_course_description" , "edu_chapter" , "edu_video");

        //将数据表中的表名映射到bean中开启驼峰命名法
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略

        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        //将数据表中的字段名映射到bean中开启驼峰命名法
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }
}
