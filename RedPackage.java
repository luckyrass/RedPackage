package dataStruct;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by jdan on 2018/2/9.
 */
public class RedPackage {

    private RedPackage() {
    }

    public static double[] generateMoneyVector(double money, int peopleNum) {
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

    public static void main(String[] args) {
        // useCase1: 10, 5
        double price1 = 10;
        int peopleNum1 = 5;
        System.out.printf("price1 = %.2f, peopleNum1 = %d\n", price1, peopleNum1);
        for (double price : RedPackage.generateMoneyVector(price1, peopleNum1)) {
            System.out.printf("%.2f,", price);
        }
        System.out.println();
        // useCase2: 5, 10
        double price2 = 5;
        int peopleNum2 = 10;
        System.out.printf("price2 = %.2f, peopleNum2 = %d\n", price2, peopleNum2);
        for (double price : RedPackage.generateMoneyVector(price2, peopleNum2)) {
            System.out.printf("%.2f,", price);
        }
        System.out.println();
        // useCase3: 0.05, 10
        double price3 = 0.05;
        int peopleNum3 = 10;
        System.out.printf("price3 = %.2f, peopleNum3 = %d\n", price3, peopleNum3);
        for (double price : RedPackage.generateMoneyVector(price3, peopleNum3)) {
            System.out.printf("%.2f,", price);
        }
    }
}
