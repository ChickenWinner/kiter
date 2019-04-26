package file;

/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/20 19:39
 */
public class Person {

    private String name = "林夕";

    private int age = 10;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
