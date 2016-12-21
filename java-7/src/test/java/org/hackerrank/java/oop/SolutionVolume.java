package org.hackerrank.java.oop;

import java.io.IOException;
import java.security.Permission;
import java.util.Scanner;


class FigureVolumeCalculator {
    public double main(int a) {
        return main(a, a, a);
    }

    public double main(int l, int b, int n) {
        return l * b * n;
    }

    double main(double r) {
        return main(r, (2.0 / 3.0) * r);
    }

    double main(double r, double h) {
        return Math.PI * r * r * h;
    }
}

class Output {
    public void display(double d) {
        System.out.printf("%.3f%n", d);
    }
}

class Calculate {
    static Scanner sc = new Scanner(System.in);
    static FigureVolumeCalculator calculator;
    public Output output = new Output();

    public int getINTVal() throws IOException {
        int n = sc.nextInt();
        if (n <= 0) {
            throw new NumberFormatException("All the values must be positive");
        }
        return n;
    }

    public double getDoubleVal() throws IOException {
        double d = sc.nextDouble();
        if (d <= 0) {
            throw new NumberFormatException("All the values must be positive");
        }
        return d;
    }

    public static FigureVolumeCalculator get_Vol() {
        if (calculator == null) {
            calculator = new FigureVolumeCalculator();
        }
        return calculator;
    }
}

public class SolutionVolume {

    public static void main(String[] args) {
        Do_Not_Terminate.forbidExit();
        try {
            Calculate cal = new Calculate();
            int T = cal.getINTVal();
            while (T-- > 0) {
                double volume = 0.0d;
                int ch = cal.getINTVal();
                if (ch == 1) {

                    int a = cal.getINTVal();
                    volume = Calculate.get_Vol().main(a);


                } else if (ch == 2) {

                    int l = cal.getINTVal();
                    int b = cal.getINTVal();
                    int h = cal.getINTVal();
                    volume = Calculate.get_Vol().main(l, b, h);

                } else if (ch == 3) {

                    double r = cal.getDoubleVal();
                    volume = Calculate.get_Vol().main(r);

                } else if (ch == 4) {

                    double r = cal.getDoubleVal();
                    double h = cal.getDoubleVal();
                    volume = Calculate.get_Vol().main(r, h);

                }
                cal.output.display(volume);
            }

        } catch (NumberFormatException e) {
           System.out.println(e);
        } catch (Do_Not_Terminate.ExitTrappedException e) {
            System.out.println("Unsuccessful Termination!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * This class prevents the user from using System.exit(0)
 * from terminating the program abnormally.
 */
class Do_Not_Terminate {

    public static class ExitTrappedException extends SecurityException {
    }

    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}//end of Do_Not_Terminate