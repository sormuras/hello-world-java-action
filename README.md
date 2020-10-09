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
> {@code double} value with mean {@code 0.0} and standard
> deviation {@code 1.0} from this random number generator's sequence.

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
      - id: foo
        uses: sormuras/hello-world-java-action@v1
        with:
          who-to-greet: 'Mona the Octocat'
      - run: echo random-number ${{ steps.foo.outputs.random-number }}
        shell: bash
```
## Feedback and Discussion

https://github.community/t/use-java-11-as-github-action-scripting-language/136755
