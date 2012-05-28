# Connect Four in Scala

## Intro

This was written for practice with Scala.

## Building

Eclipse Indigo was used to develop the source. You should be able to
compile with the following commands:

    scalac -classpath lib/specs_2.9.1-1.6.9.jar -d bin src/connect_four/*.scala src/connect_four/specs/*.scala

## Spec Running

Specs use the specs scala library available here: http://code.google.com/p/specs/.

Specs are provided as part of the `connect_four.specs` package. You can
run them with the following command:

    scala -cp lib/specs_2.9.1-1.6.9.jar:bin run connect_four.specs.BoardSpec

You should expect a result that looks like the following:

> Specification "BoardSpec"
>   + Board has size with height of 6 and width 7
>   + All of the board spaces should be empty
>   + should have appropriate position indices
> Total for specification "BoardSpec":
> Finished in 0 second, 118 ms
> 3 examples, 4 expectations, 0 failure, 0 error



