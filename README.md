# HandWrite-Tomcat

Netty作为底层通讯框架，可以用来实现Web容器。

这里首先使用传统的**Java BIO**来构建一个Tomcat容器，后面尝试使用**Netty**对项目进行重构。



## 1、Tomcat是什么？

​		当我们在做web项目时，多数需要http协议，也就是基于请求和响应。比如你在百度输入一行内容搜索，那么百度服务器如何处理这个请求呢？他需要创建servlet来处理，servlet其实就是java程序，只是在服务器端的java程序，servlet通过配置文件拦截你的请求，并进行相应处理，然后展示给你相应界面，那么servlet如何创建？ 这时候tomcat用到了，它就是帮助你创建servlet的东西，所以也称web容器，没有它，没法运行web项目。



​	 Tomcat主要入口是web.xml。web.xml文件中主要配置Servlet、Filter、Listener等，而Servlet、Filter、Listener在J2EE中只是抽象的实现，具体的业务逻辑由我们自己实现。

