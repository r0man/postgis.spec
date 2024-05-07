(ns postgis.spec
  (:require [clojure.spec.alpha :as s]
            [postgis.spec.gen :as gen])
  (:import [net.postgis.jdbc.geometry LinearRing LineString MultiLineString
            MultiPoint MultiPolygon Point Polygon]))

(s/def ::line-string
  (s/with-gen #(instance? LineString %)
    (constantly gen/line-string)))

(s/def ::linear-ring
  (s/with-gen #(instance? LinearRing %)
    (constantly gen/linear-ring)))

(s/def ::multi-line-string
  (s/with-gen #(instance? MultiLineString %)
    (constantly gen/multi-line-string)))

(s/def ::multi-point
  (s/with-gen #(instance? MultiPoint %)
    (constantly gen/multi-point)))

(s/def ::multi-polygon
  (s/with-gen #(instance? MultiPolygon %)
    (constantly gen/multi-polygon)))

(s/def ::polygon
  (s/with-gen #(instance? Polygon %)
    (constantly gen/polygon)))

(s/def ::point
  (s/with-gen #(instance? Point %)
    (constantly gen/point)))

(s/def ::geometry
  (s/or :line-string ::line-string
        :linear-ring ::linear-ring
        :multi-line-string ::multi-line-string
        :multi-point ::multi-point
        :multi-polygon ::multi-polygon
        :point ::point
        :polygon ::polygon))

(s/def ::geography ::geometry)
