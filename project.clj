(defproject blockchain-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [compojure "1.6.2"]
                 [ring/ring-core "1.8.2"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [ring/ring-json "0.5.0"]
                 [ring-cors "0.1.13"]
                 [org.clojure/data.json "2.9.1"]
                 [ring/ring-defaults "0.3.2"]
                 [org.clojure/data.json "1.0.0"]
                 [digest "1.4.10"]]
  :main ^:skip-aot blockchain-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})

