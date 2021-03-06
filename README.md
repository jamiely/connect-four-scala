# Connect Four in Scala

## Intro

This was written for practice with Scala.

[![Build Status](https://travis-ci.org/jamiely/connect-four-scala.svg?branch=master)](https://travis-ci.org/jamiely/connect-four-scala) [![Coverage Status](https://coveralls.io/repos/github/jamiely/connect-four-scala/badge.svg?branch=master)](https://coveralls.io/github/jamiely/connect-four-scala?branch=master)

## Building

Use sbt to run.

```bash
brew install sbt
sbt compile
```

If you encounter an OutOfMemory exception when compiling, use the
following command to run *sbt*

```bash
env SBT_OPTS="-XX:MaxPermSize=256M" sbt
```

## Using

There is a simple console UI you can run using:

```bash
sbt run
```

## Spec Running

You can use *sbt* to run the tests.

```
sbt test
```

Specs use the specs scala library available here: http://code.google.com/p/specs/.

Specs are provided as part of the `connect_four.specs` package.

You should expect a result that looks like the following:

```
[info] BoardSpec
[info]
[info] A new board should
[info] + have a height of 6, width of 7, and length of 42
[info] + be empy
[info] + have empty spaces
[info] + have appropriate position indices
[info] + test for bounded indices
[info] + convert index objects to array indices
[info] + return a marker when updatePosition called
[info] + return true when move is valid
[info] + return appropriate marker after move
[info] + return appropriate marker after move 2
[info] + should return posIs true when appropriate
[info] + have available moves
[info] + detect when there are no moves remaining
[info]
[info]
[info] Total for specification BoardSpec
[info] Finished in 501 ms
[info] 13 examples, 30 expectations, 0 failure, 0 error
```

## Coverage

```bash
sbt coverage test coverageReport
# results in target/scala-2.11/scoverage-report/index.html
```

