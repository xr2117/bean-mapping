# Bean Mapping

**BeanMapping** 致力于**Bean**之间的映射方案。此项目包含自动映射对象与类之间的属性，手动映射类与对象特殊属性。方便开发者通过**BeanMapping**工具类，轻松使用工具来简化应用开发。

依托**BeanMapping**，您需要适配以下要求:

**JDK**：JDK8+

**MAVEN**: apache-maven-3.5.0+



为**BeanMapping**贡献代码请参考联系查看开发者信息

## 主要功能

* **语法支持**：默认支持**JDK8**，**lambda**表达式，仅限使用JDK8+版本
* **对象对类自动映射**：默认支持 **source -> class**类的自动映射
* **对象对类手动映射**：适配自动映射,支持不一致字段名称,不一致类型字手动映射。
* **集合对集合的自动映射**：默认支持集合 **source**->**class**类集合的自动映射。
* **集合对集合的手动映射**：适配自动映射,支持不一致字段名称,不一致类型字手动映射。
* **集合对集合的范围自动映射**：默认支持范围集合**source -> class**类集合的自动映射，自动适应范围。
* **集合对集合的范围自动映射**：适配自动范围集合映射,支持不一致字段名称,不一致类型字手动映射。
* **对象对类嵌自动套映射**：默认支持嵌套映射，类与类的继承关系之间自动映射
* **对象对类手动嵌套映射**：适配自动映射，类与类的继承关系不一致字段名称,不一致类型字手动映射。
* **对象对类自动映射JSON字符串**: 默认支持 **source -> class**类的自动映射JSON字符串
* **对象对类手动映射JSON字符串**: 适配自动映射,支持不一致字段名称,不一致类型字手动映射**JSON**字符串
* **集合对集合的自动映射JSON字符串**: 默认支持集合 **source -> class**类集合的自动映射**JSON**字符串
* **集合对集合的手动映射JSON字符串**: 适配自动映射,支持不一致字段名称,不一致类型字手动映射**JSON**字符串

****


功能演示请参考源码测试用例。

## 组件

```java
toBean(T source, Class<R> target)

toBean(T source, Class<R> target, BiConsumer<T, R> biConsumer)

toList(Collection<T> source, Class<R> target)

toList(Collection<T> source, Class<R> target, BiConsumer<T, R> biConsumer)

toListRange(List<T> source, Class<R> target, int skip)

toListRange(List<T> source, Class<R> target, int skip, BiConsumer<T, R> biConsumer)

toListRange(List<T> source, Class<R> target, int skip, int limit)

toListRange(List<T> source, Class<R> target, int skip, int limit, BiConsumer<T, R> biConsumer)

toSet(Collection<T> source, Class<R> target)

toSet(Collection<T> source, Class<R> target, BiConsumer<T, R> biConsumer)

toJsonBean(T source, Class<R> target)

toJsonBean(T source, Class<R> target, BiConsumer<T, R> biConsumer)

toJsonList(Collection<T> source, Class<R> target)

toJsonList(Collection<T> source, Class<R> target, BiConsumer<T, R> biConsumer)

toJsonListRange(List<T> source, Class<R> target, int skip)

toJsonListRange(List<T> source, Class<R> target, int skip, BiConsumer<T, R> biConsumer)

toJsonListRange(List<T> source, Class<R> target, int skip, int limit)

toJsonListRange(List<T> source, Class<R> target, int skip, int limit, BiConsumer<T, R> biConsumer)

toJsonSet(Collection<T> source, Class<R> target)

toJsonSet(Collection<T> source, Class<R> target, BiConsumer<T, R> biConsumer)
```



## 如何构建

* **BeanMapping**：最低支持 JDK 1.8。
* **BeanMapping**：最低支持apache-maven-3.5.0+

## 如何使用

### 如何引入依赖

如果需要使用已发布的版本，在 `dependencyManagement` 中添加如下配置。

```xml
<dependencies>
    <dependency>
        <groupId>com.github.xr2117</groupId>
        <artifactId>bean-mapping</artifactId>
        <version>2.1.1</version>
    </dependency>
</dependencies>
```

然后在 `dependencies` 中添加自己所需使用的依赖即可使用。


### 邮件列表

xr2117@126.com，欢迎通过此邮件讨论与 **BeanMapping** 相关的一切。
