import java.util.Random;

class Action {
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
        var file = Path.of(githubOutput);
        if (file.getParent() != null) Files.createDirectories(file.getParent());
        var lines = (name + "=" + value).lines().toList();
        if (lines.size() != 1) {
          throw new UnsupportedOperationException("Multiline strings are no supported");
        }
        debug("Write output %s to %s".formatted(lines, file));
        Files.write(file, lines, UTF_8, CREATE, APPEND, WRITE);
      } catch (IOException exception) {
        throw new UncheckedIOException(exception);
      }  
    }
  }
}
