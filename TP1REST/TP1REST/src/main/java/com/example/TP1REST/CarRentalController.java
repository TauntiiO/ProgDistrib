package com.example.TP1REST;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarRentalController {

    // Get car features
    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Car getCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
        // In a real app, you would fetch this from a database
        // This is just a mock implementation
        if ("11AA22".equals(plateNumber)) {
            return new Car("11AA22", "Ferrari", 100);
        }
        throw new Exception("Car not found");
    }

    // Rent or return a car
    @PutMapping(value = "/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rentOrGetBack(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value = "rent", required = true) boolean rent,
            @RequestBody(required = false) RentalDates dates) throws Exception {
        
        if (rent) {
            // Handle car rental
            if (dates == null) {
                throw new Exception("Rental dates are required");
            }
            System.out.println("Renting car " + plateNumber + " from " + dates.getBegin() + " to " + dates.getEnd());
            // In a real app, you would update the car's status in the database
        } else {
            // Handle car return
            System.out.println("Returning car " + plateNumber);
            // In a real app, you would update the car's status in the database
        }
    }

    // Supporting classes
    public static class Car {
        private String plateNumber;
        private String brand;
        private int price;

        public Car(String plateNumber, String brand, int price) {
            this.plateNumber = plateNumber;
            this.brand = brand;
            this.price = price;
        }

        // Getters and setters
        public String getPlateNumber() { return plateNumber; }
        public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
        public String getBrand() { return brand; }
        public void setBrand(String brand) { this.brand = brand; }
        public int getPrice() { return price; }
        public void setPrice(int price) { this.price = price; }
    }

    public static class RentalDates {
        private String begin;
        private String end;

        // Getters and setters
        public String getBegin() { return begin; }
        public void setBegin(String begin) { this.begin = begin; }
        public String getEnd() { return end; }
        public void setEnd(String end) { this.end = end; }
    }
}