package json;

import com.alibaba.fastjson.JSON;
import file.Person;
import org.junit.Test;
import wang.redder.kiter.json.JsonUtil;

/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/26 11:04
 */
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
