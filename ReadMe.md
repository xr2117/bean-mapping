### 自动BeanMapping工具类
##### 1.0 版本发布
##### 功能
1. 支持Lambda表达式手动映射不一致字段
2. 支持对象自动映射字段一致对象
3. 支持List自动映射List
4. 支持List自动映射一致字段,手动映射不一致字段
5. 支持Set自动映射Set
6. 支持Set自动映射一致字段,手动映射不一致字段

##### 2.0 版本发布
##### 新增功能
1.新增范围List方法
2.新增范围转JsonList方法
##### 修复
1.修复目标对象缺少无参构造方法引发异常问题

##### 2.0.1 版本发布
##### 修复
1.修复静态属性抛出异常问题
2.修复实现序列化无法映射问题

##### 2.1 版本发布
##### 新增功能
1.新增嵌套映射,支持继承类映射
##### 修复
1.修复映射类为抽象类错误

##### maven依赖
```xml
<dependency>
  <groupId>com.github.xr2117</groupId>
  <artifactId>bean-mapping</artifactId>
  <version>2.1.0</version>
</dependency>
```