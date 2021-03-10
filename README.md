# Hello World Java Action
A template to demonstrate how to build a Java action via [JEP 330: Launch Single-File Source-Code Programs](https://openjdk.java.net/jeps/330).

This action prints "Hello World" to the log or "Hello" + the name of a person to greet.

To learn how this action was built, see "[Creating a composite run steps action](https://docs.github.com/en/free-pro-team@latest/actions/creating-actions/creating-a-composite-run-steps-action)" in the GitHub Help documentation.

## Inputs

### `who-to-greet`

**Required** The name of the person to greet. Default `"World"`.

## Outputs

### `random-number`

> Returns the next pseudorandom, Gaussian ("normally") distributed
> `double` value with mean `0.0` and standard deviation `1.0` from
> this random number generator's sequence.

See [java.util.Random#nextGaussian()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Random.html#nextGaussian()) for details.

## Example usage

```yaml
jobs:
  build:
    strategy:
      matrix:
        os: [ ubuntu-latest, macos-latest, windows-latest ]
    runs-on: ${{ matrix.os }}
    steps:
      - uses: actions/checkout@v2
      - id: hello
        uses: sormuras/hello-world-java-action@v1
        with:
          who-to-greet: 'Mona the Octocat'
      - run: echo random-number ${{ steps.hello.outputs.random-number }}
        shell: bash
```

## `Action.java`

```java
import java.util.Random;

class Action {
  public static void main(String... args) {
    System.out.printf("Hello %s.%n", args.length == 0 ? "Java" : args[0]);
    var gaussian = new Random().nextGaussian();
    System.out.println("::set-output name=random-number::" + gaussian);
    System.out.println("Goodbye and have fun with: " + gaussian);
  }
}
```

## Feedback and Discussion

https://github.community/t/use-java-11-as-github-action-scripting-language/136755
