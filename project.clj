(defproject com.andrewmcveigh/cljs-time "0.1.0-SNAPSHOT"
  :description "A clj-time inspired date library for clojurescript."
  :url "https://github.com/andrewmcveigh/cljs-time"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:url "git@github.com:andrewmcveigh/cljs-time.git"}
  :dependencies [[com.cemerick/clojurescript.test "0.0.4"]]
  :plugins [[lein-cljsbuild "0.3.2"]
            [lein-marginalia "0.7.1"]]
  :hooks [leiningen.cljsbuild]
  :clojurescript? true
  :profiles
  {:dev {:cljsbuild
         {:builds
          [{:source-paths ["src"]
            :compiler {:output-to "target/main.js"
                       :optimizations :whitespace
                       :pretty-print true}}
           {:source-paths ["src" "test"]
            :incremental? true
            :notify-command
            ["phantomjs" "resources/runner.js" "target/unit-test.js"]
            :compiler {:output-to "target/unit-test.js"
                       :optimizations :whitespace
                       :pretty-print true}}]}}
   :prod
   {:cljsbuild
    {:builds [{:source-paths ["src" "test"]
               :compiler {:output-to "target/cljs/whitespace.js"
                          :optimizations :whitespace
                          :pretty-print true}}
              {:source-paths ["src" "test"]
               :compiler {:output-to "target/cljs/simple.js"
                          :optimizations :simple
                          :pretty-print true}}
              {:source-paths ["src" "test"]
               :compiler {:output-to "target/cljs/advanced.js"
                          :optimizations :advanced
                          :pretty-print true}}]
     :test-commands
     {"phantom-whitespace"
      ["phantomjs" "resources/runner.js" "target/cljs/whitespace.js"]
      "phantom-simple"
      ["phantomjs" "resources/runner.js" "target/cljs/simple.js"]
      "phantom-advanced"
      ["phantomjs" "resources/runner.js" "target/cljs/advanced.js"]}}}}
  :aliases {"test-all" ["with-profile" "prod" "test"]}
  :deploy-repositories
  [["snapshots" {:url "https://clojars.org/repo/" :creds :gpg}]
   ["releases" {:url "https://clojars.org/repo/" :creds :gpg}]])
