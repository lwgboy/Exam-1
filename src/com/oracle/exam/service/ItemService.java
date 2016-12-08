package com.oracle.exam.service;

import com.oracle.exam.domain.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2016/12/2.
 */
public class ItemService {
    private Item[] items;
    private static final String file = "D:/WorkData/盈佳/JProject004-EXM-(单机考试管理软件)/JP004.1-需求说明/Items.txt";
    private static final String filePath = "D:/WorkData/盈佳/JProject004-EXM-(单机考试管理软件)/answer.dat";

    public ItemService() {
        List<String> list = readTextFile(file);
        int len = list.size();
        items = new Item[len];
        for (int i = 0, j = 0; i < len; i++) {

            String title = list.get(i++);
            int id = Integer.parseInt(title.substring(title.indexOf("第")+1,title.indexOf("题")));
            title = title.substring(title.indexOf("题")+2);
            String a = list.get(i++);
            String b = list.get(i++);
            String c = list.get(i++);
            String d = list.get(i++);
            String answer = list.get(i++);
            items[j++] = new Item(id,title, a, b, c, d, answer);
        }
    }

    public Item getItem(int index) {
        return items[index];
    }

    /**
     * 以文本行为单位读取文件
     *
     * @param filename
     */
    private List<String> readTextFile(String filename) {
        List<String> list = new ArrayList<>();
        File file = new File(filename);
        BufferedReader bufferedReader = null;
        String line = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * 并将数组中的内容以对象形式序列化写入到文件中保存。
     * 写一个Recode类去封装考试信息
     * 分数 时间 答案数组
     *
     *
     * @param answer
     */
    public void saveAnswer(char[] answer) {
        File file = new File(filePath);
        // 文件不存在,就创建该文件
        if (!file.exists()) {
            // 先创建文件的目录
            String path = filePath.substring(0, filePath.lastIndexOf('/'));
            File fold = new File(path);
            fold.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(answer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.flush();
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 反序列化
     */
    public void readAnswer(String filePath) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            char[] answer = (char[]) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void judgment(char[] answer) {
        ItemService itemService = new ItemService();
        int count = 0;
        readAnswer(filePath);
        System.out.println("正确答案\t\t您的答案");
        for (int i = 0; i < 10; i++) {
            String result = itemService.getItem(i).getAnswer();
            String ans = String.valueOf(answer[i]).toUpperCase();
            System.out.println("第" + (i + 1) + "题：" + result + "\t\t   "+ ans);
            if (result.toString().equalsIgnoreCase(ans)) {
                count++;
            }
        }
        System.out.println("您一共答对了 " + count + "道题，最终得分为：" + (count * 10));
    }
}
