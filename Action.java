import java.util.Random;

class Action {
  public static void main(String... args) {
    System.out.printf("Hello %s.%n", args.length == 0 ? "Java" : args[0]);
    var gaussian = new Random().nextGaussian();
    System.out.println("::set-output name=random-number::" + gaussian);
    System.out.println("Goodbye and have fun with: " + gaussian);
  }
}
