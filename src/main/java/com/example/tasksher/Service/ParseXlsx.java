package com.example.tasksher.Service;

import com.example.tasksher.Model.Flight;
import com.example.tasksher.Model.Point;
import com.example.tasksher.Model.Road;
import com.example.tasksher.Repository.FlightRepository;
import com.example.tasksher.Repository.PointRepository;
import com.example.tasksher.Repository.RoadRepository;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

@Service
public class ParseXlsx {
    private final PointRepository pointRepository;
    private final RoadRepository roadRepository;
    private final FlightRepository flightRepository;
    File myfile1 = new File("src/main/resources/table/Distance.xlsx");
    File flightsTable = new File("src/main/resources/table/Рейсы, пассажиры.xlsx");

    public ParseXlsx(PointRepository pointRepository, RoadRepository roadRepository, FlightRepository flightRepository) {
        this.pointRepository = pointRepository;
        this.roadRepository = roadRepository;
        this.flightRepository = flightRepository;
    }

    public void ParseSheetPoint(){
        try {
            FileInputStream fis = new FileInputStream(myfile1);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            Iterator<Row> rowIterator = xssfSheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                if(row.getCell(0).getCellType() == CellType.STRING){
                    continue;
                }
                int point_id = (int) row.getCell(0).getNumericCellValue();
                final String location_id;
                if(row.getCell(1).getCellType() == CellType.STRING){
                    location_id = row.getCell(1).getStringCellValue();
                } else {
                    location_id = String.valueOf((int) row.getCell(1).getNumericCellValue());
                }
                final Point point = new Point(point_id,location_id);
                pointRepository.save(point);
            }
            fis.close();
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            throw new RuntimeException(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            throw new RuntimeException(i.getMessage());
        }
    }
    public void ParseSheetRoad(){
        try {
            FileInputStream fis = new FileInputStream(myfile1);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(1);
            Iterator<Row> rowIterator = xssfSheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                if(row.getCell(0).getCellType() == CellType.STRING){
                    continue;
                }
                final int road_id = (int) row.getCell(0).getNumericCellValue();
                final int source_point_id = (int) row.getCell(1).getNumericCellValue();
                final int target_point_id = (int) row.getCell(2).getNumericCellValue();
                final int distance = (int) row.getCell(3).getNumericCellValue();
                final Road road = new Road(road_id,source_point_id,target_point_id,distance);
                roadRepository.save(road);
            }
            fis.close();
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            throw new RuntimeException(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            throw new RuntimeException(i.getMessage());
        }
    }
    public void ParseFlights(){
        try {
            FileInputStream fis = new FileInputStream(flightsTable);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            Iterator<Row> rowIterator = xssfSheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                if(row.getCell(4).getCellType() == CellType.STRING){
                    continue;
                }
                final String date = row.getCell(0).getStringCellValue();
                final String type = row.getCell(1).getStringCellValue();
                final String terminal = row.getCell(2).getStringCellValue();
                final String codeAK = row.getCell(3).getStringCellValue();
                final int numberFlight = (int) row.getCell(4).getNumericCellValue();
                final LocalDateTime planTime = row.getCell(5).getLocalDateTimeCellValue();
                final String codeAp = row.getCell(6).getStringCellValue();
                final String airport = row.getCell(7).getStringCellValue();
                final String typeBC;
                final String numberParkingPlace;
                final String numberGate;
                final int countPassenger = (int) row.getCell(11).getNumericCellValue();
                if(row.getCell(8).getCellType() == CellType.NUMERIC){
                    typeBC = String.valueOf(row.getCell(8).getNumericCellValue());
                } else {
                    typeBC = row.getCell(8).getStringCellValue();
                }
                if(row.getCell(9).getCellType() == CellType.NUMERIC){
                    numberParkingPlace = String.valueOf((int) row.getCell(9).getNumericCellValue());
                } else {
                    numberParkingPlace = row.getCell(9).getStringCellValue();
                }
                if(row.getCell(10).getCellType() == CellType.NUMERIC){
                    numberGate = String.valueOf(row.getCell(10).getNumericCellValue());
                } else {
                    numberGate = row.getCell(10).getStringCellValue();
                }
                final Flight flight = new Flight(parseDate(date),type,terminal,codeAK,numberFlight,
                        planTime.getHour() + ":" + planTime.getMinute(),codeAp,airport,typeBC,
                        numberParkingPlace,numberGate,countPassenger,planTime.getMinute(), planTime.getHour());
                flightRepository.save(flight);
            }
            fis.close();
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            throw new RuntimeException(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            throw new RuntimeException(i.getMessage());
        }
    }
    private LocalDateTime parseDate(final String date){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date,formatter).atStartOfDay();
    }

}
