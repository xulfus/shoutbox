(defproject shoutbox "0.1.0-SNAPSHOT"
  :description "Shoutbox Application"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [postgresql "9.1-901.jdbc4"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [compojure "1.3.2"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot shoutbox.web
  :uberjar-name "shouter-standalone.jar"
  :profiles {:uberjar {:aot :all}})
