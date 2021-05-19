(ns learn-clj.functions)

(comment
  (defn add
    [x & xs]
    (apply + x xs))
  (add 1 2 3)

  (defn multiply-two
    [x & xs]
    (apply * x xs))
  (multiply-two 3 3 5)

;; anonymous functions
  (def add-var
    (fn [x y]
      (+ x y)))

  (def subtract-var
    (fn [x y]
      (- x y)))

  (def multiply-var
    #(* %1 %2))

  (defn nil-return
    [x y]
    [(+ x y) nil]))

  (comment
    (add-var 1 2)

    (subtract-var 5 3)

    (multiply-var 5 10)

    (nil-return 8 8)

    (add 2 2 3 4 5)

    (defn add-two
      [x y]
      (+ x y))
    (add-two 2 1)

    (def subtract-anon
      #(- 2 %))
    (subtract-anon 1)


  ;; these three are the same
    (def add-three
      #(+ % 3))
    (add-three 1)

    (def add-threetwo (fn [x] (+ x 3)))
    (add-threetwo 1)

    (defn add-threethree [x] (+ x 3))
    (add-threethree 1)

  ;; these three are the same
    (defn subtract-two [x y]
      (- x y))
    (subtract-two 2 1)

    (def subtract-three
      (fn [x y] (- x y)))
    (subtract-three 2 1)

    (def subtract-four
      #(- %1 %2))
    (subtract-four 2 1)

  ;; these three are the same
    (defn printwords [x & ys] (println x ys))
    (printwords "how" "are" "you" "today?")

    (def printwordstwo (fn [x & ys] (println x ys)))
    (printwordstwo "see" "you" "later")

    (def printwordsthree
      #(println % %&))
    (printwordsthree "hello" "how" "are" "you"))

(comment
  (def ls-two '(1 2 3 4 5))
  (def vector-two [1 3 5 7])

  (apply max ls-two)
  (apply min vector-two)
  (reverse vector-two)
  (last ls-two)
  (butlast vector-two)
  (count vector-two)
  (apply + vector-two)
  (apply + [1 2 3])

  (/ (reduce + vector-two) (count vector-two))


  (def map-three {:a nil :b 2})
  (nil? :b))

(comment
  (defn greet []
    (println "Hello"))

  (def greety (fn [] (println "Hello")))

  (def greet-two
    #(println "Hello"))

  (defn greeting []
    (println "Hello, world!"))

  (defn greeting-one [x]
    (str "Hello, " x))

  (defn do-nothing [x]
    (identity x))

  (do-nothing "yes")

  (defn greeting-two [x y]
    (str x ", " y "!"))

  (defn always-thing [_x]
    (num 100))

  (always-thing 10)

  ;;?
  (defn triplicate [f]
    (repeatedly 3 f))

  (triplicate greeting-one))