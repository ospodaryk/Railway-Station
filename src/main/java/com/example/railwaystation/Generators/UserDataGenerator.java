package com.example.railwaystation.Generators;

import com.example.railwaystation.Models.TicketFIles.Ticket;
import com.example.railwaystation.Models.TicketFIles.TicketType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDataGenerator {
    private static UserDataGenerator userDataGenerator;
    public static UserDataGenerator getInstance() throws IOException {
        if(userDataGenerator == null){
            userDataGenerator = new UserDataGenerator();
        }
        return userDataGenerator;
    }

    private static SplittableRandom  randomizer;

    private static List<String> randomFirstNameList;
    private List<String> randomCityList;
    private static List<String> randomLastNameList;

    private UserDataGenerator() throws IOException {
        randomizer = new SplittableRandom();
        File fileFirstName = new File("src/main/resources/com/example/railwaystation/generatedData/first_name.txt");
        File fileLastName = new File("src/main/resources/com/example/railwaystation/generatedData/last_name.txt");
        File fileCities = new File("src/main/resources/com/example/railwaystation/generatedData/city.txt");
        try (Stream<String> lines = Files.lines(fileFirstName.toPath())){
            randomFirstNameList = lines.collect(Collectors.toList());
        }
        try (Stream<String> lines = Files.lines(fileLastName.toPath())) {
            randomLastNameList = lines.collect(Collectors.toList());
        }
        try (Stream<String> lines = Files.lines(fileCities.toPath())) {
            randomCityList = lines.collect(Collectors.toList());
        }
    }
    public  String generatePhoneNumber(){
        return "+380" + randomizer.nextInt(999999999);
    }
    public String generatePassportId(){
        return String.valueOf(randomizer.nextInt(999999999));
    }
    public String generateName(){
        return randomFirstNameList.get(randomizer.nextInt(randomFirstNameList.size()));
    }
    public String generateLastName(){
        return randomLastNameList.get(randomizer.nextInt(randomLastNameList.size()));
    }

    public int generateAge(){
        return randomizer.nextInt(18,61);
    }
    public List<Ticket> generateTickets(){
        var tickets = new ArrayList<Ticket>();
        for(int i = randomizer.nextInt(1,5); i > 0; i--){
            tickets.add(new Ticket(TicketType.values()[randomizer.nextInt(TicketType.values().length)],randomizer.nextInt(100,1000),randomCityList.get(randomizer.nextInt(randomCityList.size())),randomDate(LocalDate.now(),12)));
        }
        return tickets;
    }
    private LocalDate randomDate(LocalDate start, int months) {
        LocalDate end = start.plusMonths(months);
        int days = (int) ChronoUnit.DAYS.between(start, end);
        return start.plusDays(randomizer.nextInt(days + 1));
    }
}