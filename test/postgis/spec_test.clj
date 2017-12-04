(ns postgis.spec-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :refer [deftest is]]
            [postgis.spec :as t]))

(deftest test-geometry
  (is (s/exercise ::t/geometry)))

(deftest test-geography
  (is (s/exercise ::t/geography)))

(deftest test-point
  (is (s/exercise ::t/point)))

(deftest test-line-string
  (is (s/exercise ::t/line-string)))

(deftest test-linear-ring
  (is (s/exercise ::t/linear-ring)))

(deftest test-multi-line-string
  (is (s/exercise ::t/multi-line-string)))

(deftest test-multi-point
  (is (s/exercise ::t/multi-point)))

(deftest test-multi-polygon
  (is (s/exercise ::t/multi-polygon)))

(deftest test-polygon
  (is (s/exercise ::t/polygon)))
