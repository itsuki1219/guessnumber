package com.example.demo;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {

    public static void main(String[] args) {
    	  System.out.println("0?\\d+");
    	// 生成答案
        int[] answer = generateAnswer();
        
        // 遊戲開始
        for (int round = 1; round <= 5; round++) {
            // 玩家猜數字
            int[] guess;

            do {
                guess = getPlayerGuess();
            } while (guess == null);

            // 檢查猜測結果
            int[] result = checkGuess(answer, guess);
            System.out.println("第 " + round + " 輪結果：" + result[0] + "A" + result[1] + "B");
            printCorrectDigits(answer, guess);

            // 判斷是否猜中
            if (result[0] == 4) {
                System.out.print("恭喜你猜中了！答案是 ");
                printArray(answer);
                break;
            }

            // 顯示答案
            if (round == 5) {
                System.out.print("遊戲結束，答案是 ");
                printArray(answer);
            }
        }
    }

    // 生成四位不重複的數字的答案
    private static int[] generateAnswer() {
        int[] answer = new int[4];
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int digit;
            do {
                digit = random.nextInt(10);
            } while (contains(answer, digit));

            answer[i] = digit;
        }

        return answer;
    }

    // 玩家猜數字，限制輸入四位數字且沒有空值
    private static int[] getPlayerGuess() {
        Scanner scanner = new Scanner(System.in);
        int[] guess = new int[4];

        System.out.println("請輸入一個四位數字：");
        String input = scanner.next();

        // 檢查是否為四位數字且無空值
        if (input.length() == 4 && input.matches("\\d+")) {
            for (int i = 0; i < 4; i++) {
                guess[i] = Character.getNumericValue(input.charAt(i));
            }
            return guess;
        } else {
            System.out.println("請輸入四位數字！");
            return null;
        }
    }

    // 檢查猜測結果，返回A和B的數量
    private static int[] checkGuess(int[] answer, int[] guess) {
        int[] result = new int[2]; // result[0]表示A的數量，result[1]表示B的數量

        for (int i = 0; i < 4; i++) {
            if (guess[i] == answer[i]) {
                result[0]++; // 數字和位置都正確，A加一
            } else if (contains(answer, guess[i])) {
                result[1]++; // 數字正確但位置不對，B加一
            }
        }

        return result;
    }

    // 判斷數組中是否包含特定數字
    private static boolean contains(int[] array, int num) {
        for (int i : array) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

    // 以純數字形式印出數組
    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num);
        }
        System.out.println();
    }

    // 印出正確的數字和位置
    private static void printCorrectDigits(int[] answer, int[] guess) {
        System.out.print("正確的數字和位置有：");
        for (int i = 0; i < 4; i++) {
            if (answer[i] == guess[i]) {
                System.out.print(answer[i]);
            }
        }
        System.out.println();
    }
}
