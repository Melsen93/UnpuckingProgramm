public class Main {

    public static void main(String[] args) {
        String testOne = StringUnpack.transformation("3[xyz]4[xy]z");
        String testTwo = StringUnpack.transformation("2[3[x]y]");
        String testThree = StringUnpack.transformation("++++++");

        System.out.println(testOne);
        System.out.println(testTwo);
        System.out.println(testThree);
    }
}
