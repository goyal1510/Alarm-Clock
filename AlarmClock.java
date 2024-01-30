import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alarm Clock");
        System.out.print("Enter the alarm time in HH:MM format (24-hour clock): ");
        String alarmTimeInput = scanner.nextLine();
        // Parse the alarm time input
        String[] parts = alarmTimeInput.split(":");
        int alarmHour = Integer.parseInt(parts[0]);
        int alarmMinute = Integer.parseInt(parts[1]);
        // Create the LocalTime object for the alarm time
        LocalTime alarmTime = LocalTime.of(alarmHour, alarmMinute);
        // Get the current time
        LocalTime currentTime = LocalTime.now();
        // Calculate the time difference
        long timeDifference = calculateTimeDifference(currentTime, alarmTime);
        if (timeDifference > 0) {
            System.out.println("Alarm set successfully!");
            try {
                // Sleep for the remaining time
                Thread.sleep(timeDifference);
                System.out.println("Wake up! It's time!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid alarm time. Please enter a future time.");
        }
        scanner.close();
    }

    public static long calculateTimeDifference(LocalTime currentTime, LocalTime alarmTime) {
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();
        int currentSecond = currentTime.getSecond();
        int alarmHour = alarmTime.getHour();
        int alarmMinute = alarmTime.getMinute();
        int alarmSecond = alarmTime.getSecond();
        // Convert the current time and alarm time to seconds
        long currentTimeInSeconds = (currentHour * 3600) + (currentMinute * 60) + currentSecond;
        long alarmTimeInSeconds = (alarmHour * 3600) + (alarmMinute * 60) + alarmSecond;
        // Calculate the time difference
        long timeDifference = alarmTimeInSeconds - currentTimeInSeconds;
        // Adjust the time difference if it's negative (alarm time is earlier than
        // current time)
        if (timeDifference < 0) {
            timeDifference += 24 * 3600; // Add 24 hours (in seconds) to the time difference
        }
        return timeDifference * 1000; // Convert seconds to milliseconds
    }
}