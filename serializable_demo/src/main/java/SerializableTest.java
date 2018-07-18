import java.io.*;

public class SerializableTest {

    public static void main(String[] args) throws Exception {
        serializePerson();
        Person person = deserializePerson();
        System.out.println(person.toString());

    }

    /**
     * 序列化
     */
    private static void serializePerson() throws IOException {
        Person person = new Person();
        person.setAge(11);
        person.setName("naruto");
        person.setSex(1);
        // ObjectOutputStream 对象输出流，将 person 对象存储到E盘的 person.txt 文件中，完成对 person 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("./person.txt")));
        oos.writeObject(person);
        System.out.println("Person 对象序列化成功！");
        oos.close();
    }

    /**
     * 反序列化
     */
    private static Person deserializePerson() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("Person 对象反序列化成功！");
        return person;
    }


}
