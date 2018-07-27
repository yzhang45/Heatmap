import java.io.*;
import java.util.Scanner;

public class Builder2 {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Integer topnumber = 1000;
        Integer record = 0, count = 0, chose;
        int maxsales = 0;
        String str;
        PrintWriter pw = new PrintWriter("database.txt");//for (byte i=0;i<9;i++){for (byte j=0;j<9;j++)  pw.printf("%3d",position[i][j].getValue());pw.println();}pw.close();}
        File data = new File("data.txt");
        Scanner scanfile = new Scanner(data);
        Double[][] position = new Double[2][35000];
        int[] sales = new int[35000];
//read sellers' information
        while (scanfile.hasNext()) {
            position[0][count] = scanfile.nextDouble();
            position[1][count] = scanfile.nextDouble();
            sales[count] = scanfile.nextInt();
            count++;
        }
//----------------------------------------------------------------------------------------------------------------
        Double lomax = position[0][0], lomin = position[0][0], almax = position[1][0], almin = position[1][0], alan = 0.0, loan = 0.0;
        for (int i = 0; i < count; i++) {
            if (position[0][i] > lomax) lomax = position[0][i];
            if (position[0][i] < lomin) lomin = position[0][i];
            if (position[1][i] > almax) almax = position[1][i];
            if (position[1][i] < almin) almin = position[1][i];
        }
        System.out.println("the range is(" + lomin + "-" + lomax + "," + almin + "-" + almax + ")");
        System.out.println("What would you like to do?\nA.Compare selections\nB.Evaluate good choices");
        if ((scan.nextLine() + "0").toUpperCase().charAt(0) == 'A') {
            System.out.println("!");

            int all = 0;
            System.out.println("position,prize,area");
            position[0][34999] = scan.nextDouble();
            position[1][34999] = scan.nextDouble();
            Double cost = scan.nextDouble() * scan.nextDouble(), income, profit;
            int tmp = 0;
            System.out.println(position[0][34999] + " " + position[1][34999]);
            for (int i = 0; i < count; i++)
                if (((position[0][34999] - position[0][i]) * (position[0][34999] - position[0][i]) * 12321 * 0.92 * 0.92 + (position[1][34999] - position[1][i]) * (position[1][34999] - position[1][i]) * 12321) <= 4.0)
                    tmp = tmp + sales[i];
            System.out.println(tmp);

        } else {
            Double[][] positionan = new Double[2][topnumber];
            int[] salesan = new int[topnumber];
            int process = 0;
            for (Double lo = lomin; lo <= lomax; lo = lo + 0.0005) {
                for (Double al = almin; al <= almax; al = al + 0.0005) {
                    int tmp = 0;
                    for (int i = 0; i < count; i++) {
                        //Math.pow(n,2)
                        if ((Math.pow(((lo - position[0][i]) * 111) * 0.92, 2) + Math.pow((al - position[1][i]) * 111, 2)) <= 1.0)
                            tmp = tmp + sales[i];
                    }
                    if (tmp > salesan[topnumber - 1]) {
                        salesan[topnumber - 1] = tmp;
                        positionan[0][topnumber - 1] = lo;
                        positionan[1][topnumber - 1] = al;
                        for (int i = topnumber - 1; i > 0; i--)
                            if (salesan[i] > salesan[i - 1]) {
                                int tmp1;
                                tmp1 = salesan[i];
                                salesan[i] = salesan[i - 1];
                                salesan[i - 1] = tmp1;
                                Double tmp2;
                                tmp2 = positionan[0][i];
                                positionan[0][i] = positionan[0][i - 1];
                                positionan[0][i - 1] = tmp2;
                                tmp2 = positionan[1][i];
                                positionan[1][i] = positionan[1][i - 1];
                                positionan[1][i - 1] = tmp2;
                            }
                    }
                }
                process++;
                if (process % 20 == 0) System.out.println(process / 20 + "%");
            }
            for (int i = 0; i < topnumber; i++) {
                pw.println(positionan[0][i] + " " + positionan[1][i] + " " + salesan[i]);
            }
            System.out.println(positionan[0][topnumber - 1] + " " + positionan[1][topnumber - 1] + " " + salesan[topnumber - 1]);
        }
        pw.close();
        scan.close();
        scanfile.close();
    }
}