package com.zxf.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerate {

    public static void main(String[] args) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            //设置代码的生成位置，磁盘的目录
            String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath + "/yeb-server/src/main/java");

            gc.setAuthor("zxf");
            gc.setOpen(false); //生成后是否打开资源管理器
            gc.setFileOverride(false); //重新生成时文件是否覆盖

            gc.setBaseResultMap(true); //xml开启 BaseResultMap
            gc.setBaseColumnList(true); //xml 开启BaseColumnList

            gc.setServiceName("%sService"); //去掉Service接口的首字母I
            gc.setSwagger2(true); //开启Swagger2模式

            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/yeb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8");
            dsc.setDriverName("com.mysql.cj.jdbc.Driver");
            dsc.setUsername("root");
            dsc.setPassword("123456");
            dsc.setDbType(DbType.MYSQL);
            mpg.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
            pc.setParent("com.zxf")
//                    .setModuleName("server")
                    .setEntity("entity")
                    .setMapper("mapper")
                    .setService("service")
                    .setServiceImpl("service.impl")
                    .setController("controller");
            mpg.setPackageInfo(pc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };

            // 如果模板引擎是 freemarker
            String templatePath = "/templates/mapper.xml.ftl";
            // 如果模板引擎是 velocity
            // String templatePath = "/templates/mapper.xml.vm";

            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/yeb-server/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
                            + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);

            // 配置模板
            TemplateConfig templateConfig = new TemplateConfig();
            templateConfig.setXml(null);

            mpg.setTemplate(templateConfig);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();

            strategy.setNaming(NamingStrategy.underline_to_camel) //数据库表映射到实体的命名策略，驼峰命名规则
                    .setTablePrefix("t_") //生成实体时去掉表前缀
                    .setColumnNaming(NamingStrategy.no_change) //数据库表字段映射到实体的命名策略
                    .setEntityLombokModel(true)  //lombok模型
                    .setRestControllerStyle(true) //restful api风格控制器
                    .setControllerMappingHyphenStyle(true); //url中驼峰转连字符

            mpg.setStrategy(strategy);
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            //执行代码的生成
            mpg.execute();
        }


}
