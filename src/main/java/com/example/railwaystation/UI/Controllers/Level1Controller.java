package com.example.railwaystation.UI.Controllers;

import com.example.railwaystation.App;
import com.example.railwaystation.Generators.ConstUserGenerator;
import com.example.railwaystation.Generators.UserTypeGenerator;
import com.example.railwaystation.Generators.WiseGenerator;
import com.example.railwaystation.Helpers.CashRegisterManager;
import com.example.railwaystation.Helpers.ConsoleLogger;
import com.example.railwaystation.Helpers.Coordinates;
import com.example.railwaystation.Helpers.QueueManager;
import com.example.railwaystation.Interfaces.Generator;
import com.example.railwaystation.Models.UserFiles.PrototypeRegistry;
import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Models.UserFiles.UserType;
import com.example.railwaystation.MovingLogic.MoveAlgo.DoorPolygonResolver;
import com.example.railwaystation.Rendering.CanvasRendering;
import com.example.railwaystation.UI.CanvasManager.Camera2D;
import com.example.railwaystation.Game.AssetsReader;
import com.example.railwaystation.Game.GameLevel;
import com.example.railwaystation.Game.LevelReader;
import com.example.railwaystation.Game.Game;
import com.example.railwaystation.Game.GameLoop;
import com.example.railwaystation.UI.Handlers.ClickOnCanvasHandler;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Level1Controller implements Initializable {


    public Canvas canvasL1;
    private double mouseX;
    private double mouseY;

    private AnimationTimer _animationTimer;

    AtomicReference<Double>  amount_people;

    AnimationTimer _timer;
    public CanvasRendering ctx;
    public Camera2D _camera;
    public Canvas canvasinfo;

    public Spinner Amount;
    public GameLoop loop;
    public int maxCount = 40;

    private Collection<User> userCollection = new ArrayList<>();
    @FXML
    private void backToMain() throws IOException {
        App.setRoot("chooselevel_other");
    }



    @FXML
    public void startGame() throws IOException {
        canvasL1.setLayoutY(0.0);


        this.loop.restore();
        final int FPS = 4;
        double drawInterval = 1_000_000_000f / FPS;             // interval between frames in nanoseconds
        this._animationTimer = new AnimationTimer()
        {
            private long lastTime = 0;
            public void handle(long currentNanoTime)
            {
                if(currentNanoTime - lastTime  >= drawInterval) {
                    lastTime = currentNanoTime;
                    loop.animation_step();
                }
            }
        };
        this._animationTimer.start();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Game.Init();
        AssetsReader.loadAssets();
        Collection<GameLevel> gameLevels = LevelReader.loadLevels();
        if (gameLevels.isEmpty())
            return;
        GameLevel gl = gameLevels.stream().collect(Collectors.toList()).get(0);
        Game.setCurrentLevel(gl);
        System.out.println("!!!"+gl.getCell_size());
        canvasL1.setWidth(Game.get_currentLevelCell_Size() * gl.getWidth());
        canvasL1.setHeight(Game.get_currentLevelCell_Size() * gl.getHeight());



        _camera = new Camera2D(new Coordinates(0,0), 1);
        ctx = new CanvasRendering(canvasL1);
        ctx.set_camera(_camera);
        ctx.drawGrid(Arrays.stream(gl.get_matrix()).toList().size() * Game.get_currentLevelCell_Size(),
                Arrays.stream(gl.get_matrix()[0]).toList().size() *Game.get_currentLevelCell_Size(), Game.get_currentLevelCell_Size(), Game.get_currentLevelCell_Size());

        var doors = gl.get_doorsList();
        List<Generator> generators = new ArrayList<>();
        try {
            generators.add(new ConstUserGenerator(doors.get(0)));
            generators.add(new UserTypeGenerator(doors.get(1), UserType.ORDINARY));
            generators.add(new WiseGenerator(doors.get(2)));
            generators.add(new WiseGenerator(doors.get(3)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var prototypeManager = new PrototypeRegistry();

        QueueManager queueManager = new QueueManager(Game.get_currentLevel());
        CashRegisterManager crManager =  new CashRegisterManager(queueManager);
        ConsoleLogger logger = new ConsoleLogger();
        crManager.subscribe(logger);

        Game.setResolver(DoorPolygonResolver.calculate(gl));
        Game.currentLevel = gl;


        this.loop = new GameLoop(generators, ctx, _camera, queueManager, crManager);

        /* click handler to show queue info */
        canvasL1.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickOnCanvasHandler(canvasinfo,_camera));

        canvasL1.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        canvasL1.setOnMouseDragged(e -> {
            if(e.getButton() == MouseButton.PRIMARY){
                double deltaX = e.getX() - mouseX;
                double deltaY = e.getY() - mouseY;
                mouseX = e.getX();
                mouseY = e.getY();
                _camera.set_position(_camera.get_position().add(new Coordinates(-deltaX,-deltaY)));
            }
        });
        canvasL1.addEventHandler(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                double deltaY = scrollEvent.getDeltaY() * scrollEvent.getMultiplierY();
                double zoom_factor = 0.25;
                if(deltaY > 0)
                    _camera.set_zoom(_camera.get_zoom() + zoom_factor);
                else if(deltaY < 0)
                    _camera.set_zoom((Math.max(_camera.get_zoom() - zoom_factor, 0.5)));
            }
        });

        //Візуальна частина --------------------------------------------------------------------------------------------------------
        SpinnerValueFactory<Integer> valueFactoryAmount = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 10, 1);
        Amount.setValueFactory(valueFactoryAmount);
        //------------------------------------------------------------------------------------------------------------------------

        maxCount= (int) Amount.getValue();
        Game.setMaxUserCount(maxCount);

        Amount.valueProperty().addListener((ChangeListener<Integer>) (observableValue, oldValue, newValue) -> {
            maxCount = (newValue);
            Game.setMaxUserCount(maxCount);

        });
    }
}