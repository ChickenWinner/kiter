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

 1. [generator(随机文件名、字符串生成器)](#generator)
 
 2. [file(文件操作)](#file)
    
 + #### <span id="generator">generator包</span>
    + FileNameGenerator `文件名生成器`
    ```java
       public class GeneratorTest {
           
           // 测试文件名生成器
           @Test
           public void testFileNameGenerator() {
       
               // randomName方法 可以传入继承了AbstractNameGenerator抽象类的具体实现类
               // 该方法主要用于让用户自定义生成文件名方式
               System.out.println(
                       FileNameGenerator.randomName(
                               new TimestampNameGenerator(0, null, "后缀")));// 输出：20190420112021724后缀
       
               // -----以下是一些默认实现类------
       
               // 根据时间戳生成文件名 使用默认长度17
               System.out.println(FileNameGenerator.timestampName(".jpg"));// 输出：20190420112639909.jpg
               // 你也可以手动指定长度
               System.out.println(FileNameGenerator.timestampName(5, ".png"));// 输出：39909.png
       
               // 根据UUID生成文件名
               System.out.println(FileNameGenerator.UUIDName(".jpg")); // 输出：867fa14ed75a4d729.jpg
               // 同样可以指定长度
               System.out.println(FileNameGenerator.UUIDName(6, ".png")); // 输出：4b370c.png
           }
       }
    ```
    +
        
 + #### <span id="file">file包(文件包)</span>
    
        
 + #### [Tuz包](https://github.com/FishGoddess/Tuz)（资源加载包）
    
     
 + #### encrypt包(加密包)
  
        
 + #### cache包(缓存包)
  
            
 + #### date包(时间包)
    
        
 + #### 未完待续...
        
### 友情链接
 + [水不要鱼 & GitHub](https://github.com/FishGoddess)
 
 + [Mackyhuang & GitHub](https://github.com/Mackyhuang)
 
 + [MaDao & GitHub](https://github.com/Madaovo)