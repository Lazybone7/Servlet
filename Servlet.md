# JavaWeb

## 静态w**eb**

**请求静态web的一次过程：**

1.  客户端发送请求到服务器的webservice
2.  webservice从服务器中获取请求的页面，并返回给客户端



**静态web的缺陷：**

*   Web页面无法动态更新，所有用户看到都是同一个页面
*   他无法和数据库交互（数据无法持久化，用户无法交互）



## 动态web

**动态web的请求过程：**

![image-20211024000725652](E:\LearnNote\Servlet\Servlet.assets\image-20211024000725652.png)



**动态web的优势：**

*   页面会动态展示。展示内容因人而异

*   Web页面可以动态更新。
*   他可以与数据库交互



**动态web的缺陷：**

*   假如服务器的动态web资源出现错误，就需要重新编写**后台程序**，重新发布；

    *   停机维护

    

## web服务器

### 涉及技术

**实现动态web的手段：**

*   ASP
    *   微软：国内最早流行
    *   在HTML中嵌套VB的脚本，ASP+COM
    *   在ASP开发中，基本一个页面都有几千行的业务代码。页面极其混乱，维护成本高
    *   C#
    *   IIS  Internet Information Services 是由微软公司提供的基于运行Microsoft Windows的互联网基本服务。
*   php
    *   PHP开发速度快，功能强大，跨平台，代码简单
    *   无法承载大访问量的情况
*   JSP/Servlet
    *   sun公司主推的B/S架构
    *   基于Java语言
    *   可以承载三高问题带来的问题（高并发，高可用，高性能）
    *   语法与ASP类似



###  web服务器

**作用：**服务器是一个被动的操作，用来处理用户的一些请求和返回给用户一些响应信息

==Tomcat==

*   Apache基金会&sun公司
*   技术先进，性能稳定且免费开发源代码的web应用服务器
*   轻量级应用服务器
*   适用于中小型系统和并发访问用户不是很多的场合
*   Tomcat实际运行的是JSP和Servlet

==IIS==

*   微软
*   ASP
*   windows自带

## Tomcat

### 文件结构

![image-20211024115715779](E:\LearnNote\Servlet\Servlet.assets\image-20211024115715779.png)

### 网站访问的流程

1.  输入域名地址
2.  检查本机的hosts文件是否存在该域名的ip映射，有就直接返回
3.  没有就直接去DNS服务器找该域名对应的ip映射



### 发布一个web网站

1.  将自己写的网站，放到服务器（Tomcat）中指定的web应用文件夹下（webapps）

**网站的文件结构：**

```java
--webapps
  -ROOT
  -自定义网页资源文件夹
  	- WEB-INF
  		- classes：	java程序
  		- lib：			web应用所依赖的jar包
  		- web.xml：	网站配置文件
  	- index.html：	默认首页
  	- static：			静态资源
  		-css
  		-js
  		-img
```

## Http

>   Http（超文本传输协议）是一个简单的请求-响应协议，运行在TCP之上
>
>   *   文本：html、字符串...
>   *   超文本：图片、视频、音频、定位、地图
>   *   80端口
>
>   Https
>
>   *   443端口

### 两个时代

*   **http1.0**
    *   HTTP/1.0：客户端可以与web服务器连接，只能获得一个web资源，请求不到断开连接
*   **http2.0**
    *   HTTP/1.0：客户端可以与web服务器连接后，可以获得多个web资源

### Http请求

`客户端——request——服务器`

```java
Request URL:	www.baidu.com	//请求地址
Request Method:	GET		//请求方法
Status	Code：200 OK	 //状态码
Remote Address：14.215.177.39:443	//远程ip地址
```

```java
Accept: text/html
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9		//语言
Connection: keep-alive
Host: www.baidu.com
```

1.  请求行

    1.  请求行的请求方式：GET
    2.  请求方式：GET POST HEAD DELETE PUT TRACT...
        1.  GET：请求能够携带的参数较少，大小有限制。不安全但高效
        2.  POST：请求能够携带的参数无限制。安全但不高效

2.  请求头

    ```java
    Accept：告诉浏览器所支持的数据类型
    Accept-Encoding：告诉浏览器所支持的数编码格式
    Accept-Language： 告诉浏览器它的语言环境
    Cache-Control:	缓存控制
    Connection：告诉浏览器，请求完成是保持断开还是连接
    Host：主机 
    ```

    ------

    

### Http响应

```java
Cache-Control: private	//缓存控制
Connection: keep-alive	//连接
Content-Encoding: gzip	//编码
Content-Type: text/html;charset=utf-8	//类型
```

1.  响应体

```
Accept：告诉浏览器所支持的数据类型
Accept-Encoding：告诉浏览器所支持的数编码格式
Accept-Language： 告诉浏览器它的语言环境
Cache-Control:	缓存控制
Connection：告诉浏览器，请求完成是保持断开还是连接
Host：主机 
Refrush：告诉客户端，多久刷新一次
Location：让网页重新定位
```

2.  响应状态码
    *   200	响应成功
    *   4**   找不到资源 404
    *   3**   请求重定向
    *   5**   服务器代码错误 500  502 网关错误

## Servlet

### Servlet简介

>   *   Servlet就是Sun公司开发动态web的一门技术
>   *   Sun在api中提供了一个接口：Servlet

`把实现了Servlet接口的java程序叫做Servlet`

### HelloServlet

Sun公司赋给Servlet两个默认的实现类：==HttpServlet==、==GenericServlet==

**编写一个Servlet程序：**

*   编写一个普通类
*   继承HttpServlet

```java
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("HelloServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
```

*   配置web.xml文件中的servlet

```xml
	<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

  <!--注册Servlet-->
    <servlet>
        <servlet-name>servlet01</servlet-name>
        <servlet-class>com.itan.HelloServlet</servlet-class>
    </servlet>
  <!--Servlet的请求路径-->
    <servlet-mapping>
        <servlet-name>servlet01</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
```

*   配置Tomcat

### Servlet原理

servlet由web服务器调用,web服务器在收到请求后

### Mapping问题

1.  一个Servlet可以指定一个映射路径

```xml
<servlet-mapping>
    <servlet-name>servlet01</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

2.  一个Servlet可以指定多个映射路径

```xml
<servlet-mapping>
    <servlet-name>servlet01</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>servlet01</servlet-name>
    <url-pattern>/hello1</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>servlet01</servlet-name>
    <url-pattern>/hello2</url-pattern>
</servlet-mapping>
```

3.  一个Servlet可以指定通用映射路径

```xml
<servlet-mapping>
    <servlet-name>servlet01</servlet-name>
    <url-pattern>/hello/*</url-pattern>
</servlet-mapping>
```

4.  默认请求路径

```xml
<servlet-mapping>
    <servlet-name>servlet01</servlet-name>
    <url-pattern>/*</url-pattern>
</servlet-mapping>
```

5.  指定一些后缀或者前缀等等...

```xml
<!--可以自定义后缀实现请求映射
	*前面不能加项目映射的路径
-->
<servlet-mapping>
    <servlet-name>servlet01</servlet-name>
    <url-pattern>*.do</url-pattern>
</servlet-mapping>
```

**优先级:**

固有的映射路径优先级最高.

```xml
<servlet>
    <servlet-name>servlet01</servlet-name>
    <servlet-class>com.itan.HelloServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>servlet01</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>

<servlet>
  <servlet-name>error</servlet-name>
  <servlet-class>com.itan.ErrorServlet</servlet-class>
</servlet>

<servlet-mapping>
  <servlet-name>error</servlet-name>
  <url-pattern>/*</url-pattern>
</servlet-mapping>
```

------



### ServletContext

>   web容器在启动时,会为每个web程序创建一个新的**ServletContext**对象.它代表了当前的web应用

**作用:**

*   **==共享数据==**	一个Servlet中的数据可以被另一个Servlet获取

```java
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        //设置属性
      	servletContext.setAttribute("username","han");
        PrintWriter writer = resp.getWriter();
        writer.print("HelloServlet");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

public class AttributeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取属性
      	ServletContext servletContext = this.getServletContext();
        String username = (String) servletContext.getAttribute("username");
        resp.getWriter().print("username = " + username);
    }
}
```

*   **==初始化参数==**

```java
/**
 * 获取web应用的初始化参数
 */
public class ParamServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String url = servletContext.getInitParameter("url");
        resp.getWriter().print(url);
    }
}
```

```xml
<!--配置web应用初始化参数-->
<context-param>
  <param-name>url</param-name>
  <param-value>jdbc:mysql://localhost:3306/mysql</param-value>
</context-param>
```

*   **==请求转发==**

![image-20211027212720209](E:\LearnNote\Servlet\Servlet.assets\image-20211027212720209.png)

```java
public class DispatcherServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        servletContext.getRequestDispatcher("/param").forward(req,resp);
    }
}
```

```xml
<servlet>
  <servlet-name>getpara</servlet-name>
  <servlet-class>com.itan.ParamServlet</servlet-class>
</servlet>
<servlet-mapping>
  <servlet-name>getpara</servlet-name>
  <url-pattern>/param</url-pattern>
</servlet-mapping>   

<servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>com.itan.DispatcherServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/patcher</url-pattern>
    </servlet-mapping>
```

*   ==读取配置文件==

```xml
<!--使用build配置resources-->
<build>
  <resources>
    <resource>
      <directory>src/main/resources</directory>
      <includes>
        <include>**/*.properties</include>
        <include>**/*.xml</include>
      </includes>
      <!--开启过滤-->
      <filtering>true</filtering>
    </resource>
    <!--这样一来，java文件夹下的properties也能被包含在项目中-->
    <resource>
      <directory>src/main/java</directory>
      <includes>
        <include>**/*.properties</include>
        <include>**/*.xml</include>
      </includes>
      <filtering>true</filtering>
    </resource>
  </resources>
</build>
```

**类路径:**

![image-20211028191637577](E:\LearnNote\Servlet\Servlet.assets\image-20211028191637577.png)

```java
//读取配置文件
/**
 * 使用ServletContext读取配置文件
 */
public class PropertiesServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/jdbc.properties");
        Properties pro = new Properties();
        pro.load(is);
        String username = pro.getProperty("username");
        String password = pro.getProperty("password");
        resp.getWriter().print(username +": "+ password);

    }
}
```

------

### Req & Resp 

>   web容器在接收到客户端的请求后,针对这个请求,分别创建一个代表请求的HttpServletRequest对象, 一个代表响应的HttpServletResponse对象

*   想要获取客户端请求来的参数:	==HttpServletRequest==
*   给客户响应的信息: ==HttpServletResponse==



#### HttpServletRequest



#### HttpServletResponse

**方法:**

1.  向浏览器发送数据的方法

```java
ServletOutputStream getOutputStream() throws IOException;
PrintWriter getWriter() throws IOException;	
```

2.  向浏览器发送响应头的方法

```java
    void setCharacterEncoding(String var1);

    void setContentLength(int var1);

    void setContentType(String var1);

    void sendRedirect(String var1) throws IOException;

    void setDateHeader(String var1, long var2);

    void addDateHeader(String var1, long var2);

    void setHeader(String var1, String var2);

    void addHeader(String var1, String var2);

    void setIntHeader(String var1, int var2);

    void addIntHeader(String var1, int var2);
```

3.  响应状态码

```java
    int SC_CONTINUE = 100;
    int SC_SWITCHING_PROTOCOLS = 101;
    int SC_OK = 200;
    int SC_CREATED = 201;
    int SC_ACCEPTED = 202;
    int SC_NON_AUTHORITATIVE_INFORMATION = 203;
    int SC_NO_CONTENT = 204;
    int SC_RESET_CONTENT = 205;
    int SC_PARTIAL_CONTENT = 206;
    int SC_MULTIPLE_CHOICES = 300;
    int SC_MOVED_PERMANENTLY = 301;
    int SC_MOVED_TEMPORARILY = 302;
    int SC_FOUND = 302;
    int SC_SEE_OTHER = 303;
    int SC_NOT_MODIFIED = 304;
    int SC_USE_PROXY = 305;
    int SC_TEMPORARY_REDIRECT = 307;
    int SC_BAD_REQUEST = 400;
    int SC_UNAUTHORIZED = 401;
    int SC_PAYMENT_REQUIRED = 402;
    int SC_FORBIDDEN = 403;
    int SC_NOT_FOUND = 404;
    int SC_METHOD_NOT_ALLOWED = 405;
    int SC_NOT_ACCEPTABLE = 406;
    int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
    int SC_REQUEST_TIMEOUT = 408;
    int SC_CONFLICT = 409;
    int SC_GONE = 410;
    int SC_LENGTH_REQUIRED = 411;
    int SC_PRECONDITION_FAILED = 412;
    int SC_REQUEST_ENTITY_TOO_LARGE = 413;
    int SC_REQUEST_URI_TOO_LONG = 414;
    int SC_UNSUPPORTED_MEDIA_TYPE = 415;
    int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    int SC_EXPECTATION_FAILED = 417;
    int SC_INTERNAL_SERVER_ERROR = 500;
    int SC_NOT_IMPLEMENTED = 501;
    int SC_BAD_GATEWAY = 502;
    int SC_SERVICE_UNAVAILABLE = 503;
    int SC_GATEWAY_TIMEOUT = 504;
    int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
```

**应用:**

*   向浏览器输出信息
*   下载文件

