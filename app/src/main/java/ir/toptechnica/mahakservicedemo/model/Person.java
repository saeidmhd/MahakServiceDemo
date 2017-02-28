package ir.toptechnica.mahakservicedemo.model;

import io.realm.RealmObject;

/**
 * Created by admin1 on 2/28/17.
 */

public class Person extends RealmObject {

    String name;
    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
