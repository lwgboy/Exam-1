package com.oracle.exam.view;

import com.oracle.exam.service.ItemService;

import java.util.Scanner;

import static com.oracle.exam.view.TSUtility.readConfirmSelection;

/**
 * Created by Jason on 2016/12/2.
 */
public class ExamView {
    ItemService itemService = new ItemService();
    private char[] answer = new char[10];
    private int i = 0;// i表示第几道题

    public char getUserAction() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        char result = ' ';
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() > 0) {
                result = line.toUpperCase().charAt(0);
                if (('A' == result || 'B' == result || 'C' == result || 'D' == result || 'N' == result || 'P' == result || 'F' == result)) {
                    return result;
                } else {
                    System.out.println("输入有误，请重新输入：");
                }
            }
        }
        return result;
    }

    public void displayItem(int i) {
        System.out.println(itemService.getItem(i));
        if (answer[i] != 0) {
            System.out.println("您输入的答案是：" + answer[i]);
            System.out.println("输入A-D修改本题答案或输入 N/P 选择其它题：");
        } else {
            System.out.print("请输入您的答案或输入 N/P 选择其它题： ");
        }
    }

    public void testExam() {
        System.out.println("--------------帮助信息-------------");
        System.out.println("你现在已经进入考试，本次考试每次显示一道题\n在每道题的下面可以直接输入你的答案");
        System.out.println(" A-D 为候选答案，N/P 选择下一题或上一题");
        System.out.println("当完成考试后，输入 f/F 确认考试完成并提交答案");
        System.out.println("-----------------------------------");

        displayItem(i);
        boolean loopflag = true;
        while (loopflag) {
            char action = getUserAction();
            switch (action) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                    if (i < 10) {
                        if (answer[i] != 0) {
                            answer[i] = action;
                            System.out.println("现在已将答案修改为：" + answer[i]);
                        } else {
                            answer[i] = action;
                        }
                    }
                    break;
                case 'N':
                    if (i < 9) {
                        displayItem(++i);
                        break;
                    } else {
                        itemService.saveAnswer(answer);
                        System.out.println("您已完成所有试题，请确认无误后按 F 键提交答案！");
                        break;
                    }
                case 'P':
                    if (i == 0) {
                        System.out.println("当前已是第一题 ！ ");
                    } else {
                        displayItem(--i);
                    }
                    break;
                case 'F':
                    System.out.println("您确认要结束考试吗？（Y/N）:");
                    if (readConfirmSelection() == 'Y') {
                        itemService.judgment(answer);
                        loopflag = false;
                    }
                    break;
            }
        }
    }
}
