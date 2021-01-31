package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.timepanel;

import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.engine.gui.guicomponents.GUIController;
import io.github.bretwitt.satviz.simulationstate.SimulationState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class TimeToolbarController extends GUIController {

    @FXML
    public Button pauseResumeButton;

    @FXML
    public AnchorPane parent;

    @FXML
    public TitledPane timeTitledPane;

    @FXML
    public TextField simulationTime;

    @FXML
    private Button fastForwardButton;

    @FXML
    private Button fastBackwardButton;

    private boolean isPaused;

    private ImageView play;

    private ImageView pause;

    private ImageView fastForward;

    private ImageView fastBackward;


    public void initialize() {
        parent.setLayoutX(1580);
        parent.setLayoutY(100);
        setupIcons();
    }

    private void setupIcons() {

        Image clockImage = new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getClassLoader().getResource("interface/graphics/clock.png")).toString()));
        Image pauseImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("interface/graphics/pause.png")).toString());
        Image playImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("interface/graphics/caret.png")).toString());
        Image fastForwardImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("interface/graphics/chevron_right.png")).toString());
        Image fastBackwardImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("interface/graphics/chevron_left.png")).toString());

        play = new ImageView(playImage);
        play.setFitHeight(15);
        play.setFitWidth(15);

        pause = new ImageView(pauseImage);
        pause.setFitHeight(15);
        pause.setFitWidth(15);

        fastForward = new ImageView(fastForwardImage);
        fastForward.setFitHeight(15);
        fastForward.setFitWidth(15);

        fastBackward = new ImageView(fastBackwardImage);
        fastBackward.setFitHeight(15);
        fastBackward.setFitWidth(15);

        ImageView clock = new ImageView(clockImage);
        clock.setFitHeight(15);
        clock.setFitWidth(15);

        pauseResumeButton.setGraphic(play);
        fastForwardButton.setGraphic(fastForward);
        fastBackwardButton.setGraphic(fastBackward);
        timeTitledPane.setGraphic(clock);
    }

    @Override
    public void update(float tpf) {
        float time = ((SimulationState)getSatViz().getCurrentState()).getSimulationTime();
        updateSimulationTime(time);
    }

    private void updateSimulationTime(float time) {
        simulationTime.setText(time + " TU");
    }

    public void pauseResumeClicked() {
        isPaused = !isPaused;
        if(isPaused) {
            setPauseResumeButtonPaused();
        } else  {
            setPauseResumeButtonResumed();
        }
        JavaFxUI.getInstance().runInJavaFxThread(() -> getGuiEventBus().post(new OnSimulationPauseGUIEvent(new SimulationPauseGUIEventData(isPaused))));
    }

    private void setPauseResumeButtonPaused() {
        pauseResumeButton.setGraphic(play);
    }


    private void setPauseResumeButtonResumed() {
        pauseResumeButton.setGraphic(pause);
    }

}
