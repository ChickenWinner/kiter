# kiter

### 介绍
`集成工具类kiter:`

开发人员在编码的过程中，难免会用到很多工具类，而这些工具类五花八门，常常让人不知道如何选择与使用。

kiter旨在`集成常用的工具类并简化其用法`，让开发人员在编码过程中`只引入一个工具jar包即可满足大多数需求`。

kiter中的工具类将会从使用的简易程度与效率上均衡，尽力做到`“更全、更方便、更快”。`

`本项目刚刚起步，欢迎大家共同参与构建!`

`Integrated tool class kiter:`

In the process of coding, developers will inevitably use a lot of tools, and these tools are so diverse that they often don't know how to choose and use them.

Kiter is designed to "integrate common tool classes and simplify their usage", allowing developers to "integrate only one tool jar package to meet most requirements" during the encoding process.

The tools in kiter will be balanced in terms of ease of use and efficiency, and try to be `"more complete, more convenient, faster". `

`This project has just started, welcome everyone to participate in the construction!`

### 开源地址
本项目开源在GitHub及Gitee(码云)上   

    GitHub地址：https://github.com/ChickenWinner/kiter
    
    Gitee地址：https://gitee.com/HappyChicken/kiter

### 实现功能
以下列举的仅仅是部分可供您使用的方法，还有一部分没有列举出来，可能需要您慢慢探索了:)

The methods below are some of usable methods, the others need your discovery:)
+ #### [Tuz包](https://github.com/FishGoddess/Tuz)（资源加载包）
    + Tuz (Class)：`核心系统`
        + 方法列表 ↓
        + load：加载资源，初始化 Tuz
        + use：使用资源，可以获取到加载过的资源
        + useGracefully：优雅地使用资源，当找不到资源时返回自定义的默认值
    + TuzConfig (Class)：`配置类`
        + 方法列表 ↓
        + isSingleton：获得类实例生成方式，默认是单例
        + setSingleton：设置类实例生成方式，可选单例或多例
    + Loadable (Interface)：`加载器接口`
        + 方法列表 ↓
        + namespace：获得命名空间
        + load：加载资源
    + Tuzable (Interface)：`这是一个信仰，没有任何方法:)`
    + ClassHelper (Class)：`类操作工具包`
        + 方法列表 ↓
        + newInstance：生成类对象实例
    + IOHelper (Class)：`IO 操作工具包`
        + 方法列表 ↓
        + newReader：获得一个指向某个资源的读取器
        + newReaderToFileSystem：获得一个指向文件系统的某个资源的读取器
        + newReaderToClasspath：获得一个指向类路径的某个资源的读取器
        + getResourceFromFileSystem：获得一个指向文件系统的某个资源的路径
        + getResourceFromClasspath：获得一个指向类路径的某个资源的路径
    + LogHelper (Class)：`日志操作工具包`
        + 方法列表 ↓
        + 就常用的 debug/info/warn/error 等日志记录方法呗
    + NameSpaceHelper (Class)：`命名空间工具包`
        + 方法列表 ↓
        + generateNameSpace：生成命名空间名字，默认从 1 开始生成
        
+ #### generator包(生成器包)
    + FileNameGenerator(Class)：`文件名生成器`
        + 方法列表↓
        + randomName：自定义文件名生成方法
        + timestampName：时间戳格式文件名
        + UUIDName：UUID格式文件名
    + StringGenerator(Class)：`字符串生成器`
        + 方法列表↓
        + getUUIDArray：获得UUID数组
        + getUUID：获得UUID
        
 + #### file包(文件包)
    + FileOperator(Class)：`文件操作者`
        + 方法列表↓
        + fileIsExits：判断文件是否存在
        + deleteFileElegant：优雅的删除文件(如果是目录，则不删除)
        + deleteFileViolent：暴力的删除包括目录的文件
        + getFileSize：返回文件大小
        + renameFile：重命名文件
        + getExtention：返回文件名后缀
        + getNamePart： 返回文件名部分
        + getFilePath：得到文件绝对路径
        + copyFile：复制文件
        + copyDir：复制目录
        + createDir：创建目录，支持多级目录
        + createFile：创建文件，如果带有目录，先创建目录
        + readFile: 根据指定编码读取文件
        + writeFile：将字节内容写入指定文件(覆盖形式)
        + writeFileAppend：将字节内容写入指定文件(追加形式)
        
  + #### 未完待续...
         
### 友情链接
 + [水不要鱼 & GitHub](https://github.com/FishGoddess)
 
 + [Mackyhuang & GitHub](https://github.com/Mackyhuang)
 
 + [MaDao & GitHub](https://github.com/Madaovo)