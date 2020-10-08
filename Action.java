import java.util.Random;

class Action {
  public static void main(String... args) {
    System.out.printf("Hello %s.%n", args.length == 0 ? "Java" : args[0]);
    System.out.printf("::set-output name=random-id::%d", new Random().nextInt(10));
    System.out.println("Goodbye");
  }
}
