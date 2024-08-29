import java.util.Random;

class Action {
  /** Entry-point for this Java action. */
  public static void main(String... args) {
    System.out.printf("Hello %s.%n", args.length == 0 ? "Java" : args[0]);
    var gaussian = new Random().nextGaussian();
    GitHub.setOutput("random-number", gaussian);
    System.out.println("Goodbye and have fun with: " + gaussian);
  }

  /** GitHub Actions helper. */
  static class GitHub {
    /** Sets an action's output parameter. */
    static void setOutput(String name, Object value) {
      if (name.isBlank() || value.toString().isBlank()) { // implicit null checks included
        throw new IllegalArgumentException("name or value are blank: " + name + "=" + value);
      }
      var githubOutput = System.getenv("GITHUB_OUTPUT");
      if (githubOutput == null) {
        throw new AssertionError("No such environment variable: GITHUB_OUTPUT");
      }
      try {
        var file = java.nio.file.Path.of(githubOutput);
        if (file.getParent() != null) java.nio.file.Files.createDirectories(file.getParent());
        var lines = (name + "=" + value).lines().toList();
        if (lines.size() != 1) {
          throw new UnsupportedOperationException("Multiline strings are no supported");
        }
        java.nio.file.Files.write(file, lines,
            java.nio.charset.StandardCharsets.UTF_8,
            java.nio.file.StandardOpenOption.CREATE,
            java.nio.file.StandardOpenOption.APPEND,
            java.nio.file.StandardOpenOption.WRITE);
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }  
    }
  }
}
