package com.mca.collection.compare;

/**
 * ClassName: Sunflower
 * Package: com.mca.collection.compare
 * Description: inner comparator
 *
 * @Author: yujie.qin
 * @Create: 2023/3/22 - 22:28
 * @version: v1.0
 */
public class Sunflower implements Comparable<Sunflower> {
    private String name;

    private Integer age;

    private Double height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public int compareTo(Sunflower sunflower) {
        // use age to compare
        return this.age - sunflower.getAge();
        // use name to compare
        // return this.name.compareTo(sunflower.getName());
        // use height to compare
        // return this.height.compareTo(sunflower.getHeight());
    }
}
