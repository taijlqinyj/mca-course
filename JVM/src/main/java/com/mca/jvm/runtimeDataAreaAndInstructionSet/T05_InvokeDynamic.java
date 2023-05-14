package com.mca.jvm.runtimeDataAreaAndInstructionSet;

/**
 * ClassName: T05_InvokeDynamic
 * Package: com.mca.jvm.runtimeDataAreaAndInstructionSet
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/13 - 23:55
 * @version: v1.0
 */
public class T05_InvokeDynamic {
    public static void main(String[] args) {


        I i = C::n;
        I i2 = C::n;
        I i3 = C::n;
        I i4 = () -> {
            C.n();
        };
        System.out.println(i.getClass());
        System.out.println(i2.getClass());
        System.out.println(i3.getClass());

        //for(;;) {I j = C::n;} //MethodArea < 1.8 Perm Space (FGC不回收)
    }

    @FunctionalInterface
    public interface I {
        void m();
    }

    public static class C {
        static void n() {
            System.out.println("hello");
        }
    }
}
