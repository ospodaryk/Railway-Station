package com.example.railwaystation.Game;

import com.example.railwaystation.Rendering.ResourceManagerCashRegister;
import com.example.railwaystation.Rendering.ResourceManagerDoor;
import com.example.railwaystation.Rendering.ResourceManagerQueueCell;
import com.example.railwaystation.Rendering.ResourceManagerUser;
import javafx.scene.image.Image;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.SplittableRandom;

public class AssetsReader {
    public static void loadAssets(){
        BufferedReader bw = null;
        URL assetFolder = AssetsReader.class.getClassLoader().getResource("com/example/railwaystation/assets");
        try {
            bw = new BufferedReader(new FileReader(assetFolder.getPath() + "/" + "assets.json"));
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load assets");
        }
        StringBuilder text = new StringBuilder();
        bw.lines().forEach(text::append);

        JSONObject assetObjJSON = null;
        try {
            assetObjJSON = (JSONObject) new JSONParser().parse(text.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            // TODO: fix relative path
            String base = "file:src/main/resources/com/example/railwaystation/assets";
            JSONObject usersJSON = (JSONObject) assetObjJSON.get("users");

            SplittableRandom random = new SplittableRandom();
            ResourceManagerUser.setSprite("ordinary", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_1.png"));
            ResourceManagerUser.setSprite("ordinary_1", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_1.png"));
            ResourceManagerUser.setSprite("ordinary_2", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_2.png"));
            ResourceManagerUser.setSprite("ordinary_3", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_3.png"));
            ResourceManagerUser.setSprite("ordinary_4", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_4.png"));
            ResourceManagerUser.setSprite("ordinary_5", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_5.png"));
            ResourceManagerUser.setSprite("ordinary_6", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_6.png"));
            ResourceManagerUser.setSprite("ordinary_7", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_7.png"));
            ResourceManagerUser.setSprite("ordinary_8", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_8.png"));
            ResourceManagerUser.setSprite("ordinary_9", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_9.png"));
            ResourceManagerUser.setSprite("ordinary_10", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_10.png"));
            ResourceManagerUser.setSprite("ordinary_11", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_11.png"));
            ResourceManagerUser.setSprite("ordinary_12", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_12.png"));
            ResourceManagerUser.setSprite("ordinary_13", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_13.png"));
            ResourceManagerUser.setSprite("ordinary_14", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_14.png"));
            ResourceManagerUser.setSprite("ordinary_15", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_15.png"));
            ResourceManagerUser.setSprite("ordinary_16", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_16.png"));
            ResourceManagerUser.setSprite("ordinary_17", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_17.png"));
            ResourceManagerUser.setSprite("ordinary_18", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_18.png"));
            ResourceManagerUser.setSprite("ordinary_19", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_19.png"));
            ResourceManagerUser.setSprite("ordinary_20", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_20.png"));
            ResourceManagerUser.setSprite("ordinary_21", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_21.png"));
            ResourceManagerUser.setSprite("ordinary_22", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_22.png"));
            ResourceManagerUser.setSprite("ordinary_23", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_23.png"));
            ResourceManagerUser.setSprite("ordinary_24", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_24.png"));
            ResourceManagerUser.setSprite("ordinary_25", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_25.png"));
            ResourceManagerUser.setSprite("ordinary_26", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_26.png"));
            ResourceManagerUser.setSprite("ordinary_27", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_27.png"));
            ResourceManagerUser.setSprite("ordinary_28", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_28.png"));
            ResourceManagerUser.setSprite("ordinary_29", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_29.png"));
            ResourceManagerUser.setSprite("ordinary_30", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_30.png"));
            ResourceManagerUser.setSprite("ordinary_31", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_31.png"));
            ResourceManagerUser.setSprite("ordinary_32", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_32.png"));
            ResourceManagerUser.setSprite("ordinary_33", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_33.png"));
            ResourceManagerUser.setSprite("ordinary_34", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_34.png"));
            ResourceManagerUser.setSprite("ordinary_35", new Image(base + "/" + (String) usersJSON.get("ordinary")+ "_35.png"));



            ResourceManagerUser.setSprite("disabled", new Image(base + "/" + (String) usersJSON.get("disabled")));
            ResourceManagerUser.setSprite("pregnant", new Image(base + "/" + (String) usersJSON.get("pregnant")));


            JSONObject doorsJSON = (JSONObject) assetObjJSON.get("door");
            ResourceManagerDoor.setSprite("north", new Image(base + "/" + (String) doorsJSON.get("north")));
            ResourceManagerDoor.setSprite("east", new Image(base + "/" + (String) doorsJSON.get("east")));
            ResourceManagerDoor.setSprite("south", new Image(base + "/" + (String) doorsJSON.get("south")));
            ResourceManagerDoor.setSprite("west", new Image(base + "/" + (String) doorsJSON.get("west")));

            JSONObject cashRegistersJSON = (JSONObject) assetObjJSON.get("cash_register");
            ResourceManagerCashRegister.setSprite("north", new Image(base + "/" + (String) cashRegistersJSON.get("north")));
            ResourceManagerCashRegister.setSprite("east", new Image(base + "/" + (String) cashRegistersJSON.get("east")));
            ResourceManagerCashRegister.setSprite("south", new Image(base + "/" + (String) cashRegistersJSON.get("south")));
            ResourceManagerCashRegister.setSprite("west", new Image(base + "/" + (String) cashRegistersJSON.get("west")));

            JSONObject queueCellsJSON = (JSONObject) assetObjJSON.get("queue_cells");
            ResourceManagerQueueCell.setSprite("0000", new Image(base + "/" + (String) queueCellsJSON.get("0000")));
            ResourceManagerQueueCell.setSprite("0001", new Image(base + "/" + (String) queueCellsJSON.get("0001")));
            ResourceManagerQueueCell.setSprite("0010", new Image(base + "/" + (String) queueCellsJSON.get("0010")));
            ResourceManagerQueueCell.setSprite("0011", new Image(base + "/" + (String) queueCellsJSON.get("0011")));
            ResourceManagerQueueCell.setSprite("0100", new Image(base + "/" + (String) queueCellsJSON.get("0100")));
            ResourceManagerQueueCell.setSprite("0101", new Image(base + "/" + (String) queueCellsJSON.get("0101")));
            ResourceManagerQueueCell.setSprite("0110", new Image(base + "/" + (String) queueCellsJSON.get("0110")));
            ResourceManagerQueueCell.setSprite("0111", new Image(base + "/" + (String) queueCellsJSON.get("0111")));
            ResourceManagerQueueCell.setSprite("1000", new Image(base + "/" + (String) queueCellsJSON.get("1000")));
            ResourceManagerQueueCell.setSprite("1001", new Image(base + "/" + (String) queueCellsJSON.get("1001")));
            ResourceManagerQueueCell.setSprite("1010", new Image(base + "/" + (String) queueCellsJSON.get("1010")));
            ResourceManagerQueueCell.setSprite("1011", new Image(base + "/" + (String) queueCellsJSON.get("1011")));
            ResourceManagerQueueCell.setSprite("1100", new Image(base + "/" + (String) queueCellsJSON.get("1100")));
            ResourceManagerQueueCell.setSprite("1101", new Image(base + "/" + (String) queueCellsJSON.get("1101")));
            ResourceManagerQueueCell.setSprite("1110", new Image(base + "/" + (String) queueCellsJSON.get("1110")));
            ResourceManagerQueueCell.setSprite("1111", new Image(base + "/" + (String) queueCellsJSON.get("1111")));
        }
        catch (Exception ex){
            System.out.println("Some icons does not exist or cannot be loaded");
            System.out.println(ex.getMessage());
        }
    }
}
