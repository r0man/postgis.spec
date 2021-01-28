(defproject postgis.spec "0.1.4-SNAPSHOT"
  :description "Clojure specs and generators for PostGIS types"
  :url "https://github.com/r0man/postgis.spec"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories [["releases" :clojars]]
  :dependencies [[org.clojure/clojure "1.10.2"]]
  :profiles {:dev {:dependencies [[org.clojure/test.check "1.1.0"]]
                   :plugins [[jonase/eastwood "0.3.13"]
                             [lein-difftest "2.0.0"]
                             [listora/whitespace-linter "0.1.0"]]}
             :provided {:dependencies [[net.postgis/postgis-jdbc "2.5.0"
                                        :exclusions [org.postgresql/postgresql]]]}}
  :aliases {"ci" ["do" ["clean"] ["difftest"] ["lint"]]
            "lint" ["do"  ["eastwood"]]})
