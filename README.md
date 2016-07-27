# maze-solver

This is a square maze solver.

A maze is represented by a matrix like:

```clojure
[[:S 0 1]
 [1  0 1]
 [1  0 :E]]
```

- _S_ : start of the maze
- _E_ : end of the maze
- _1_ : This is a wall that you cannot pass through
- _0_ : A free space that you can move through.

The goal is the get to the end of the maze.  A solved maze will have a
_:x_ in the start, the path, and the end of the maze, like this:

```clojure
[[:x :x 1]
 [1  :x 1]
 [1  :x :x]]
```

## Instructions

- Clone or fork this repo

- Install Leiningen
    * ### Mac OSX
    
      ```shell
      brew install leiningen
      ```

    * ### UNIX
      
      ```shell
      wget https://raw.github.com/technomancy/leiningen/stable/bin/lein
      chmod +x lein
      mv lein ~/bin
      lein
      ```

- Run the tests with `lein test`
- Prepare the application

    ```shell
    lein uberjar
    ```

- Run the application with a maze

    ```shell
    java -jar target/tiny-maze-0.1.0-SNAPSHOT-standalone.jar "[[:S 0 1][1 0 1][1 0 :E]]"
    ```

    ```clojure
    ;; =>
    
    [[:x :x 1]
    [1  :x 1]
    [1  :x :x]]
    ```

## Note

This application aims to solve the tiny-maze challenge posed by Carin Meier in her
[wonderland-clojure-katas](https://github.com/gigasquid/wonderland-clojure-katas).

## License

Copyright © 2016 Charlie Hebert

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
