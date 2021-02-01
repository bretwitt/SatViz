package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.timepanel;

import com.google.common.eventbus.Subscribe;
import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.gui.guicomponents.GUIController;
import io.github.bretwitt.mathematics.units.UnitSystem;
import io.github.bretwitt.mathematics.units.base.time.TimeUnit;
import io.github.bretwitt.mathematics.units.metric.MetricTimeUnit;
import io.github.bretwitt.satviz.simulationstate.SimulationState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.text.DecimalFormat;
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

    private TimeUnit currentTimeUnit;

    private boolean isPaused;

    private ImageView play;

    private ImageView pause;

    private ImageView fastForward;

    private ImageView fastBackward;

    SimulationState state;

    public void initialize() {
        parent.setLayoutX(1580);
        parent.setLayoutY(100);
        setupIcons();
    }

    private void setupIcons() {

        Image clockImage = new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/clock.png")).toString()));
        Image pauseImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/pause.png")).toString());
        Image playImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/caret.png")).toString());
        Image fastForwardImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/chevron_right.png")).toString());
        Image fastBackwardImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ui/graphics/chevron_left.png")).toString());

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
        float time = getState().getSimulationTime();
        updateSimulationTime(getUnitSystem().getTimeUnit().fromTU(time));
    }

    private void updateSimulationTime(float time) {
        DecimalFormat df = new DecimalFormat("##");

        String seconds = df.format(time % 60);
        String minutes = df.format((time / 60) % 60);
        String hours = df.format((time / 3600) % 24);
        String days = df.format(Math.floor(time / 86400));
        simulationTime.setText(days + " d " + hours + " h " + minutes + " m " + seconds + " s ");
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

    private UnitSystem getUnitSystem() {
        return getState().getCurrentUnits();
    }

    private void setPauseResumeButtonPaused() {
        pauseResumeButton.setGraphic(play);
    }


    private void setPauseResumeButtonResumed() {
        pauseResumeButton.setGraphic(pause);
    }

}
