package file;

import org.junit.Test;
import wang.redder.kiter.file.DefaultExcelListener;
import wang.redder.kiter.file.FileOperator;
import wang.redder.kiter.file.FileParser;
import java.io.File;
import java.nio.charset.Charset;

/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/20 14:24
 */
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
