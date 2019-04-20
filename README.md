# kiter  [![Version](./maven_central.svg)](https://github.com/ChickenWinner/kiter) [![License](./license.svg)](https://www.apache.org/licenses/LICENSE-2.0.html) 

### 介绍
`集成工具箱 kiter:`

开发人员在编码的过程中，难免会用到很多工具类，而这些工具类五花八门，常常让人不知道如何选择与使用。

kiter旨在`集成常用的工具类并简化其用法`，让开发人员在编码过程中`只引入一个工具jar包即可满足大多数需求`。

kiter中的工具类将会从使用的简易程度与效率上均衡，尽力做到`“更全、更方便、更快”。`

`欢迎大家共同参与构建!`

### 开源地址
本项目开源在GitHub及Gitee(码云)上   

    GitHub地址：https://github.com/ChickenWinner/kiter
    
    Gitee地址：https://gitee.com/HappyChicken/kiter

### 实现功能
以下列举的仅仅是部分可供您使用的方法，还有一部分没有列举出来，可能需要您慢慢探索了:)

 1. generator( 随机生成文件名、字符串 )
 
 2. file( 文件操作 )
 
 3. [Tuz( ioc、资源管理 )](https://github.com/FishGoddess/Tuz)
 
 4. encrypt( 加密工具 )
 
 5. cache( 缓存操作 )
 
 6. date( 时间工具 )
 
 7. 开发中, 敬请期待~
    
 + #### generator包
    + FileNameGenerator 文件名生成器
    ```java
    public class GeneratorTest {
       // 测试文件名生成器
        @Test
        public void testFileNameGenerator() {
    
            // randomName方法 可以传入继承了AbstractNameGenerator抽象类的具体实现类
            // 该方法主要用于让用户自定义生成文件名方式
            FileNameGenerator.randomName(new TimestampNameGenerator(0, null, "后缀"));// 输出：20190420112021724后缀
    
            // -----以下是一些默认实现类------
    
            // 根据时间戳生成文件名 使用默认长度17
            FileNameGenerator.timestampName(".jpg");// 输出：20190420112639909.jpg
            // 你也可以手动指定长度
            FileNameGenerator.timestampName(5, ".png");// 输出：39909.png
    
            // 根据UUID生成文件名
            FileNameGenerator.UUIDName(".jpg"); // 输出：867fa14ed75a4d729.jpg
            // 同样可以指定长度
            FileNameGenerator.UUIDName(6, ".png"); // 输出：4b370c.png
        }
   }
    ```
    + StringGenerator 字符串生成器
    ```java
    public class GeneratorTest {
    
        // 测试字符串生成器
        @Test
        public void testStringGenerator() {
            // 生成UUID数组，指定生成个数以及替换UUID自带的'-'
           /* 输出：06f6e20e/b1fe/47db/8305/011d7614a8dc
                 aaf83ab/b8da/4ec7/b6ef/d0865b244207 
           */
            StringGenerator.getUUIDArray(2, "/");
    
            // 生成单个UUID，如果不想替换'-'可以选择传入''或者null
            StringGenerator.getUUID(null); // 输出：8456e2c8-64c3-4448-93a7-ed97dca4ced9
            StringGenerator.getUUID("@"); // 输出：943fdcbe@bc52@4254@9f87@bdea2d2c1b01
    
            // 你也可以指定UUID的长度
            StringGenerator.getUUID(5, ""); // 输出：8b8df
            // 如果长度小于0，抛出异常
            StringGenerator.getUUID(-1, "");// java.lang.IllegalArgumentException: 请求的长度为负数
        }
     }
    ```
         
 + #### file包(文件包)
    
        
 + #### [Tuz包](https://github.com/FishGoddess/Tuz)（资源加载包）
    
     
 + #### encrypt包(加密包)
  
        
 + #### cache包(缓存包)
  
            
 + #### date包(时间包)
    
        
 + #### 未完待续...
        
### 友情链接
 + [水不要鱼 & GitHub](https://github.com/FishGoddess)
 
 + [Mackyhuang & GitHub](https://github.com/Mackyhuang)
 
 + [MaDao & GitHub](https://github.com/Madaovo)