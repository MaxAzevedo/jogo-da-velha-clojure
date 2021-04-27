(ns jogo-da-velha.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn print-tabuleiro [tabuleiro]
      (println (nth tabuleiro 0) (nth tabuleiro 1) (nth tabuleiro 2))
      (println (nth tabuleiro 3) (nth tabuleiro 4) (nth tabuleiro 5))
      (println (nth tabuleiro 6) (nth tabuleiro 7) (nth tabuleiro 8)))

(defn possibilidades [tabuleiro]
      (concat (partition-all 3 tabuleiro)
              (list
                (take-nth 3 tabuleiro)
                (take-nth 3 (drop 1 tabuleiro))
                (take-nth 3 (drop 2 tabuleiro))
                (take-nth 4 tabuleiro)
                (take-nth 2 (drop-last 2 (drop 2 tabuleiro))))))

(defn fim-do-jogo [tabuleiro]
      ;(some true? (map #(apply = %) (possibilidades tabuleiro)))
      (some #(apply = %) (possibilidades tabuleiro)))

(defn -main
      ""
      [& args]
      (loop [tab [1 2 3 4 5 6 7 8 9]
             player 1]
            (print-tabuleiro tab)
            (if (not (fim-do-jogo tab))
              (do
                (println "Digite a casa que deseja jogar")
                (let [in (Integer. (read-line))]
                     (if (or (< in 1) (> in 9))
                       (do (println "Jogada inválida")
                           (recur tab player))
                       (cond
                         (= 1 player) (recur (assoc tab (- in 1) "X") 2)
                         (= 2 player) (recur (assoc tab (- in 1) "O") 1)))))
                (println (str "O jogador " (if (= player 1) 2 1) " ganhou. \nGame Over.")))))