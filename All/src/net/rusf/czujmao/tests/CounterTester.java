package net.rusf.czujmao.tests;

public class CounterTester {
    private static void test(ICounter c) {
        System.out.println(c.getClass().getName());
        long time = System.currentTimeMillis();
        for (int i=0; i< 1000000; i++){
            c.addWord("yyy").addWord("yyy").addWord("xxx");
            c.addWord("yyy").addWord("xxx").addWord("zzz");
            c.addWord("zzz").addWord("yyy").addWord("bbb");
            c.addWord("bbb").addWord("bbb").addWord("bbb");
            c.addWord("kkk").addWord("kkk").addWord("kkk");
            c.addWord("yy").addWord("yy").addWord("xx");
            c.addWord("yy").addWord("xx").addWord("zz");
            c.addWord("zz").addWord("yy").addWord("bb");
            c.addWord("bb").addWord("bb").addWord("bb");
            c.addWord("kk").addWord("kk").addWord("kk");
        }
        System.out.println(c.byCount());
        System.out.println("Time:" + (System.currentTimeMillis() - time));
    }

    public static void main(String[] args) {
        test(new SuperCounter());
    }
}