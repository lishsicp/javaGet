import java.util.*;
public class JetCinema {
	
	private static int rows;
	private static int columns;
	private static String[][] sitsTable;
	private static boolean exit = false;
	private static int numOfTickets = 0;
	private static int currentIncome = 0;
	private static int totalIncome = 0;
	private static double percentage;
	private static double sum;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of rows:");
		rows = sc.nextInt();
		System.out.println("Enter the number of seats in each row:");
		columns = sc.nextInt();
		
		sum = rows * 1.0 * columns;
		if (columns * rows < 60) {
			totalIncome = rows * columns * 10;
		} else {
			totalIncome = rows / 2 * columns * 10 + (rows - (rows / 2)) * columns * 8;
		}
		
		sitsTable = new String[rows + 1][columns + 1];
		CreateTable(sitsTable);
		while (!exit) {
			
			System.out.println("1. Show the seats");
			System.out.println("2. Buy a ticket");
			System.out.println("3. Statistics");
			System.out.println("0. Exit");
			int option = sc.nextInt();
			switch (option) {
				case 1:
					DisplayTable(sitsTable);
					break;
				case 2:
					BookSit(sitsTable);
					break;
				case 3:
					Stats();
					break;
				case 0:
					sc.close();
					exit = true;
					break;
				default: 
					exit = true;
					break;
			}
		}
		// DisplayTable(sitsTable);
		
	}
	
	public static void CreateTable(String[][] sitsTable) {
		for (int i = 0; i < sitsTable.length; i++) {
        	for (int j = 0; j < sitsTable[i].length; j++) {
        		sitsTable[0][0] = " ";
        		if (j >= 1) {
        			sitsTable[0][j] = " " + j;
        		}
        		if (i >= 1) {
        			sitsTable[i][0] = i + "";
        		}
        		if (i != 0 && j != 0) {
        			sitsTable[i][j] = " S";
        		}
        		// System.out.print(sitsTable[i][j]);
        	}
        	// System.out.println();
        }
	}
	
	
	public static void DisplayTable(String[][] sitsTable) {
		System.out.println("Cinema:");
		for (int i = 0; i < sitsTable.length; i++) {
        	for (int j = 0; j < sitsTable[i].length; j++) {
        		System.out.print(sitsTable[i][j]);
        	}
        	System.out.println();
        }
	}
	public static void BookSit(String[][] sitsTable) {
		Scanner sc = new Scanner(System.in);
		System.out.println();
        System.out.println("Enter a row number:");
        int row_num = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int sit_num = sc.nextInt();
        int ticket = 0;
        int half_sits = rows / 2;
        
        boolean alreadyBooked = false;
        if (CountSits(sitsTable) <= 60) {
        	ticket = 10;
        } else if (CountSits(sitsTable) > 60) {
        	if (row_num < half_sits) {
        		ticket = 10;
        	} else if (row_num >= half_sits) {
        		ticket = 8;
        	}
        }
        
        if (row_num <= 0 || sit_num <= 0 || row_num > rows || sit_num > columns ) {
        	System.out.println();
        	System.out.println("Wrong input!");
        	System.out.println();
        	BookSit(sitsTable);
        } else if (sitsTable[row_num][sit_num].equals(" B")) {
    			alreadyBooked = true;
    			System.out.println();
    			System.out.println("That ticket has already been purchased!");
    			System.out.println();
    			BookSit(sitsTable);
    	} else {
        	for (int i = 0; i < sitsTable.length; i++) {
            	for (int j = 0; j < sitsTable[i].length; j++) {
            		if (i != 0 && j != 0 && sitsTable[i][j].equals(" S")) {
            			sitsTable[row_num][sit_num] = " B";
            			
            		} 
            	}
            }
        	System.out.println();
            if (!alreadyBooked) {
            	numOfTickets += 1;
            	System.out.println(String.format("Ticket price: $%d", ticket));
            	currentIncome += ticket;
            	percentage = numOfTickets * 1.0 / sum * 100;
            }
            System.out.println();
        }
        
        
        
        // sc.close();
	}
	
	public static int CountSits(String[][] sitsTable) {
		int counter = 0;
		for (int i = 0; i < sitsTable.length; i++) {
        	for (int j = 0; j < sitsTable[i].length; j++) {
        		if (sitsTable[i][j].equals(" S") || sitsTable[i][j].equals(" B")) {
        			counter++;
        		}
        	}
        }
		return counter;
	}
	
	
	public static void Stats() {
		char p = '%';
		System.out.printf("Number of purchased tickets: %d%n", numOfTickets);
		System.out.printf("Percentage: %.2f%c\n", percentage, p);
		System.out.printf("Current income: $%d%n", currentIncome);
		System.out.printf("Total income: $%d%n", totalIncome);
	}
	
}
