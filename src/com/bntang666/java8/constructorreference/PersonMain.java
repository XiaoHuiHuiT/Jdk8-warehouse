package com.bntang666.java8.constructorreference;

/**
 * @author BNTang
 */
public class PersonMain {
    public static void main(String[] args) {
        usePersonBuilder(Person::new);
    }

    private static void usePersonBuilder(PersonBuildInterface personBuildInterface) {
        Person bnTangPerson = personBuildInterface.build("BNTang", 23);
        System.out.println(bnTangPerson.getName() + "," + bnTangPerson.getAge());
    }
}