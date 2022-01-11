# HandWrite-Tomcat

Netty作为底层通讯框架，可以用来实现Web容器。

这里首先使用传统的**Java BIO**来构建一个Tomcat容器，后面尝试使用**Netty**对项目进行重构。





​	 Tomcat主要入口是web.xml。web.xml文件中主要配置Servlet、Filter、Listener等，而Servlet、Filter、Listener在J2EE中只是抽象的实现，具体的业务逻辑由我们自己实现。

