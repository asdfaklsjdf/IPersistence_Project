1、Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？

在 XML 映射文件内，以 XML 标签的形式编写动态 SQL ，完成逻辑判断和动态拼接 SQL 的功能。

<if/>、<choose/>、<where/>、<otherwise/>、<trim/>、<when/>、<set/>、<foreach/>、<bind/>

使用 OGNL 的表达式，从 SQL 参数对象中计算表达式的值,根据表达式的值动态拼接 SQL ，以此来完成动态 SQL 的功能



2、Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？

Mybatis仅支持association关联对象和collection关联集合对象的延迟加载，association指的就是一对一，collection指的就是一对多查询。在Mybatis配置文件中，可以配置是否启用延迟加载lazyLoadingEnabled=true|false。



它的原理是，使用CGLIB创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName()，拦截器invoke()方法发现a.getB()是null值，那么就会单独发送事先保存好的查询关联B对象的sql，把B查询上来，然后调用a.setB(b)，于是a的对象b属性就有值了，接着完成a.getB().getName()方法的调用



3、Mybatis都有哪些Executor执行器？它们之间的区别是什么？

SimpleExecutor、ReuseExecutor、BatchExecutor

区别

SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。

ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于`Map<String, Statement>`内，供下一次使用。简言之，就是重复使用Statement对象。

BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。



4、简述下Mybatis的一级、二级缓存（分别从存储结构、范围、失效场景。三个方面来作答）？

存储结构：

​	一级缓存底层是个hashMap：key：statementId，params，boundSql，rowBounds；value：查询结果对象

​	二级缓存底层是个hashMap：key：statementId，params，boundSql，rowBounds；value：查询结果对象的数据



范围：一级缓存在每一个sqlSession中，二级缓存在Mapper实例中



失效场景：

​	一级缓存：sqlSession执行了commit操作，就会情况一级缓存

​	二级缓存：增，删，改操作会清空namespace下的全部缓存







5、简述Mybatis的插件运行原理，以及如何编写一个插件？

对ExecutorStatementHandlerParameterHandlerResultSetHandler四个组件进行拦截，用来增强核心对象功能，增强功能的本质是动态代理



全局mybatis配置文件添加插件类

插件类实现Interceptor，重写里面的2个方法（Intercept，plugin，setProperties）

类添加注释：

```java
@Intercepts({
        @Signature(type = StatementHandler.class,//拦截接口
                    method = "prepare",//拦截的方法
                    args = {Connection.class, Integer.class})//拦截方法的入参，按顺序写
})
```

