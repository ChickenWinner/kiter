package wang.redder.kiter.file;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.*;
import java.util.List;

/**
 * 文件解析器
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/2 13:10
 */
public class FileParser {


    /**
     * 解析excel方法，可自定义listener
     *
     * @param file     文件类型
     * @param listener 监听者，里面包含了解析完成的数据，以及可以在解析前后执行的方法
     *                 用户如果想自定义解析后的数据格式，或者现在解析前后做一些事情，可自定义一个ExcelListener类
     * @return
     */
    public static Object parseExcel(File file, DefaultExcelListener listener, boolean is2003Excel) {
        InputStream inputStream = null;
        ExcelReader excelReader = null;
        try {
            inputStream = new FileInputStream(file);
            // 如果是xls类型
            if (file.getName().endsWith(".xls")) {
                excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
                excelReader.read();
            } else {
                if (file.getName().endsWith(".xlsx")) {
                    if (is2003Excel) {
                        // 如果是2003excel
                        excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
                    } else {
                        // 不是2003版本的excel
                        excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
                    }
                    excelReader.read();
                } else {
                    // 不是excel文件
                    throw new RuntimeException("文件类型不是excel");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 返回数据
        return listener.getDatas();
    }

    /**
     * 解析excel并存储到list中
     *
     * @param file excel文件
     * @return list集合
     */
    public static List<Object> parseExcelToList(File file) {
        return (List<Object>) parseExcel(file, new DefaultExcelListener(), false);
    }

    /**
     * 解析excel并存储到list中
     *
     * @param filePath 文件路径
     * @return list集合
     */
    public static List<Object> parseExcelToList(String filePath) {
        return (List<Object>) parseExcel(new File(filePath), new DefaultExcelListener(), false);
    }

    


}
