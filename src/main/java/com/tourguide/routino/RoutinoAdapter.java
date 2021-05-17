package com.tourguide.routino;

import com.tourguide.containers.Constants;
import com.tourguide.containers.algorithm.Route;
import com.tourguide.containers.enums.TravelType;
import com.tourguide.containers.general.Place;
import com.tourguide.exceptions.RouteCannotBeCreatedException;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class RoutinoAdapter {
    private static String readFile(){
        try
        {
            File file=new File("shortest.txt");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            StringBuilder sb=new StringBuilder();
            String line;
            while((line=br.readLine())!=null)
            {
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            return sb.toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static void buildFinalPath(ArrayList<Place> places){
        ProcessBuilder processBuilder = new ProcessBuilder();
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < places.size();i++) {
            stringBuilder.append(" --lat").append(i+1).append("=").append(places.get(i).getPoint().getLatitude())
                    .append(" --lon").append(i+1).append("=").append(places.get(i).getPoint().getLongitude());
        }
        String command = "routino-router --dir=src/main/resources/map_data " +stringBuilder.toString() +" --transport=foot\n";
        processBuilder.command("bash", "-c", command);
        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                String[] lines = Objects.requireNonNull(RoutinoAdapter.readFile()).split("\n");
            }
        } catch (Exception e){

        }
    }
    public static Route buildRoute(Place p1, Place p2, TravelType type) throws RouteCannotBeCreatedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String literal_type;
        switch (type){
            case FOOT:
                literal_type = "foot";
                break;
            case BUS:
                literal_type = "psv";
                break;
            case CAR:
                literal_type = "motorcar";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        String command = "routino-router --dir=src/main/resources/map_data " +
                "--lat1=" + p1.getPoint().getLatitude() +
                " --lon1=" + p1.getPoint().getLongitude() + " --lat2=" + p2.getPoint().getLatitude() +
                " --lon2=" + p2.getPoint().getLongitude() + " --transport=" +literal_type + "\n";
        processBuilder.command("bash", "-c", command);
        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                String[] lines = Objects.requireNonNull(RoutinoAdapter.readFile()).split("\n");
                String lastLine = lines[lines.length - 1];
                double length = Double.parseDouble(lastLine.substring(lastLine.indexOf(" min")+4,lastLine.lastIndexOf(" km")).strip());
                double timeSpent = Double.parseDouble(lastLine.substring(lastLine.lastIndexOf(" km") + 3, lastLine.lastIndexOf(" min")).strip());
                double cost;
                switch (type){
                    case FOOT:
                        cost = 0;
                        break;
                    case BUS:
                    case CAR:
                        cost = Constants.startTripPrice + length * Constants.costPerKM;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + type);
                }
                return new Route(length,timeSpent / 60 ,cost,Constants.initialPheromonesLevel, p1, p2,type);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new RouteCannotBeCreatedException("Imposible to build route between selected points " + p1.name + " " + p2.name);
    }

}
