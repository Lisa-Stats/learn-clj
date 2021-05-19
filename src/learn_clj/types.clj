(ns learn-clj.types

  (:require
   [clojure.set :as set]))

 
;; some important key bindings
;; 1. send expr to REPL - C+M+c C+M+space (need to hold C+M
;; with space or expr will be automatically evaluated)
;; 2. load all code and namespace in REPL - C+M+c enter

;; we defined a variable named hello and gave it the value
;; which is a string "hello"
(def hello "hello")

;; we defined a variable named some-num and gave it a value
;; which is a number 42
(def some-num 42)

;; we defined a variable named some-dec and gave it the value
;; which is a decimal 3.5
(def some-dec 3.5)

;; we defined a variable named ls-one and put a bunch of
;; values inside of the same type
(def ls-one '(1 2 3))

;;we defined a variable named vec-one and put a bunch of
;;values inside of the same type
(def vec-one [1 2 3])

;;testing ordered collections
(comment
  (first ls-one)
  (last ls-one)
  (rest ls-one)
  (butlast ls-one)
  (nth ls-one 2)
  (conj ls-one 4)

  (first vec-one)
  (last vec-one)
  (rest vec-one)
  (butlast vec-one)
  (nth vec-one 3)
  (conj vec-one 4)
  )

;;we defined a variable named set-one and put a bunch of 
;;values inside of the same type
(def set-one #{1 2 3})

(def set-two #{3 4 5})
(comment
  (conj set-one 1)
  (set/union set-one set-two)
  (set/intersection set-one set-two)
  (set/difference set-one set-two)
  (set/difference set-two set-one)
  
  )

(defn hello-fn
  [] 
  (fn [] "hello"))

(comment
  (+ 1 2 )
  
  (+ 1 2 3 4 5 )
  
  (+ 1 (- 3 2 ))
  
  )

(comment
  some-dec

hello

(println "Hello World")

(println "Hola")

(println "What is this:" (+ some-num some-num))

(prn "What is this:" (+ some-num some-num))

(+ 7654 1234)

(/ (+ 7 (* 3 4) 5) 10)

(defn greet [name] (str "Hello " name))

(greet "students")

(- 25 3)

(- 30 5)

(println "hello")

(string? "yes")
(string? 1)


(compare "a" "x")

(compare 2 1)

(println "Hello World")

  )


(comment
  (def ls-two '(1 2 3 4 5 6))

  (peek ls-two)
  (get ls-two 2)
  (pop ls-two)
  (list? ls-two)
  (count ls-two)
  (empty? ls-two)
  (distinct? ls-two)
  (seq? ls-two)
  (cons ls-two 1 2)

  (def vec-two [7 8 9 10 11])

  (first vec-two)
  (last vec-two)
  (rest vec-two)
  (nth vec-two 2)
  (conj vec-two 5)
  (conj vec-two 6)
  (count vec-two)
  (peek vec-two)
  (get vec-two 1)

  (get vec-two 3)
  (rseq vec-two)
  (empty vec-two)
  (contains? vec-two 2)
  (disj vec-two 9)
  (assoc vec-two 2 7)
  (subvec vec-two 0 4)
  (pop vec-two)


  )  

(comment
  (def set-three #{1 3 5 7 2})
  
  (def set-four #{0 2 3 6 8})
  
  (conj set-three 6)
  (set/union set-three set-four)
  (set/intersection set-three set-four)
  (set/difference set-three set-four)
  (set/difference set-four set-three)
  (seq set-three)
  (contains? set-three 7)
  (disj set-three 5 7)
  
  )
  
(comment
  (def zmap (zipmap [:g :h] [10 11]))
  
  (contains? zmap :g)
  (get zmap :g)
  (find zmap :g)
  (keys zmap)
  (vals zmap)
  (assoc zmap :i 12 :j 13)
  
  )

;; we defined a variable called map-one and stored a bunch
;; of keys and values of the same type
(def map-one {:a 1 :b 2})
(def map-two {:a 1 :b 2 :c {:d 4}})
(comment
  (get map-one :a)
  (get map-one :c 5) ;;5 is the default value, if :c is not found
  ;;keywords can act like functions, can access maps this way
  ;;get is rarely used
  (:a map-one)
  (:d (:c map-two))
  (-> map-two :c :d)
  (-> [[1 2 5] 2 3]
      first
      last
      (- 1))
  (- (last (first [[1 2 5] 2 3])) 1)
  )

(comment
  (def map-three {:c 3 :d 5 :e 7 :f {:g 9 :h 11}})

  (:c map-three)
  (:h map-three)
  (:f map-three)
  (:g (:f map-three))
  (:h (:f map-three))
  (-> map-three :f :g)
  (-> map-three :f :h (+ 2))
  (+ (:h (:f map-three)) 2)

  (def map-four {:j 2 :k 4 :l {:m 6 :n {:o 8 :p 10}}})

  (:o (:n (:l map-four)))
  (-> map-four :l :n :o)
  (- (:k map-four) 2)
  (-> map-four :k (- 2))
  (:m (:l map-four))
  (keys map-four)
  (vals map-four)
  (get map-four :q 8)

  (-> [[1 2 3] 4 5 6]
      first
      first
      (+ 2))

  (-> [1 2 3 [4 5 [6 7 8]]]
      last
      rest
      last
      first
      (+ 2))

  (-> [[[6 7 8] 4 5] 1 2 3]
      rest
      first
      (- 1))
)

(comment
  (hello-fn)

  (greet "World")
  (greet "everybody")

  (defn greetings [people] (str "Hey " people))
  (greetings "you")

  (defn messenger
    ([] (messenger "Hello world"))
    ([msg] (println msg)))
  (messenger)
  (messenger "Hello!")

  (defn hello-again [greeting & who]
    (println greeting who))
  (hello-again "Hello" "world" "class")

  (def sumone (fn [x y] (+ x y)))
  (sumone 1 2)

  (take-nth 4 [1 2 3 4 5 6])
  
  (println "Hello world")
  
  (defn greetingstwo [place]
    (str "Hello " place))
  (greetingstwo "world") 
  
  )