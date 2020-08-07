package java3;

public class TestTest {
    @Test(priority = 1)
    public static void qw(){
        System.out.println(1);
    }
    @Test(priority = 5)
    public static void qwer(){
        System.out.println(2);
    }
    @BeforeSuit
    public static void qwert(){
        System.out.println(12);
    }
    @AfterSuit
    public static void qwerty(){
        System.out.println(15);
    }
}
