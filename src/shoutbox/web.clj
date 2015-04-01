(ns shoutbox.web
  (:require [compojure.core :refer [defroutes GET]]
            [ring.adapter.jetty :as ring]
            [hiccup.page :as page]
            [shoutbox.dada :as db]))

(declare home)

(defroutes routes
  (GET "/" [] (home)))

(defn home []
  (page/html5
    [:head
      [:title "Shoutbox"]]
    [:body
      [:div {:id "content"} "Hello Shouters"]]))

(defn -main []
  (when-not (db/db-initialized?)
    (db/initialize-db))
  (ring/run-jetty #'routes {:port 8080 :join? false}))

