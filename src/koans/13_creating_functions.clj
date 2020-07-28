(ns koans.13-creating-functions
  (:require [koan-engine.core :refer :all]))

(defn square [x] (* x x))

(meditations
  "One may know what they seek by knowing what they do not seek"
  (= [true false true] (let [not-a-symbol? (complement symbol?)]
                         (map not-a-symbol? [:a 'b "c"])))
  ;; only 'b is a symbol, but complement return the opposite

  "Praise and 'complement' may help you separate the wheat from the chaff"
  (= [:wheat "wheat" 'wheat]
       (let [not-nil? (complement nil?)]
         (filter not-nil? [nil :wheat nil "wheat" nil 'wheat nil])))
  ;; similar to previous, complement returns opposite of filter

  "Partial functions allow procrastination"
  (= 20 (let [multiply-by-5 (partial * 5)]
          (multiply-by-5 4)))
  ;; turns a variable into a fn w/ loose arguments, result becomes argument for next fn call (if any) (hundred-times 3 3) => 900

  "Don't forget: first things first"
  (= [:a :b :c :d]
       (let [ab-adder (partial concat [:a :b])]
         (ab-adder [:c :d])))
  ;; via partial, ab-adder becomes concat

  "Functions can join forces as one 'composed' function"
  (= 25 (let [inc-and-square (comp square inc)]
          (inc-and-square 4)))
  ;; output of fn => input for next fn
  ;; (defn f [x] (+ 11 (- x 90)))
  ;; (defn g [x] (* 2 x))
  ;; ((comp g f) 100) => (* 21 2) => 42

  "Have a go on a double dec-er"
  (= 8 (let [double-dec (comp dec dec)]
          (double-dec 10)))
  ;; dec(rement by 1)

  "Be careful about the order in which you mix your functions"
  (= 99 (let [square-and-dec (comp dec square)]
          (square-and-dec  10 )))
  ;; squares input, then decrements
  )
