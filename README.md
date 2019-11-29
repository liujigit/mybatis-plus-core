**基于mybatis-plus开发实现分表功能（拓展支持postgre sql的语法conflict、returning）**
   
一、修改说明：
     
    1、修改BaseMapper，添加接口参数：suffix，可以配合注解@TableName设置表名（"tableName${suffix}"）,利用mybatis的机制补全表名；
    2、修改因添加参数而影响自带的insert接口；因为insert接口entity参数默认没有注解，insert后续操作也需要通过注解名称操作
    3、添加find接口（没什么卵用），类似原始的select
二、使用说明

    在引入mybatis-plus依赖是，排除原始的mybatis-plus-core依赖，引入改依赖即可
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>3.1.2</version>
                <exclusions>
                    <exclusion>
                        <groupId>com.baomidou</groupId>
                        <artifactId>mybatis-plus-core</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-core</artifactId>
                <version>3.1.2-SNAPSHOT</version>
            </dependency>
            
三、升级说明：
    
    1、添加postgre sql语法的特殊支持（upsert、returning），mapper借口可以继承PgBaseMapper即可。
    2、添加save、saveReturn
    
四、信息
    
    **git**：https://github.com/liujigit/mybatis-plus-core