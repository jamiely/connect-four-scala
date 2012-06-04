# Connect Four in Scala

## Intro

This was written for practice with Scala.

## Building

Eclipse Indigo was used to develop the source. You should be able to
compile with the following commands:

    scalac -classpath lib/specs2_2.9.2-1.10.jar:lib/specs2-scalaz-core_2.9.2-6.0.1.jar:bin -d bin src/connect_four/*.scala src/connect_four/specs/*.scala

## Using

There is a simple console UI you can run using:

    scala -cp bin connect_four.UIConsole

## Spec Running

Specs use the specs scala library available here: http://code.google.com/p/specs/.

Specs are provided as part of the `connect_four.specs` package. You can
run them with the following command:

    for i in BoardSpec GameSpec GamePlaySpec GameOverSpec
      scala -cp lib/specs2_2.9.2-1.10.jar:lib/specs2-scalaz-core_2.9.2-6.0.1.jar:bin specs2.run connect_four.specs.$i
    

You should expect a result that looks like the following:

> Specification "BoardSpec"
>   + Board has size with height of 6 and width 7
>   + All of the board spaces should be empty
>   + should have appropriate position indices
> Total for specification "BoardSpec":
> Finished in 0 second, 118 ms
> 3 examples, 4 expectations, 0 failure, 0 error



