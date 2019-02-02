(ns demo.frontend)
               
(defn init []
  (let [dump-el (js/document.getElementById "dump")]
    (js/console.log "init" dump-el)
    (-> (js/fetch "/.netlify/functions/api")
        (.then (fn [res]
                 (.text res)))
        (.then (fn [text]
                 (set! (.-innerHTML dump-el) text))))))