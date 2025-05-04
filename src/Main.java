import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("-----------------------------------------");
            System.out.println("Welcome to Calender Console Application");
            System.out.println("-----------------------------------------");
            System.out.println("1.Find the Day");
            System.out.println("2.Show the Calender");
            System.out.println("3.Exit");
            System.out.println("-----------------------------------------");
            System.out.println("Enter your choice");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Date : ");
                    int date = sc.nextInt();
                    if(date > 31)
                    {
                        System.out.println("Warning ! Please enter valid date like(1-31) : ");
                        break;
                    }
                    System.out.println("Enter Month : ");
                    int month = sc.nextInt();
                    if(month > 12)
                    {
                        System.out.println("Warning ! Please enter valid month like(1-12) : ");
                        break;
                    }
                    System.out.println("Enter Year : ");
                    int year = sc.nextInt();
                    System.out.println("-----------------------------------------");
                    System.out.println("The Day is " + dayCalculate(date, month, year));
                    System.out.println("-----------------------------------------");
                    break;
                case 2:
                    System.out.println("Enter Month You Want to Display : ");
                    int mon = sc.nextInt();
                    System.out.println("Enter Year You Want to Display : ");
                    int yea = sc.nextInt();
                    displayMonth(1, mon, yea);
                    while (true) {
                        System.out.println("\nGo to next (or) previous ");
                        System.out.println("1.Next ");
                        System.out.println("2.Previous ");
                        System.out.println("3.Back");
                        int n = sc.nextInt();
                        if (n > 2) break;
                        switch (n) {
                            case 1:
                                mon++;
                                if (mon > 12) {
                                    mon = 1;
                                    yea++;
                                    displayMonth(1, mon, yea);
                                } else {
                                    displayMonth(1, mon, yea);
                                }
                                break;
                            case 2:
                                mon--;
                                if (mon == 0) {
                                    mon = 12;
                                    yea--;
                                    displayMonth(1, mon, yea);
                                } else {
                                    displayMonth(1, mon, yea);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 3:
                    flag = false;
                    break;
            }
        }

    }

    static String dayCalculate(int date, int month, int year) {
        int yearCode = findYearCode(year);
        int res = 0;
        String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        HashMap<Integer, Integer> mon = new HashMap<>() {
            {
                put(1, 0);
                put(2, 3);
                put(3, 3);
                put(4, 6);
                put(5, 1);
                put(6, 4);
                put(7, 6);
                put(8, 2);
                put(9, 5);
                put(10, 0);
                put(11, 3);
                put(12, 5);
            }
        };
        int rem = year % 100;
        res += rem;
        int four = rem / 4;
        res += four;
        res += date;
        res += mon.get(month);
        res += yearCode;
        if (((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) && month <= 2) {
            res--;
        }
        return days[res % 7];
    }

    static int findYearCode(int year) {
        int century = year / 100;
        if (century % 4 == 0) {
            return 6;
        } else if (century % 4 == 1) {
            return 4;
        } else if (century % 4 == 2) {
            return 2;
        } else {
            return 0;
        }
    }

    static void displayMonth(int date, int month, int year) {

        String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        String day = dayCalculate(date, month, year);
        int idx = Arrays.asList(days).indexOf(day);
        HashMap<Integer, Integer> monthValue = new HashMap<>() {
            {
                put(1, 31);
                put(2, 28);
                put(3, 31);
                put(4, 30);
                put(5, 31);
                put(6, 30);
                put(7, 31);
                put(8, 31);
                put(9, 30);
                put(10, 31);
                put(11, 30);
                put(12, 31);
            }
        };
        HashMap<Integer, String> months = new HashMap<>() {
            {
                put(1, "JANUARY");
                put(2, "FEBRUARY");
                put(3, "MARCH");
                put(4, "APRIL");
                put(5, "MAY");
                put(6, "JUNE");
                put(7, "JULY");
                put(8, "AUGUST");
                put(9, "SEPTEMBER");
                put(10, "OCTOBER");
                put(11, "NOVEMBER");
                put(12, "DECEMBER");
            }
        };
        int val = 0;
        if (((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) && month == 2) {
            val = monthValue.get(month) + 1;
        } else {
            val = monthValue.get(month);
        }
        System.out.println("----------------------------------------------------------");
        System.out.printf("----------%3s%s%3s------------%3s%d%3s------------", "", months.get(month), "", "", year, "");
        System.out.println("\n----------------------------------------------------------");
        System.out.printf("| %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s |\n", days[0], days[1], days[2], days[3], days[4], days[5], days[6]);
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < 5; i++) {
            for (int s = 0; s < idx; s++) {
                System.out.printf("| %5s ", " ");
            }
            for (int j = idx; j < days.length; j++) {
                if (date <= val) {
                    System.out.printf("| %5d ", date);
                    date++;
                } else {
                    System.out.printf("| %5s ", " ");
                }
            }
            System.out.print("|");
            idx = 0;
            System.out.println();
            System.out.println("----------------------------------------------------------");
        }
        if(date <= val)
        {
            for (int k = 0; k < days.length; k++) {
                if (date <= val) {
                    System.out.printf("| %5d ", date);
                    date++;
                }
                else {
                    System.out.printf("| %5s ", " ");
                }
            }
            System.out.print("|");
            System.out.println("\n----------------------------------------------------------");
        }
    }
}
