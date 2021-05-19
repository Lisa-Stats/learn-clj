(ns learn-clj.destructuring)

(comment
  (def body-data-one {:height 172 :weight 120 :bmi 21})

  (def body-data-two {:height 180 :weight 175 :bmi 22})

  (def body-data-three {:height 195 :weight 200 :bmi 22.6})

  (def body-data-four {:height 210 :weight 220 :bmi 24})

  (def body-datas [body-data-one
                   body-data-two
                   body-data-three
                   body-data-four])

  (defn avg-weight [bdatas]
    (float (/ (reduce + (map :weight bdatas))
        (count bdatas))))

  (avg-weight body-datas)
)

(comment

  (defn height-inch
    [{:as bdatas :keys [height]}]
    (assoc bdatas :height (/ height 2.54)))

  (height-inch body-data-two)

  (map height-inch body-datas)

  ;;these two are the same
  (filter
   (fn [bdata]
     (<= 200 (:weight bdata)))
   body-datas)

  (filter
   #(>= (:weight %) 200) body-datas)

;;these two are the same
  (filter
   #(and (<= (:bmi %) 23) (> (:weight %) 180)))

  (filter
   (fn [bdata]
     (and (>= 23 (:bmi bdata)) (< 180 (:weight bdata)))))


  ;;correct
  ;;but should be naming collection something different
  ;;like in average-bmi function
  ;;when it is a reduce, generally should give defn the
  ;;entire coll
  (defn average-map [bdata]
    (/ (reduce + (map :bmi bdata))
       (count bdata)))

  (average-map body-datas)

  ;;correct
  ;;good practice to name argument parameter something
  ;;different:1. it is coll and other is one body data
  ;;2. if same name, the local scope will map over
  ;;name in the general or defn scope
  ;;bdatas = coll, bdata = one piece of data
  (defn average-bmi [bdatas]
    (/ (reduce (fn [acc bdata]
                 (+ acc (:bmi bdata))) 0 bdatas)
       (count bdatas)))

  (average-bmi body-datas)
  )


  (comment

    (defn convert-to-kg
      [{:as bdata :keys [weight]}]
      (assoc bdata :weight (/ weight 2.2)))

    (convert-to-kg body-data-two)

    (map convert-to-kg body-datas)


    (+ (:weight body-data-one)
       (:weight body-data-two))
      )


  (comment
  ;;sequential destructuring
    (def my-vector [[1 2 3] [4 5]])

    (let [[p1 p2] my-vector
          [x1 y1] p1
          [x2 y2] p2]
      (println
       "line from (" x1 "," y1 ") to (" x2 ", " y2 ")"))

    (def colours ["blue" "red" "green" "yellow" "orange"])

    (let [[x y & zs] colours]
      (apply prn [x y zs]))

    (let [[item1 _ item2 _] colours]
      (println "good colours:" item1 "and" item2))

    (let [[x _ _ _ x2 :as all] colours]
      (println "the best colour is:" x)
      (println "the worst colour is:" x2)
      (println "these were taken from:" all)))


(comment
  ;;associative destructuring or key destructuring
  (def some-map [{:name "Lisa"
                  :age 29
                  :hometown "Toronto"}])

  (let [{:keys [] :as info} some-map]
    info)

  (let [{:keys [name age]} some-map]
    [name age])

  (defn sentence [{:keys [name age]}]
    (println (str "my name is "name))
    (println (str "my age is "age)))

  (sentence some-map)

  (filter #(< (:age %) 20) some-map)

  (defn gen-map [x y z]
    {:name x
     :age y
     :hometown z})

  (gen-map "Lisa" 29 "Toronto")

  (let [{:keys [name age hometown]}
        (gen-map "Vikki" 62 "Toronto")]
    (format "Hi my name is %s, I am %s and I live in %s "
            name age hometown))
  )

(comment
  (def some-map-two {:a 1 :b 2 :c 3})
  (def some-vec [1 2 3])

  (map (fn [[x y]]
         (if (> y 0)
           (vector x (* 2 y))
           [x y]))
       some-map-two)

  (map (fn [[x y]]
         (vector x (* 2 y)))
       some-map-two)
  ;;???
  ( (filter #(= 0 (mod %2 3))) some-map-two)
  ;;
  (filter (fn [[_x y]] (= 0 (mod y 3)))
          some-map-two)

  (defn gen-map-two [name age]
    {:name name
     :age age
     })
  (gen-map-two "lisa" 29)

  (let [{:keys [name age] }
        (gen-map-two "lisa" 29)]
    (str "Hi my name is "name" and I am "age" years old"))

  )

(comment
  ;;purely functional
  ;;where to destructure
  ;;- anytime you are binding a symbol to a value, instead
  ;; of using symbol can use any destructuring form
  ;;- wherever the docs say 'bindings'
  ;;- let, for, loop, doseq

  ;;associative or key destructuring
  (defn f [{:keys [a b c] :as info}]
    (prn a)
    (prn b)
    (prn c)
    info)

  (f {:a 1 :b 2 :c 3})

  (defn print-form-name [{:keys [first-name last-name]
                          :or {last-name "Doe"}
                          :as person}]
    (println person)
    (println (str first-name " " last-name)))

  (print-form-name {:first-name "Joe"
                    :last-name "Smith"})

  ;;keyword arguments
  ;;- want to be able to give arguments names, so can put
  ;;them in any order, do not want to rely on order esp
  ;;as list gets longer
  ;;- having to remember what order they go in and that they
  ;;are all optional, how to represent not passing it in
  (defn http-request [url & {:keys [request-method body headers callback]
                             :or   {request-method :get
                                    body nil
                                    headers {}
                                    callback (fn [& _])}}]
    :body)

  (http-request "http://lispcast.com" :request-method :post :body "1234" :headers {})

  ;;nested destructuring
  (def http-request-two {:body "ok"
                         :request-method :post
                         :headers {"content-type" "text/plain"}
                         :cookies {"session" {:expires #inst "2020-09-09"
                                              :value "session1"}}})

  ;;not destructured
  (defn print-session [request]
    (get-in request [:cookies "session" :value]))

  ;;destructured
  (defn print-session-two [{{{session :value} "session"} :cookies}]
    (println session))

  ;;destructuring examples
  ;;use destr. a lot when getting a vector back from a fn
  (for [[k v] (seq {:a  1 :b 2})]
    (str k v))

  (let [[_ first-name last-name] (re-find #"(\w*) (\w*)" "Eric Normand")]
    (println first-name last-name))

  (defn part [p? ls]
    [(filter p? ls)
     (remove p? ls)])
  (let [[evens odds] (part even? [1 2 3 4 5])]
    (println evens odds))

  (loop [ls [1 2 3 4]]
    (when-let [[f & rst] (seq ls)]
      (println f)
      (recur rst)))

  ;;times when using destr. isn't a good idea
  (def circle {:centre [0 0]
               :radius 5})

  ;;this fn is doing 2 things - calculating radius
  ;;           - pulling out radius from inside of a circle
  (defn area [{:keys [radius]}]
    (* 3.14 radius radius))

  (defn area-two [radius]
    (* 3.14 radius radius))


  ;;some other code
  (area {:radius 5})

  ;;better to destr. here
  (area-two (:radius circle))


  )
