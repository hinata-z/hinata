package test;
/**
 *
 -Xmx4000M -Xms4000M -Xmn600M-XX:PermSize=500M -XX:MaxPermSize=500M
 -Xss256K -XX:+DisableExplicitGC-XX:SurvivorRatio=1 -XX:+UseConcMarkSweepGC
 -XX:+UseParNewGC-XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection
 -XX:CMSFullGCsBeforeCompaction=0-XX:+CMSClassUnloadingEnabled
 -XX:LargePageSizeInBytes=128M-XX:+UseFastAccessorMethods
 -XX:+UseCMSInitiatingOccupancyOnly-XX:CMSInitiatingOccupancyFraction=80
 -XX:SoftRefLRUPolicyMSPerMB=0-XX:+PrintClassHistogram -XX:+PrintGCDetails
 -XX:+PrintGCTimeStamps-XX:+PrintHeapAtGC -Xloggc:log/gc.log


 -XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn10M
 */
public class JvmMemoryTest {

    private static final  int _1MB=1024*1024;

    public static void main(String[] args) {
        byte[] b1,b2,b3,b4;
        b1=new byte[1*_1MB];
        b2=new byte[1*_1MB];
        b3=new byte[1*_1MB];
//        b4=new byte[6*_1MB];
//        b3=new byte[6*_1MB];
    }

}
