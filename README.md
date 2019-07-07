# dynamic-proxy-adapter
使用动态代理和适配器模式, 完成集成第三方client记录请求日志功能, 配合java-backend project使用

## 运行此项目除了要将整个java-backend project clone至本地外, 还需要执行 thirdparty/mvn-install.sh脚本, 将自定义的第三方jar包添加至本地仓库


## 动态代理知识点:
1. 被代理对象必须要实现一个接口, 需要面向接口编程
   ```java
      /*
         本例中被代理对象是第三方的Client ThirdPartyClient.java
         但是它是一个类无继承任何一个接口, 所以使用不了代理模式,
         因此创建了一个ThirdPartyAdapter.java接口作为适配器, 并
         只需要将它的同步用户、订单信息接口暴露出来(至于为何这样
         做, 可以把它当成一种需求, 现在这么做是为了实现这个需求).
         之后, 接口暴露出来了, 需要有一个真正的接口类型的实例, 进
         而创建了ThirdPartyAdapterImpl.java类, 作为它的实例, 并
         继承ThirdPartyClient.java, 保证能调用到ThirdPartyClient
         中的方法, 也就是super.具体方法();
      */
       
      // ThirdPartyAdapter.java
      public interface ThirdPartyAdapter {
        
          void syncUserInfo(Object info);
       
          void syncOrderInfo(Object info);
      }
   
      // ThirdPartyAdapterImpl.java
      public class ThirdPartyAdapterImpl extends ThirdPartyClient implements ThirdPartyAdapter {

          @Override
          public void syncUserInfo(Object info) {
              super.syncUserInfo(info);
          }
       
          @Override
          public void syncOrderInfo(Object info) {
              super.syncOrderInfo(info);
          }
      }
   ```
2. 代理对象需要实现InvocationHandler接口
   ```java 
       // ThirdPartyProxy.java
       public class ThirdPartyProxy implements InvocationHandler {
       
           private Logger logger = LoggerFactory.getLogger(ThirdPartyProxy.class);
       
           private ThirdPartyAdapter thirdPartyAdapter;
       
           public void setThirdPartyAdapter(ThirdPartyAdapter thirdPartyAdapter) {
               this.thirdPartyAdapter = thirdPartyAdapter;
           }
       
           @Override
           public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               logger.info("proxy ================第三方client代理开始==================");
               logger.info("proxy 代理执行的方法: [ " + method.getName() + " ]");
               logger.info("proxy 代理执行的参数: [ " + Arrays.toString(args) + " ]");
       
               Object object = null;
       
               try {
                   object = method.invoke(thirdPartyAdapter, args);
               } catch (Exception e) {
                   logger.error("proxy 代理调用发生异常, 真实异常信息为: ", e.getCause());
                   throw e.getCause();
               } finally {
                   logger.info("proxy 代理方法执行完成");
               }
       
               return object;
           }
       }
   ```
3. 被代理对象需要作为代理对象的属性
   ```java
       //见上述的code
       private ThirdPartyAdapter thirdPartyAdapter;
              
       public void setThirdPartyAdapter(ThirdPartyAdapter thirdPartyAdapter) {
          this.thirdPartyAdapter = thirdPartyAdapter;
       }
   ```
4. 创建代理对象时, 需要将被代理对象注入到代理对象的属性中
   ```java
       // InitThirdPartyProxy.java
       public class InitThirdPartyProxy {

           @Bean
           public ThirdPartyAdapter initThirdPartyAdapter() {
               // 被代理对象
               ThirdPartyAdapter thirdPartyAdapter = new ThirdPartyAdapterImpl();
       
               // 代理对象
               ThirdPartyProxy thirdPartyProxy = new ThirdPartyProxy();
               // 将被代理对象添加至代理对象的被代理对象属性中
               thirdPartyProxy.setThirdPartyAdapter(thirdPartyAdapter);
       
               return new ConstructDynamicProxy<ThirdPartyAdapter>(thirdPartyAdapter, thirdPartyProxy).getProxy();
           }
       }
   ```
5. 将代理对象加入到spring容器
   ```java
       /**
        * 将该注解添加至springboot入口类中, 加入到spring容器
        */
       @Target(ElementType.TYPE)
       @Documented
       @Retention(value = RetentionPolicy.RUNTIME)
       @Import(value = InitThirdPartyProxy.class)
       public @interface EnableThirdPartyProxy {
       }
    ```
    
## 总结
1. 代理类一般是无状态的对象, 应该设计成单例模式, 而spring就存在典型的单例模式管理容器, 添加进去即可
2. 通过代理方法添加 "proxy"前缀, 方便使用grep追踪日志信息。