package cityrescue;

public class Testing {
    public static void main(String[] args) {
        Unit[] arr = new Unit[10];
        for (int i = 0; i < 8; i++) {
            arr[i] = new Ambulance(); 
        }
        arr[6] = null;

        for (Unit elem : arr) {
            System.out.println(elem);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                if (arr[i+1] != null) {
                    arr[i] = arr[i+1];
                    arr[i+1] = null;
                }
            }
        }

        System.out.println("BREAK");
        System.out.println();


        for (Unit elem : arr) {
            System.out.println(elem);
        }

        /**
         * Compile and execution commands for this file:
         * PS C:\Users\samue\Documents\EXCSYear1\Object Oriented Programming\CityRescue_Coursework\src\main\java> javac cityrescue/Testing.java
>> 
            PS C:\Users\samue\Documents\EXCSYear1\Object Oriented Programming\CityRescue_Coursework\src\main\java> java cityrescue.Testing   


            File Path dependent on local storage
         */

        
    }
}