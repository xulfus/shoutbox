(ns shoutbox.dada
   (:require [clojure.java.jdbc :as sql]))

(def db-uri "postgresql://janne:@localhost:5432/janne")

(defn db-initialized? []
  (-> (sql/query db-uri
                 ["select count(*) from information_schema.tables 
                   where table_name='shouts'"])
      first :count pos?))

(defn initialize-db []
  (sql/db-do-commands db-uri
                      (sql/create-table-ddl
                       :shouts
                       [:id :serial "PRIMARY KEY"]
                       [:shout :varchar "NOT NULL"]
                       [:created_at :timestamp
                        "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

(defn all-shouts []
  ; list all shouts from db
  [])

(defn create [content]
  ; insert shout
  nil)

