# springboot种子项目代码生成器


详见 https://github.com/Autom-liu/springboot-base

配置文件： src\main\resources\generatorConfig.json

生成模式： 

- BASE 基础框架生成  只生成common和common-service
- MODULE  只生成模块
- ENTITY_OVERRIDE  表变更 只实体覆盖
- INTEGRATE 完整生成  BASE + MODULE


# 存在问题

非单主键实体`Mapper`启动会报错，把继承的`Mapper`改为`BaseMapper`即可。非单主键将无法使用`IN`查询

# 集成Admin模块

1. 获取springboot-admin模块

2. 重构包名

3. 引用依赖

4. 开启注解，配置`MapperScan`
