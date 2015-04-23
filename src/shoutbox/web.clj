(ns shoutbox.web
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.adapter.jetty :as ring]
            [ring.util.response :as resp]
            [hiccup.page :as page]
            [compojure.route :as route]
            [compojure.handler :as handler] 
            [shoutbox.dada :as db]
            [shoutbox.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]))

(defn shoutform []
  [:div {:id "shout-form"}
   (form/form-to [:post "/"]
                 (form/label "shout" "What do you want to SHOUT?")
                 (form/text-field "shout")
                 (form/submit-button "SHOUT!"))])

(defn display-shouts [shouts]
  [:div 
   (map
    (fn [shout] [:h2 {:class "shout"} (h (:shout shout))])
    shouts)])

(defn index [shouts]
  (layout/common "SHOUTER"
                 (shoutform)
                 [:div {:class "clear"}]
                 (display-shouts shouts)))

(defn create-shout [shout]
  (println "creating shout" shout)
  (when-not (str/blank? shout)
    (db/create shout))
  (resp/redirect "/"))

(defroutes routes
  (POST "/" [shout] (create-shout shout))
  (GET "/" [] (index (db/all-shouts)))  
  (route/resources "/"))

(def application (handler/site routes))

(defn -main []
  (when-not (db/db-initialized?)
    (db/initialize-db))
  (ring/run-jetty application {:port 8080 :join? false}))

