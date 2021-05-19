(ns learn-clj.map)

(comment
  ;; lines 5, 6 and 7 are identical
  (map inc [1 2 3 4 5])
  (map #(+ % 1) [1 2 3 4 5])
  (map (fn [x] (+ x 1))
       [1 2 3 4 5])

  (map dec [9 8 7 6 5])
  (map #(- % 1) [9 8 7 6 5])
  (map (fn [x] (- x 1))
       [9 8 7 6 5])

  (map :c [{:a 1 :c 2}
           {:a 3 :c 4}])

  (map first [[:a 1] [:b 2] [:c 3] [:d 4] [:e 5]])

  (def my-coll [{:a 75 :b 32}
                {:a 100 :b 64}
                {:a 125 :b 128}])

  (map first my-coll)
  (map :b my-coll)
  (map last my-coll)
  (reduce + (map #(:a %) my-coll))

  (map second my-coll)
  (map #(:b %) my-coll)

  (map + [1 2 3] [4 5 6])

  (map (fn [x]
         (str "Hello " x "!"))
       ["world" "everybody"])

  (map #(str "Hello " % "!") ["world" "everybody"])

  (map (fn [x] (vector (first x) (* 2 (second x))))
       {:a 1 :b 2 :c 3})

  (map #(vector (first %) (* 2 (second %)))
       {:a 1 :b 2 :c 3})

  (map {1 "one" 2 "two" 3 "three"} [1 2 3])

  (def matrix [[1 2 3 4]
               [1 2 3 4]
               [1 2 3 4]])

  (apply map * matrix)
  (map #(reduce * %) matrix)

  ;;example
  (def daysweek ["mon" "tues" "wed"])

  (def numbered [1 2 3])

  (defn unify
    [day number]
    {:day day
     :number number})

  (map unify daysweek numbered))