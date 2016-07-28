(ns tiny-maze.solver
  (:require [tiny-maze.maze-structure :as m-s]
            [tiny-maze.maze-exploration :as m-e])
  (:gen-class))

(declare solve-maze)

(defn advance-maze-tree
  [maze-tree]
  (let [latest-maze (last maze-tree)
        next-mazes (m-e/advance-maze latest-maze)]
    (if (= next-mazes latest-maze)
      (set [maze-tree])
      (set (mapv (fn [next-maze]
                   (conj maze-tree next-maze))
                 next-mazes)))))

(defn flatten-set-once
  [some-set]
  (set (apply concat some-set)))

(defn advance-maze-trees
  [maze-trees]
  (flatten-set-once (set (mapv advance-maze-tree maze-trees))))

(defn solve-maze-recursive
  [maze-trees]
  (let [next-maze-trees (advance-maze-trees maze-trees)]
    (if (= maze-trees next-maze-trees)
      maze-trees
      (recur next-maze-trees))))

(defn get-shortest-solution
  [solutions]
  (if (not-empty solutions)
    (let [solution-counts   (mapv count solutions)
          shortest-count    (apply min solution-counts)
          smallest-solution (first (filter (fn [solution]
                                             (= shortest-count
                                                (count solution)))
                                           solutions))]
      smallest-solution)
    []))

(defn get-best-maze-solution-path
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (let [started-maze (m-e/move-to-start-position maze)
        solved-mazes (solve-maze-recursive #{[started-maze]})
        shortest-solved-tree (get-shortest-solution solved-mazes)]
    shortest-solved-tree))

(defn solve-maze
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (let [shortest-solved-tree (get-best-maze-solution-path maze)
        best-solved-maze (last shortest-solved-tree)]
    best-solved-maze))

(defn -main [maze-string]
  (let [maze-structure (read-string maze-string)]
    (do
      (println "Solving Maze:\n")
      (doseq [row maze-structure]
        (println row))
      (let [solved-maze (solve-maze maze-structure)]
        (if solved-maze
          (do (println "\nSolution:\n")
              (doseq [row solved-maze]
                (println row)))
          (println "\nNo solution available"))))))

