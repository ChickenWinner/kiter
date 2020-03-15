package wang.redder.kiter.file;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
     * @return listener中的数据容器
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
    @SuppressWarnings("unchecked")
    public static List<Object> parseExcelToList(File file) {
        return (List<Object>) parseExcel(file, new DefaultExcelListener(), false);
    }


    /**
     * 解析excel并存储到list中
     *
     * @param filePath 文件路径
     * @return list集合
     */
    @SuppressWarnings("unchecked")
    public static List<Object> parseExcelToList(String filePath) {
        return (List<Object>) parseExcel(new File(filePath), new DefaultExcelListener(), false);
    }


    /**
     * 解析excel并转换为指定的对象集合
     * <p> 注意：目前要求Bean的属性 顺序和个数和excel中的相同!! </p>
     *
     * @param file 文件
     * @param clazz 映射对象
     * @param <T> 对象类型
     * @return 对象集合
     * @throws Exception 异常
     */
    public static <T> List<T> parseExcelToBeanList(File file, Class<T> clazz) throws Exception {
        // 返回的对象集合
        List<T> objects = new ArrayList<>();
        // 得到的数据集合,这里是两重list,第一重为行，第二重为每行的数据
        List<Object> datas = parseExcelToList(file);
        // 按顺序得到属性
        Field[] fields = clazz.getDeclaredFields();
        // 遍历数据，为对象属性设置值
        // 从1开始，跳过了第一行
        for(int rowNum = 1; rowNum < datas.size(); rowNum++) {
            // 创建对象 一行就是一个对象
            T t = clazz.newInstance();
            // 每一行的数据
            List<Object> rowData = (List<Object>) datas.get(rowNum);
            for(int colNum = 0; colNum < rowData.size(); colNum++) {
                // 为对象设置属性
                coverField(t, fields[colNum], String.valueOf(rowData.get(colNum)));
            }
            // 将对象添加进集合
            objects.add(t);
        }
        return objects;
    }


    /**
     * 解析excel并转换为指定的对象集合
     * <p> 注意：目前要求Bean的属性 顺序和个数和excel中的相同!! </p>
     *
     * @param filePath 文件路径
     * @param clazz 映射对象
     * @param <T> 对象类型
     * @return 对象集合
     * @throws Exception 异常
     */
    public static <T> List<T> parseExcelToBeanList(String filePath, Class<T> clazz) throws Exception {
        return parseExcelToBeanList(new File(filePath), clazz);
    }


    // 填充对象的属性值
    private  static void coverField(Object t, Field field, String data) throws IllegalAccessException {
        // 设置可访问
        field.setAccessible(true);
        // 类型转换
        if(field.getType() == String.class) {
            field.set(t, data);
        } else if(field.getType() == Long.class || field.getType() == long.class) {
            field.set(t, Long.valueOf(data));
        } else if(field.getType() == Float.class || field.getType() == float.class) {
            field.set(t, Float.valueOf(data));
        } else if(field.getType() == Integer.class || field.getType() == int.class) {
            field.set(t, Integer.valueOf(data));
        } else if(field.getType() == Short.class || field.getType() == short.class) {
            field.set(t, Short.valueOf(data));
        } else if(field.getType() == Double.class || field.getType() == double.class) {
            field.set(t, Double.valueOf(data));
        } else if(field.getType() == Character.class || field.getType() == char.class) {
            field.set(t, data.toCharArray().length > 0? data.toCharArray()[0]: "");
        } else if(field.getType() == Boolean.class || field.getType() == boolean.class) {
            field.set(t, Boolean.valueOf(data));
        }

    }


}
