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
 
 3. [tuz( ioc、资源管理 )](https://github.com/FishGoddess/Tuz)
 
 4. encrypt( 加密工具 )
 
 5. cache( 缓存操作 )
 
 6. date( 时间工具 )
 
 7. json( json工具 )
 
 8. 开发中, 敬请期待~
    
 + ### generator包
    + FileNameGenerator 文件名生成器
    ```java
    public class GeneratorTest {
       // 测试文件名生成器
        @Test
        public void testFileNameGenerator() {
    
            // randomName方法 可以传入继承了AbstractNameGenerator抽象类的具体实现类
            // 该方法主要用于让用户自定义生成文件名方式
            FileNameGenerator.randomName(new TimestampNameGenerator(0, null, "后缀"));// 输出：20190420112021724后缀
    
            // -----以下是一些默认实现类-----
    
            // 根据时间戳生成文件名 使用默认长度17
            FileNameGenerator.timestampName(".jpg"); // 输出：20190420112639909.jpg
            // 你也可以手动指定长度
            FileNameGenerator.timestampName(5, ".png"); // 输出：39909.png
    
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
         
 + ### file包(文件包)
    + FileOperator 文件操作类
    ```java
    public class FileTest {
    
        // 测试文件操作
        @Test
        public void testFileOperator() throws Exception {
    
            // 创建文件，如果文件已经存在，则抛出异常
            // 支持带目录的文件路径 如O://tset/my.txt 将同时创建目录和文件
            FileOperator.createFile("O://my.txt"); // 创建成功返回true，失败返回falese
            // 创建目录
            FileOperator.createDir("O://test"); // 创建成功返回true，失败返回false
    
            // 判断文件是否存在
            FileOperator.fileIsExits("O://test.txt"); // 存在返回true,不存在返回false
    
            // 删除文件 包括优雅的删除和暴力的删除 区别在于是否删除目录型文件
            FileOperator.deleteFileElegant("O://test.txt"); // 如果删除成功，返回true,否则返回false
            FileOperator.deleteFileViolent("O://test.txt");
    
            // 返回文件大小，一个是数字，一个是文字描述
            FileOperator.getFileSize(new File("O://test.txt")); // 输出：14
            FileOperator.getFileSizeDetail(new File("O://test.txt")); // 输出：14.0B
    
            // 重命名文件，支持对文件或者目录重命名
            FileOperator.renameFile("O://dir", "newdir"); // 成功返回true,失败返回false
    
            // 返回文件扩展名，如果是文件没有后缀返回空
            FileOperator.getExtention("O://test.txt"); // 输出：.txt
            // 得到文件的名字部分，实际上就是路径中的最后一个路径分隔符后的部分。
            FileOperator.getNamePart("O://test/test.txt"); // 输出：test.txt
    
            // 从文件名得到文件的绝对路径
            FileOperator.getFilePath("pom.xml"); // 输出：O:\IdeaProject\kiter\pom.xml
    
            // 文件复制
            FileOperator.copyFile("O://test.txt", "O://new.txt");
            FileOperator.copyFile(new File("O://test.txt"), new File("O://new.txt"));
            // 目录复制
            FileOperator.copyDir("O://test", "O://new");
    
            // 写文件，默认是覆盖
            FileOperator.writeFile("O://test.txt", "1".getBytes());
            // 追加的写文件
            FileOperator.writeFileAppend("O://test.txt", "2".getBytes());
            // 读文件 返回的是一个List<String> 每一个List元素是文件的一行
            // 你可以指定读取文件时的编码
            FileOperator.readFile("O://test.txt", Charset.defaultCharset()); //输出：[12]
        }
    }
    ```
    + FileParser 文件解析类
    ```java
    public class FileTest {
     
        // 测试文件解析器
        @Test
        public void testFileParser() throws Exception {
            // 解析excel内容，用户可以实现监听器来自定义处理excel前后的逻辑
            FileParser.parseExcel(new File("O://test.xlsx"), new DefaultExcelListener(), false);
            
            // 解析excel内容 并存储到list中
            System.out.println(FileParser.parseExcelToList("O://test.xlsx")); // 输出：[[姓名, 年龄, 性别], [小王, 22, 男], [小梦, 21, 女]]
            
            // 解析excel内容，并转换为对应的实体类集合
            FileParser.parseExcelToBeanList("O://test.xlsx", Person.class);
    
        }
     }
    ```
     
 + ### encrypt包(加密包)
    + MD5encrypt MD5加密
    ```java
    public class EncryptTest {
    
        // 测试MD5加密
        @Test
        public void testMD5encrypt() {
            // 普通MD5加密
            MD5encrypt.getStrMD5("password"); // 输出：5f4dcc3b5aa765d61d8327deb882cf99
    
            // MD5加盐加密
            // 如果加入的盐值为""或null，默认用随机数做盐值
            MD5encrypt.getSaltMD5("password", ""); // 输出：{password=7493add49bf8b4e157d450f2f0b865d7, salt=4392854518933427}
        }
    }
    ```
     
 + ### cache包(缓存包)
    + RedisOperator redis操作类
    ```java
    public class RedisTest {
    
        // 测试Redis操作
        @Test
        public void testRedisOperator() {
            // 初始化redis操作器
            // 拥有多个构造函数，这里实例最简单的构造函数
            RedisOperator redisOperator = new RedisOperator("127.0.0.1", 6379);
    
            // 获取redis连接池，适用于想自由发挥的同学
            JedisPool jedisPool = redisOperator.getJedisPool();
    
            // -----以下是实现一些常用的redis命令-----
    
            // 根据key得到值，可以转为对应的对象
            redisOperator.get("key", Person.class);
            // 设置key，可以设置过期时间，小于等于0表示永不过期
            redisOperator.set("key", 0, "value"); // 设置结果 true成功 false失败
            // 判断key是否存在
            redisOperator.exists("key");
            // 对应key值加减1 用于数字类型
            redisOperator.incr("key"); // 返回加1后的结果
            redisOperator.decr("key"); // 返回减1后的结果
        }
    }
    ```
  
 + ### date包(时间包)
    + DateUtil 日期工具类
    ```java
    public class DateTest {   
        @Test
        public void testDateUtil() throws Exception {
    
            // 格式化日期，如果未指定格式则使用默认格式
            DateUtil.dateFormat(new Date(), "yyyy-MM-dd"); // 输出：2019-04-20
    
            // 日期加减  分别对应年 月 日 时 分 秒
            DateUtil.dateAdd(new Date(), 1, 1 ,1,1, 1); // 返回一个date对象
            // 当然也可以使用简单的操作，如年份加减
            DateUtil.dateAddYears(new Date(), 1);
    
            // 将字符串日期转为日期对象，注意格式要与字符串一致
            DateUtil.str2Date("2018-1-1", "yyyy-MM-dd");
    
            // 比较2个日期的大小
            DateUtil.compareDate(new Date(), new Date()); // 大于返回1，相等返回0，小于返回-1
    
            // 计算时间差
            // 返回时间差秒数
            DateUtil.getBetweenTime(new Date(), new Date()); // 输出：0
            // 返回时间差文字描述
            System.out.println(DateUtil.getBetweenTimeStr(new Date(), new Date())); // 输出：0天0小时0分0秒
        }
    }
    ```
 + ### json包(Json工具包)
    + JsonUtil Json工具类
    ```java
    public class JsonTest {   
        @Test
        public void testJsonUtil() {
            // 实体类，注意要有get和set方法
            Person person = new Person();
            
            // 将实体类转为Json串
            String s = JsonUtil.beanToString(person);
            System.out.println(s);; // 输出：{"age":10,"name":"林夕"}
    
            // 将Json串转为实体类
            JsonUtil.stringToBean(s, Person.class); // 输出：Person{name='林夕', age=10}
        }   
    }
    ```
        
 + ### 未完待续...
        
### 友情链接
 + [水不要鱼 & GitHub](https://github.com/FishGoddess)
 
 + [Mackyhuang & GitHub](https://github.com/Mackyhuang)
 
 + [MaDao & GitHub](https://github.com/Madaovo)