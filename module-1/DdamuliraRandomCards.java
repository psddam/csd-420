
/*
 * Name: Peter Ddamulira
 * Course: CSD420 - Advanced Java Programming
 * Assignment: Module 1.3 - Random Playing Cards
 * Date: October 24, 2026
 *
 * Purpose:
 * Display four randomly selected playing cards from
 * a standard deck of 52 cards using JavaFX.
 *
 * The Refresh Cards button uses a lambda expression
 * to select and display four new cards.
 *
 * How it works:
 * 1. Create a list of card numbers from 1 to 52.
 * 2. Shuffle the list to randomize the deck.
 * 3. Select four unique cards for display.
 * 4. Load the corresponding images from the cards folder.
 * 5. Use a lambda event handler to refresh the cards.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DdamuliraRandomCards extends Application {

    private final HBox cardContainer = new HBox(15);
    private List<Integer> previousCards = new ArrayList<>();

    @Override
    public void start(Stage stage) {

        // Display the application heading.
        Text heading = new Text("Random Playing Cards");
        heading.setFont(Font.font("Arial", 24));

        // Center the four playing cards horizontally.
        cardContainer.setAlignment(Pos.CENTER);

        // Create the refresh button.
        Button refreshButton = new Button("Refresh Cards");

        // Lambda expression handles button click events.
        refreshButton.setOnAction(event -> displayRandomCards());

        // Display four cards when the application opens.
        displayRandomCards();

        // Arrange the heading, cards, and button vertically.
        VBox root = new VBox(25);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                heading,
                cardContainer,
                refreshButton
        );

        // Create and display the application window.
        Scene scene = new Scene(root, 650, 350);

        stage.setTitle("CSD420 - Module 1.3 Random Cards");
        stage.setScene(scene);
        stage.show();
    }

    /*
     * Randomly selects four unique cards.
     * On refresh, the new selection must differ
     * from the previous selection.
     */
    private void displayRandomCards() {

        List<Integer> deck = new ArrayList<>();

        // Add all 52 cards to the deck.
        for (int card = 1; card <= 52; card++) {
            deck.add(card);
        }

        List<Integer> selectedCards;

        // Shuffle until a different set of cards is selected.
        do {
            Collections.shuffle(deck);
            selectedCards = new ArrayList<>(deck.subList(0, 4));
        } while (selectedCards.equals(previousCards));

        previousCards = new ArrayList<>(selectedCards);

        // Remove previously displayed images.
        cardContainer.getChildren().clear();

        // Load and display each selected card.
        for (int cardNumber : selectedCards) {

            File cardFile = new File(
                    "cards/" + cardNumber + ".png"
            );

            Image image = new Image(cardFile.toURI().toString());

            ImageView cardView = new ImageView(image);
            cardView.setFitHeight(180);
            cardView.setPreserveRatio(true);

            cardContainer.getChildren().add(cardView);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
