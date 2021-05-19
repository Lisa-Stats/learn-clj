(ns learn-clj.reduce)

(comment
  (reduce + [1 2 3 4 5])

  (reduce + 3 [1 2 3 4 5])

  (reductions + [1 2 3 4 5])

  (reduce * [1 2 3 4 5])

  (reductions * [1 2 3 4 5])

  (reduce + 3 #{1 2 4 5})

  (reductions (fn [x y] (+ x y)) [1 2 3 4 5])

  (reduce (fn [x y] (+ x y)) 3 [1 2 3 4 5])

  (conj [] 3 4)

  (reduce conj #{5} [1 2 3 4])

  (conj #{1 2 3 4} 5)

  ;;apply also works
  (/ (reduce + [1 2 3]) 3)

  (defn average [coll]
    (/ (reduce + coll) (count coll)))
  (average [1 2 2 3 3 7]))

(comment
  ;;does not work because cannot find last y, were giving it
  ;;two explicit arguments
  ;;expecting a map at (:weight y) and calling that but receiving
  ;;nothing b/c map is finished so it throws NullPointerException
  ;; 1 - 120 + 175
  ;; 2 - 295 + 200
  ;; 3 - 495 + 220
  ;; 4 - 715 + ???
  ;;(reduce
   ;;(fn [x y]
     ;;(+ (:weight x)
       ;; (:weight y))))

  ;;bad answer
  ;;map creates another list
  ;;reduce goes over that list
  ;;it is redundant to make another list
  ;;(reduce + (map :weight body-datas))

  ;;first arg is acc, continue to add onto that
  ;;only call :weight on one of the args now
  ;;correct answer
  ;;(reduce
   ;;(fn [acc bdata]
     ;;(+ acc (:weight bdata)))
   ;;0
   ;;body-datas)
)

(comment
  ;;clojure for the brave and true examples

  (reduce (fn [new-map [key val]]
            (assoc new-map key (inc val)))
          {}
          {:b 21 :c 25})

  (reduce (fn [new-map [key val]]
            (if (> val 3)
              (assoc new-map key val) new-map))
          {}
          {:a 2 :b 5})
  )

(comment
  ;;purely functional
  ;;should really be called assemble
  ;;it is the generic recursive function
  ;;implementing map using reduce
  (defn map* [f ls]
    (reduce (fn [res v]         ;; res-result list, v-some value
              ;; comes from the list
              (conj res (f v))) ;;returns result list,
            ;;with something added to
            ;;the end of it
            [] ls))
  ;;[] is the initial value, recursing over entire ls

  ;;implementing filter with reduce
  (defn filter* [pred ls]
    (reduce (fn [res v]
              (if (pred v)      ;; if predicate is truthy to v
                (conj res v) ;; add v
                res))        ;; otherwise return, res is accumulator
            [] ls))  ;;[] is the initial accumulator

  (filter* even? [1 2 3 4 5])

  ;;reduce-kv
  ;;regular reduce works on a sequence
  ;;this works on associative collections (maps, vectors)
  )
