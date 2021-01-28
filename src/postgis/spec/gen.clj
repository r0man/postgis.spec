(ns postgis.spec.gen
  (:require [clojure.spec.gen.alpha :as gen])
  (:import [org.postgis LinearRing LineString MultiLineString MultiPoint MultiPolygon Point Polygon]))

(def coordinate
  (gen/double* {:NaN? false :infinite? false}))

(defn- such-that-consistent [gen]
  (gen/such-that #(.checkConsistency %) gen 1000))

(def point-2d
  "A generator that produces 2d Point instances."
  (->> (gen/tuple coordinate coordinate)
       (gen/fmap (fn [[x y]] (Point. x y)))
       (such-that-consistent)))

(def point-3d
  "A generator that produces 3d Point instances."
  (->> (gen/tuple coordinate coordinate coordinate)
       (gen/fmap (fn [[x y z]] (Point. x y z)))
       (such-that-consistent)))

(def point
  "A generator that produces 2d and 3d Point instances."
  (gen/one-of [point-2d point-3d]))

(def points
  "A generator that produces lists of 2d or 3d Point instances."
  (gen/one-of [(gen/vector point-2d) (gen/vector point-2d)]))

(def line-string
  "A generator that produces LineString instances."
  (->> (gen/such-that #(> (count %) 1) points)
       (gen/fmap #(LineString. (into-array Point %)))
       (such-that-consistent)))

(def linear-ring
  "A generator that produces LinearRing instances."
  (->> (gen/such-that #(> (count %) 2) points)
       (gen/fmap #(LinearRing. (into-array Point (conj % (first %)))))
       (such-that-consistent)))

(def polygon
  "A generator that produces 3d Polygon instances."
  (->> (gen/vector linear-ring 4 10)
       (gen/fmap #(Polygon. (into-array LinearRing %)))
       (such-that-consistent)))

(def multi-line-string
  "A generator that produces MultiLineString instances."
  (->> (gen/list line-string)
       (gen/fmap #(MultiLineString. (into-array LineString %)))
       (such-that-consistent)))

(def multi-point
  "A generator that produces MultiPoint instances."
  (->> (gen/fmap #(MultiPoint. (into-array Point %)) points)
       (such-that-consistent)))

(def multi-polygon
  "A generator that produces MultiPolygon instances."
  (->> (gen/list polygon)
       (gen/fmap #(MultiPolygon. (into-array Polygon %)))
       (such-that-consistent)))

(def gens
  "The generators by geometry type."
  {:line-string line-string
   :linear-ring linear-ring
   :multi-line-string multi-line-string
   :multi-point multi-point
   :multi-polygon multi-polygon
   :point point
   :polygon polygon})
