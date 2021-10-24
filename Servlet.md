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

