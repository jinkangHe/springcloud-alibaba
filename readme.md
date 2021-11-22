SpringCloudAlibaba

# 项目结构

一个父工程 **springcloud-alibaba**

三个微服务 **shop user oder**

使用**nacos**作为配置中心和注册中心

使用**openfiegn**作为声明式服务调用

# 版本与相关依赖


| spring cloud | spring cloud alibaba | springboot |
| -------------- | ---------------------- | ------------ |
| 2020.0.4     | 2021.1               | 2.5.6      |

父工程相关依赖

```xml
            <!--Spring Cloud Alibaba 的版本信息-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2021.1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--Spring Cloud 的版本信息-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>2020.0.4</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
```

# Nacos组件使用

[github里选择合适的版本](https://) 下载并解压，双击bin目录下的startup.cmd即可 在浏览器输入 localhost:8848/nacos 即可进入web页面 登录账户密码都是nacos

## 1.配置中心

新建modul取名shop-9002,引入springboot-web相关依赖，准备一个接口用来获取配置中心的某个值

* 加入配置中心相关依赖

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

* 配置

在resource资源文件夹下新建bootstrap.yml文件，文件内指定服务名和nacos配置中心地址

```yaml
server:
  port: 9002 #端口号
spring:
  application:
    name: shop-9002 #服务名
  cloud:
    nacos:
    
      config:
        server-addr: 127.0.0.1:8848 #Nacos作为配置中心地址
        file-extension: yaml #指定yaml格式的配置
```

继续新建application.yml文件 用来指定当前profile.active

```yaml
spring:
  profiles:
    active: dev
```

进入nacos管理页面，在配置列表中新建配置

![image.png](./assets/1650464529481-image.png)

其中DataID的格式为\${prefix}-\${spring.profiles.active}.${file-extension}

* ${prefix}：默认取值为微服务的服务名，即配置文件中 spring.application.name 的值，可以在配置文件中通过配置 spring.cloud.nacos.config.prefix 来指定。
* \${spring.profiles.active}：表示当前环境对应的 Profile，例如 dev、test、prod 等。当没有指定环境的 Profile 时，其对应的连接符也将不存在， dataId 的格式变成 \${prefix}.${file-extension}。
* ${file-extension}：表示配置内容的数据格式，我们可以在配置文件中通过配置项 spring.cloud.nacos.config.file-extension 来配置，例如 properties 和 yaml。

还需要加入如下依赖才能让项目正确启动

```xml
<!--SpringCloud2020及以后的版本默认不启用 bootstrap 配置，我们需要在pom里面显式地引入：-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

启动之后访问接口http://localhost:9002/api/shop/serverName 即可获取到在配置中心配置的参数

## 2.服务注册

* 引入依赖

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

* 配置文件配置注册中心地址

  ```yaml
  spring:
    application:
      name: shop-9002 #服务名
    cloud:
      nacos:
        discovery:
          server-addr: 127.0.0.1:8848 #Nacos服务注册中心地址
  ```
* 在启动类加上注解@EnableDiscoveryClient

  ```java
  @SpringBootApplication
  @EnableDiscoveryClient
  public class ShopApplication {
      public static void main(String[] args) {
          SpringApplication.run(ShopApplication.class,args);
      }
  }
  ```

直接启动项目，在nacos管理页面点击服务列表就可以看到该服务

![image.png](./assets/1650465797690-image.png)
