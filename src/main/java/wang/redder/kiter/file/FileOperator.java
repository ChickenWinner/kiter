package wang.redder.kiter.file;


import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;


/**
 * 文件操作类，包含各种操作文件的方法
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/1 13:52
 */
public class FileOperator {

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件名
     * @return true:存在  false:不存在
     */
    public static boolean fileIsExits(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


    /**
     * 删除文件工具类
     *
     * @param filePath  文件路径
     * @param isViolent 针对文件夹是否暴力删除
     *                  true:暴力删除 false:不删除(这种情况下如果碰到文件夹直接返回false)
     * @return 删除结果 true:删除成功 false删除失败
     * @throws FileNotFoundException 文件找不到
     */
    private static boolean deleteFile(String filePath,
                                      boolean isViolent) throws FileNotFoundException {
        File file = new File(filePath);
        // 如果文件存在
        if (file.exists()) {
            // 如果是目录
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                // 目录不为空且想要暴力删除
                if (files != null && files.length > 0 && isViolent) {
                    // 暴力删除全部文件
                    for (File file1 : files) {
                        // 一个删除失败，返回失败
                        if (!file1.delete()) {
                            return false;
                        }
                    }
                }
            }
            // 返回删除结果
            return file.delete();
        } else {
            throw new FileNotFoundException(file.getName() + "找不到文件");
        }
    }

    /**
     * 优雅的删除 如果删除的是文件夹，直接失败
     *
     * @param filePath 文件路径
     * @return 删除结果
     * @throws FileNotFoundException 文件找不到异常
     */
    public static boolean deleteFileElegant(String filePath) throws FileNotFoundException {
        return deleteFile(filePath, false);
    }

    /**
     * 暴力的删除 如果是文件夹，也会删除文件夹下的文件
     *
     * @param filePath 文件路径
     * @return 删除结果
     * @throws FileNotFoundException 文件找不到异常
     */
    public static boolean deleteFileViolent(String filePath) throws FileNotFoundException {
        return deleteFile(filePath, true);
    }


    /**
     * 返回文件大小
     *
     * @param file 文件
     * @return 字符串
     */
    public static String getFileSize(File file) throws IOException {
        FileChannel fc = null;
        // 文件存在
        if (file.exists() && file.isFile()) {
            FileInputStream fis = new FileInputStream(file);
            fc = fis.getChannel();
            return getPrintSize(fc.size());
        } else {
            throw new FileNotFoundException(file.getName() + " 文件不存在");
        }

    }

    /**
     * 输出文件大小
     *
     * @param size 文件字节大小
     * @return 文件大小
     */
    private static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < 1024) {
            return String.valueOf(value) + "B";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < 1024) {
            return String.valueOf(value) + "KB";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        if (value < 1024) {
            return String.valueOf(value) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }


    /**
     * 重命名文件
     *
     * @param filePath 文件路径，这里不包括文件名
     * @param oldName  旧文件名
     * @param newName  新文件名
     * @return 修改结果
     * @throws IOException 这里包含了文件找不到异常和文件已经存在异常
     */
    public static boolean renameFile(String filePath, String oldName,
                                     String newName) throws IOException {
        // 新的文件名和以前文件名不同时,才有必要进行重命名
        if (!oldName.equals(newName)) {
            File oldfile = new File(filePath + File.separator + oldName);
            if (!oldfile.exists()) {
                throw new FileNotFoundException(oldfile.getName() + " 找不到文件");
            }
            File newfile = new File(filePath + File.separator + newName);
            //若在该目录下已经有一个文件和新文件名相同，则不允许重命名
            if (newfile.exists()) {
                throw new FileExistsException(newfile.getName() + " 文件已经存在");
            } else {
                return oldfile.renameTo(newfile);
            }
        }
        return false;
    }


    /**
     * 获取文件扩展名
     *
     * @param filePath 文件路径
     * @return 文件扩展名
     */
    public static String getExtention(String filePath) {
        int pos = filePath.lastIndexOf(".");
        return filePath.substring(pos);
    }


    /**
     * 得到文件的名字部分。 实际上就是路径中的最后一个路径分隔符后的部分。
     *
     * @param filePath 文件全路径名
     * @return 文件名
     */
    public static String getNamePart(String filePath) {
        // 找到最后一个/
        int point = getPathLastIndex(filePath);
        int length = filePath.length();
        // 如果没有/ 直接返回
        if (point == -1) {
            return filePath;
        } else {
            if (point == length - 1) {
                int secondPoint = getPathLastIndex(filePath, point - 1);
                if (secondPoint == -1) {
                    if (length == 1) {
                        return filePath;
                    } else {
                        return filePath.substring(0, point);
                    }
                } else {
                    return filePath.substring(secondPoint + 1, point);
                }
            } else {
                return filePath.substring(point + 1);
            }
        }
    }


    /**
     * 得到路径分隔符在文件路径中最后出现的位置
     *
     * @param filePath 文件全路径
     * @return 分隔符在文件路径中最后出现的位置
     */
    private static int getPathLastIndex(String filePath) {
        int point = filePath.lastIndexOf("/");
        if (point == -1) {
            point = filePath.lastIndexOf("");
        }
        return point;
    }


    /**
     * 得到路径分隔符在文件路径中指定位置前最后出现的位置
     *
     * @param filePath  文件路径
     * @param fromIndex 指定位置
     * @return 分隔符在文件路径中指定位置前最后出现的位置
     */
    private static int getPathLastIndex(String filePath, int fromIndex) {
        int point = filePath.lastIndexOf("/", fromIndex);
        if (point == -1) {
            point = filePath.lastIndexOf("", fromIndex);
        }
        return point;
    }


    /**
     * 从文件名得到文件绝对路径
     *
     * @param fileName 文件名
     * @return 文件绝对路径
     */
    public static String getFilePath(String fileName) {
        return new File(fileName).getAbsolutePath();
    }


    /**
     * 复制文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     */
    public static void copyFile(File srcFile, File destFile) {
        try {
            // 调用了FileUtils下已有方法
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 复制文件夹
     *
     * @param srcDir  源文件夹
     * @param destDir 目标文件夹
     */
    public static void copyDir(File srcDir, File destDir) {
        try {
            // 调用了FileUtils下已有方法
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建目录，如果有父目录，创建父目录
     *
     * @param filePath 文件路径
     * @return 创建结果
     * @throws IOException 文件已经存在异常
     */
    public static boolean createDir(String filePath) throws IOException {
        File dir = new File(filePath);
        // 如果目录不存在
        if (!dir.exists()) {
            return dir.mkdirs();
        } else {
            throw new FileExistsException(dir.getName() + " 文件已经存在");
        }
    }


    /**
     * 创建文件，如果有指定目录，也会创建目录
     *
     * @param filePath 文件路径
     * @return 创建结果
     * @throws IOException 文件已经存在异常
     */
    public static boolean createFile(String filePath) throws IOException {
        File file = new File(filePath);
        // 目录
        File fileParent = file.getParentFile();
        // 如果指定了目录，但是该目录不存在，先创建目录
        if (!fileParent.exists()) {
            // 目录创建失败，返回失败结果
            if (!fileParent.mkdirs()) {
                return false;
            }
        } else {
            throw new FileExistsException(fileParent.getName() + " 文件已经存在");
        }
        // 文件不存在则创建
        if (!file.exists()) {
            return file.createNewFile();
        } else {
            // 抛出文件存在异常
            throw new FileExistsException(file.getName() + " 文件已经存在");
        }
    }


    /**
     * 读取file内容
     *
     * @param filePath 文件路径
     * @param charset  文件编码
     * @return 文件内容
     * @throws IOException IO异常
     */
    public static String readFile(String filePath, Charset charset) throws IOException {
        // 构造path
        Path path = Paths.get(filePath);
        // 调用内置方法读取文件 需要传入文件路径和字符集
        List<String> strings = Files.readAllLines(path, charset);
        // 用于StringBuffer拼接内容
        StringBuffer sb = new StringBuffer();
        if (strings != null && strings.size() > 0) {
            for (String s : strings) {
                sb.append(s).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * 写文件调用类
     *
     * @param filePath 文件路径
     * @param bytes    数据
     * @param option   选项
     * @throws IOException IO异常
     */
    private static void writeFileWithOption(String filePath, byte[] bytes,
                                            OpenOption option) throws IOException {
        // 构造path
        Path path = Paths.get(filePath);
        // 调用内置方法读取文件 需要传入文件路径和字符集
        // 如果需要追加，则添加追加属性
        if (option != null) {
            Files.write(path, bytes, StandardOpenOption.APPEND);
            return;
        }
        Files.write(path, bytes);
    }


    /**
     * 写文件，默认覆盖
     *
     * @param filePath 文件路径
     * @param bytes    数据
     * @throws IOException IO异常
     */
    public static void writeFile(String filePath, byte[] bytes) throws IOException {
        writeFileWithOption(filePath, bytes, null);
    }


    /**
     * 追加写文件
     *
     * @param filePath 文件路径
     * @param bytes    数据
     * @throws IOException IO异常
     */
    public static void writeFileAppend(String filePath, byte[] bytes) throws IOException {
        writeFileWithOption(filePath, bytes, StandardOpenOption.APPEND);
    }


}
