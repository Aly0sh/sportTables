package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DbWorker dbWorker=new DbWorker();
        Scanner in=new Scanner(System.in);
        System.out.println("1.    Sports\n" +
                "else. Trainers");
        System.out.print("Введите номер номер таблицы: ");
        if (in.nextInt()==1) {
            dbWorker.selectSports();
        }
        else {
            dbWorker.selectTrainers();
        }
    }
}
