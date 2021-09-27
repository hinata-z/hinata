package com.hinata.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 *cas：原子操作
 *    利用现代处理器 （内存地址v,旧值，新值）
 */
public class CasTools {
    static AtomicReference<User> atomicReference;

    public static void main(String[] args) {
        User user=new User("aa",11);
        atomicReference=new AtomicReference<>(user);
        user.setAge(1);
        atomicReference.compareAndSet(user,user);
        System.out.println(atomicReference.get());
    }
    static class User{

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String name;
        int age;

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
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
