(ns postgis.spec.gen
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen])
  (:import [org.postgis LinearRing LineString MultiLineString MultiPoint MultiPolygon Point Polygon]))

(def point-2d
  "A generator that produces 2d Point instances."
  (gen/fmap (fn [[x y]] (Point. x y))
            (gen/tuple (s/gen double?)
                       (s/gen double?))))

(def point-3d
  "A generator that produces 3d Point instances."
  (gen/fmap (fn [[x y z]] (Point. x y z))
            (gen/tuple (s/gen double?)
                       (s/gen double?)
                       (s/gen double?))))

(def point
  "A generator that produces 2d and 3d Point instances."
  (gen/one-of [point-2d point-3d]))

(def line-string
  "A generator that produces LineString instances."
  (gen/fmap #(LineString. (into-array Point %))
            (gen/list point)))

(def linear-ring
  "A generator that produces LinearRing instances."
  (gen/fmap #(LinearRing. (into-array Point %))
            (gen/list point)))

(def polygon
  "A generator that produces 3d Polygon instances."
  (gen/fmap #(Polygon. (into-array LinearRing %))
            (gen/list linear-ring)))

(def multi-line-string
  "A generator that produces MultiLineString instances."
  (gen/fmap #(MultiLineString. (into-array LineString %))
            (gen/list line-string)))

(def multi-point
  "A generator that produces MultiPoint instances."
  (gen/fmap #(MultiPoint. (into-array Point %))
            (gen/list point)))

(def multi-polygon
  "A generator that produces MultiPolygon instances."
  (gen/fmap #(MultiPolygon. (into-array Polygon %))
            (gen/list polygon)))

(def gens
  "The generators by geometry type."
  {:line-string line-string
   :linear-ring linear-ring
   :multi-line-string multi-line-string
   :multi-point multi-point
   :multi-polygon multi-polygon
   :point point
   :polygon polygon})
