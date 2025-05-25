/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d'aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d'adéquation
 * à un usage particulier et d'absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d'auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d'un contrat, d'un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d'autres éléments du logiciel.
 *
 * (c) 2022-2025 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm.calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * La classe CalculatorController permet de gérer les actions réalisées par
 * l'utilisateur sur l'interface de la calculatrice.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public class CalculatorController {

    /**
     * La mémoire de la calculatrice, qui contient la dernière valeur calculée.
     */
    @FXML
    private int memory = 0;

    /**
     * Le dernier opérateur arithmétique sur lequel l'utilisateur a appuyé.
     */
    @FXML
    private String operator;

    /**
     * Un booléen pour savoir s'il faut réinitialiser la calculatrice au
     * prochain appui sur un chiffre.
     */
    @FXML
    private boolean toClear = false;

    @FXML
    private TextField screen;  // ou Label selon votre interface FXML

    /**
     * Modifie le contenu affiché par l'écran de la calculatrice.
     *
     * @param content Le nouveau contenu à afficher.
     */
    @FXML
    private void setScreenContent(String content) {
        // TODO Vous devez implanter cette méthode.
        screen.setText(content);

    }

    /**
     * Donne le contenu affiché par l'écran de la calculatrice.
     *
     * @return Ce qui est actuellement affiché sur l'écran de la calculatrice.
     */
    @FXML
    private String getScreenContent() {
        // TODO Vous devez implanter cette méthode.
        return screen.getText();
    }

    /**
     * Exécute l'action correspondant à l'appui sur un chiffre.
     *
     * @param event La valeur du chiffre sur lequel l'utilisateur a appuyé.
     */

    @FXML
    private void pressDigit(ActionEvent event) {
        Button button = (Button) event.getSource();
        int value = Integer.parseInt((String) button.getUserData());

        if (toClear) {
            setScreenContent("");
            toClear = false;
        }
        setScreenContent(getScreenContent() + value);
    }


    /**
     * Exécute l'action correspondant à l'appui sur un opérateur.
     *
     * @param event Le symbole de l'opérateur sur lequel l'utilisateur a appuyé.
     */

    @FXML
    private void pressOperator(ActionEvent event) {
        Button button = (Button) event.getSource();
        String symbol = (String) button.getUserData();

        if (operator == null) {
            memory = Integer.parseInt(getScreenContent());
        } else {
            memory = computeResult();
        }

        operator = symbol;
        toClear = true;
    }


    /**
     * Calcule le résultat de l'opération saisie par l'utilisateur.
     *
     * @return Le résultat de l'opération.
     */

    @FXML
    private int computeResult() {
        // On récupère la dernière valeur saisie.
        int value = Integer.parseInt(getScreenContent());

        // On calcule le résultat en fonction de l'opérateur.
        int result = memory;
        if (operator != null) {
            result = switch (operator) {
                case "+" -> memory + value;
                case "-" -> memory - value;
                case "*" -> memory * value;
                case "/" -> memory / value;
                default -> throw new UnsupportedOperationException();
            };
        }

        // On affiche ensuite le résultat obtenu.
        setScreenContent(Integer.toString(result));
        return result;
    }

    @FXML
    private void clear() {
        setScreenContent("");
        memory = 0;
        operator = null;
        toClear = false;
    }

    @FXML
    private void pourcentage() {
        int value = Integer.parseInt(getScreenContent());
        setScreenContent(Integer.toString(value / 100));
    }

    @FXML
    private void negatif() {
        int value = Integer.parseInt(getScreenContent());
        setScreenContent(Integer.toString(-value));
    }

    @FXML
    private void factorielle(){
        int value = Integer.parseInt(getScreenContent());
        int result = 1;
        for(int i = 1; i <= value; i++){
            result *= i;
        }
        setScreenContent(Integer.toString(result));
    }

    private void divisionEuclidienne(){
        int value = Integer.parseInt(getScreenContent());
        int result = memory / value;
        setScreenContent(Integer.toString(result));
    }

    private void exponentielle(){
        int value = Integer.parseInt(getScreenContent());
        int result = 1;
        for(int i = 0; i < value; i++){
            result *= memory;
        }
        setScreenContent(Integer.toString(result));

    }

}
