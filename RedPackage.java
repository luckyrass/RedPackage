package dataStruct;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by jdan on 2018/2/9.
 */
public class RedPackage {

    private RedPackage() {
    }

    // method 1
    public static double[] generateMoneyAverageVector(double money, int peopleNum) {
        // money in red package is not enough
        if (money < peopleNum * 0.01) {
            return new double[0];
        }
        double remain = money - peopleNum * 0.01;
        int shares = (int)(remain * 100);
        double[] redPackageArray = new double[peopleNum];
        Arrays.fill(redPackageArray, 0);
        Random rand = new Random(System.currentTimeMillis());
        while (shares-- > 0) {
            int idx = rand.nextInt(peopleNum);
            redPackageArray[idx] += 0.01;
        }
        return redPackageArray;
    }

    // method 2
    public static double[] generateMoneyGaussianVector(double money, int peopleNum) {
        // money in red package is not enough
        if (money < peopleNum * 0.01) {
            return new double[0];
        }
        double[] redPackageArray = new double[peopleNum];
        Arrays.fill(redPackageArray, 0);
        // generate a normal distribution array
        double moneyLeft = money - peopleNum * 0.01;
        double mean = moneyLeft / peopleNum;
        double stddev = mean / 2;
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < peopleNum - 1; i++) {
            double shareMoney = rand.nextGaussian() * stddev + mean;
            if (shareMoney < 0) {
                shareMoney = 0;
            }
            if (shareMoney > moneyLeft) {
                shareMoney = moneyLeft;
            }
            redPackageArray[i] = shareMoney + 0.01;
            moneyLeft -= shareMoney;
        }
        redPackageArray[peopleNum-1] = moneyLeft + 0.01;
        return redPackageArray;
    }

    public static void main(String[] args) {
        // useCase1: 10, 5
        double price1 = 10;
        int peopleNum1 = 5;
        System.out.println("==========generateMoneyAverageVector=========");
        System.out.printf("price1 = %.2f, peopleNum1 = %d\n", price1, peopleNum1);
        for (int i = 0; i < 3; i++) {
            for (double price : RedPackage.generateMoneyAverageVector(price1, peopleNum1)) {
                System.out.printf("%.2f,", price);
            }
            System.out.println();
        }
        // useCase2: 5, 10
        double price2 = 5;
        int peopleNum2 = 10;
        System.out.printf("price2 = %.2f, peopleNum2 = %d\n", price2, peopleNum2);
        for (int i = 0; i < 3; i++) {
            for (double price : RedPackage.generateMoneyAverageVector(price2, peopleNum2)) {
                System.out.printf("%.2f,", price);
            }
            System.out.println();
        }

        System.out.println("==========generateMoneyAverageVector=========");
        System.out.printf("price1 = %.2f, peopleNum1 = %d\n", price1, peopleNum1);
        for (int i = 0; i < 3; i++) {
            for (double price : RedPackage.generateMoneyGaussianVector(price1, peopleNum1)) {
                System.out.printf("%.2f,", price);
            }
            System.out.println();
        }

        System.out.printf("price2 = %.2f, peopleNum2 = %d\n", price2, peopleNum2);
        for (int i = 0; i < 3; i++) {
            for (double price : RedPackage.generateMoneyGaussianVector(price2, peopleNum2)) {
                System.out.printf("%.2f,", price);
            }
            System.out.println();
        }
    }
}
