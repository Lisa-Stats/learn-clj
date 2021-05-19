(ns learn-clj.filter)

(comment

  (filter odd? (range 10))

  (filter (fn [x]
            (= (count x) 1))
          ["a" "aa" "Baa" "A"])

  (filter #(= (count %) 1)
          ["a" "aa" "aaa" "a" "b"])

  (filter #(> (second %) 10)
          {:a 2
           :b 12
           :c 16
           :d -3})

  (filter (comp #{3 5} last)
          {:a 1 :b 2 :c 3 :d 4 :e 5})

  (filter #{0 1 2 3} #{2 3 4})

  (def entries [{:month 1 :val 12 :s1 true :s2 false}
                {:month 2 :val 5 :s1 false :s2 false}
                {:month 3 :val 3 :s1 false :s2 true}])

  (filter #(:s2 %) entries)

  (filter #(and (:s2 %) (> (:val %) 4)) entries)

  (def ps [{:name "fluffy" :type :cat}
           {:name "sparky" :type :dog}
           {:name "spot" :type :dog}])

  (def qs [{:name "fluffy" :type :cat}
           {:name "sparkyq" :type :dog}
           {:name "spotq" :type :dog}])

  (defn find-dogs [pets]
    (filter #(= :dog (:type %)) pets))

  (find-dogs qs)

  (defn multiple-of-three? [n]
    (= 0 (mod n 3)))

  (multiple-of-three? 8)

  (filter multiple-of-three? (range 20))

  (defn multiple-of-five? [n]
    (= 0 (mod n 5)))

  (multiple-of-five? 5)

  (filter multiple-of-five? (range 21))

  (defn mult-of-three-or-five? [n]
    (and (multiple-of-three? n)
         (multiple-of-five? n)))

  (mult-of-three-or-five? 3)

  (filter mult-of-three-or-five? (range 101))

  (defn multthreefive [n]
    (or (multiple-of-three? n)
        (multiple-of-five? n)))

  (multthreefive 6)

  (filter
   (apply every-pred [even? #(> % 3)])
   [1 2 3 4 5]))
;; => nil



(comment
  (dotimes [_i 3]
    (println "Hello World"))

  (if (even? 3) "even" "odd")

  (let [x 2]
    (cond
      (< x 2) "x is less than 2"
      (< x 10) "x is less than 10"
      :else "x is equal to or greater than 10")))


(comment
  (if (multiple-of-three? 3) "fizz" "")

  (defn fizzbuzzone [x]
    (cond
      (mult-of-three-or-five? x) "fizzbuzz"
      (= 0 (mod x 3)) "fizz"
      (= 0 (mod x 5)) "buzz")
    :else x)
  (fizzbuzzone 11)

  (defn fizzbuzztwo [x]
    (cond
      (mult-of-three-or-five? x) "fizzbuzz"
      (multiple-of-three? x) "fizz"
      (multiple-of-five? x) "buzz"
      :else x))
  (fizzbuzztwo 15)

  ;;function with collection
  (defn fizzbuzzthree [coll]
    (map fizzbuzztwo coll))

  (fizzbuzzthree (range 16)))


(comment
  (filter multiple-of-three? [1 2 3 4 5 6])

  (map multiple-of-three? [1 2 3 4 5 6])

  (defn eventwo [n]
    (= 0 (mod n 2)))

  (filter eventwo (range 11))

  (def my-vector [1 2 3 4 5])

  (filter odd? my-vector))
