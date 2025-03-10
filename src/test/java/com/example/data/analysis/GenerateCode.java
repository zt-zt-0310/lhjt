package com.example.data.analysis;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/11/19 18:35
 */
public class GenerateCode {
    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        ////生成文件的输出目录/lhjt/data-analysis-project
//        gc.setOutputDir(projectPath + "/src/main/java");
//        //作者
//        gc.setAuthor("zt");
//        //是否覆盖已有文件 默认值：false
//        gc.setFileOverride(true);
//        //开启 baseColumnList 默认false
//        gc.setBaseColumnList(true);
//        //开启 BaseResultMap 默认false
//        gc.setBaseResultMap(true);
//        //是否打开输出目录 默认值:true
//        gc.setOpen(false);
//        //开启 swagger2 模式 默认false
//        gc.setSwagger2(true);
//        mpg.setGlobalConfig(gc);
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/analysis_data?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("root");
//        mpg.setDataSource(dsc);
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.example.data.dataAnalysisProject");
//        mpg.setPackageInfo(pc);
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//            }
//        };
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/"+tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        //【实体】是否为lombok模型（默认 false)
//        strategy.setEntityLombokModel(true);
//        //生成 @RestController 控制器
//        strategy.setRestControllerStyle(true);
//        strategy.setEntityColumnConstant(true);
//        //需要生成的表名
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//        //驼峰转连字符
//        strategy.setControllerMappingHyphenStyle(false);
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help);
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
