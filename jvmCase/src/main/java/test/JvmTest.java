package test;
/**
 * javap -v JvmTest.class a.txt

 */
public class JvmTest {

    final String a="is final";

    static int b=1;

    public int add(int a){
        return a+100;
    }

    public static void main(String[] args) {
        JvmTest jvmTest=new JvmTest();
        jvmTest.add(1);
        byte d=1;
        System.out.println();
    }
}
