(ns learn-clj.practice
  (:require [clojure.string :as str]))

(comment

  (filter #(and (even? %) (> % 3)) [1 2 3 4 5 6])
  (filter (apply every-pred
                 [even? #(> % 3)]) [1 2 3 4 5 6])

  (filter even? (map #(* % 3) [0 1 2 3 4 5]))

  (filter (fn [[_k v]]
            (even? v)) {:a 1 :b 2 :c 3})

  (filter #(> (:val %) 3) [{:a 1 :val 2}
                           {:d 3 :val 4}])

  (->> (range 10)
       (filter even?)
       (map #(* % %)))

  (take 10 (iterate (partial + 3) 0))

  (->> (range 0 100 3)
       (filter #(= 0 (mod % 4)))
       (take 5))

  (take 5 (filter #(= 0 (mod % 4))
                  (range 0 100 3)))

  (filter count odd?)

  )

(comment
  ;;clojure for the brave and true examples (minus reduce)
  ;;and ch. 3 questions
  (defn codger-comm
    [whipper]
    (str "get off my lawn " whipper "!"))

  (defn codger-comm-two
    [& whippers]
    (map codger-comm whippers))

  (codger-comm-two "p" "i")

  (defn announce-treasure
    [{lat :lat long :long}]
    (println (str "treasure lat: " lat))
    (println (str "treasure long: " long)))

  (defn announce-treasure-two
    [{:keys [lat long]}]
    (println (str "treasure lat: " lat))
    (println (str "treasure long: " long)))

  (announce-treasure {:lat 25 :long 21})

  (#(+ % 100) 10)

  ((fn [n] (+ 100 n)) 10)

  (def add-hundred
    (partial + 100))
  (add-hundred 10)

  (def words ["team" "meat" "mat" "mate" "eat" "tea"])
  (group-by set words)

  (defn decmaker [x]
    #(- % x))
  (def dec9 (decmaker 9))
  (dec9 10)

;;ch. 4 - seq function examples
  (def sum #(reduce + %))
  (def average #(/ (sum %) (count %)))

  (defn stats [numbers]
    (map #(% numbers) [sum count average]))
  (stats [1 2 3 4 5])

  ;;take-while, drop-while and some examples}
  (def food-journal
    [{:month 1 :day 5 :human 1.3}
     {:month 1 :day 7 :human 1.5}
     {:month 2 :day 8 :human 2}
     {:month 3 :day 10 :human 3}
     {:month 4 :day 12 :human 4.7}])

  (filter #(< (% :month) 4) food-journal)

  (drop-while #(< (% :month) 3) food-journal)
  ;;to return true or false
  (some #(> (:human %) 4) food-journal)
  ;;to return the value
  (some #(and (> (:human %) 4) %) food-journal)

  ;;infinite sequences
  ;;repeat
  (concat (take 3 (repeat "na")) ["Batman"])

  ;;repeatedly
  (take 3 (repeatedly (fn [] (rand-int 10))))

  ;;into
  (into [] '(1 2 3))
  (into [1 2] [3 4 8])
  (into {} [[:a 1] [:b 2]])

  ;;partial
  (def add-five (partial + 5))
  (add-five 3)

  ;;comp
  ((comp inc *) 1 2)
  (filter pos-int? [0 0 1 2 4])

  (def modez #(= 0 (mod % 3)))
  (def muls-modez
    (comp (partial map #(* % 2))
          (partial filter modez)))
  (muls-modez [1 2 3 4 5 6])
  (filter modez [1 2 3])

  (->> (range)
       (map #(* % %))
       (filter even?)
       (take 5)))

(comment
  ;;loop and recursion

  (loop [x 5
         int []]
    (if (> x 0)
      (recur (dec x) (conj int x))
      int))

  (loop [x 0
         int []]
    (if (< x 11)
      (recur (+ 2 x) (conj int x))
      int))

  (loop [x 1
         int []]
    (when (< x 12)
      (println x)
      (recur (inc x) (conj int x))))

  ((fn [n]
     (when (> n 0)
       (println "Hello World")
       (recur (dec n)))) 3)

  ((fn [n] (dotimes [_n n]
             (println "Hello World"))) 3))


(comment
  ;;practice
)
(def fav-colour #{:blue :green})

(defn same-fav-colour? [colour]
  (contains? fav-colour colour))

(same-fav-colour? :blue)

(merge-with + {:a 1 :b 2 :c 3} {:a 2 :c 3 :d 2})

(for [x [["a"] ["b"] ["c"]]
      l x]
  l)

(for [x [1 2 3]
      y [:a :b :c]]
  [x y])

(take 5 (map second
              (iterate
               (fn [[a b]] [b (+ a b)]) [1 1])))

(mapcat (partial repeat 2) [1 2 3])

(range 1 (+ 7 3))

((juxt take drop) 2 [1 2 3 4 5])

(take 5 (iterate (partial + 2) 0))

(comment

  (butlast (interleave [1 2 3] (repeat 0)))

  (defn *map [f ls]
    (if (empty? ls)
      ()
      (cons (f (first ls))
            (*map f (rest ls)))))

  (defn factor [num]
    (loop [n num
           acc 1]
      (if (> n 0)
        (recur (dec n) (* acc n))
        acc)))

  (factor 3)

  (into [] (zipmap [:a :b :c] (range)))

  (defn range* [n1 n2]
    (loop [n1 n1
           acc []]
      (if (< n1 n2)
        (recur (inc n1) (conj acc n1))
        acc)))

  (defn simple-loop [n]
    (loop [n n
           acc []]
      (if (> n 0)
        (recur (dec n) (conj acc n))
        acc)))

  (simple-loop 10)
  )

(comment
  (let [yes 1]
    (cond
      (< yes 1) "less than"
      (> yes 1) "greater than"
      :else "idk"))

  ;;g - c
  ;;c - g
  ;;t - a
  ;;a - u

  (str/replace "gcta" #"g|c|t|a" {"g" "c" "c" "g" "t" "a" "a" "u"})


  )
