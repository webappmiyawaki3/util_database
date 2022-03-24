package main;

import java.util.Map;
import java.util.Scanner;

import connect.JdbcModel;
import connect.ObjectFactoryForConnect;
import connect.SampleObject;

public class testConnect {
    public static void main(String[] args) {
        JdbcModel jm = new JdbcModel();
        var flg = true;
        String select;
        while (flg) {
            System.out.println("0:quit 1:insert 2:selectAll 3:selectUnit 4:update 5:deleteAll 6:deleteUnit");
            select = new Scanner(System.in).nextLine();
            switch (select) {
                case "0":
                    System.out.println("quit");
                    flg = false;
                    break;
                case "1":
                    System.out.println("insert");
                    boolean isInsert = jm.insert(ObjectFactoryForConnect.create());
                    System.out.println("isInsert:" + isInsert);
                    break;
                case "2":
                    System.out.println("select all");
                    Map<String, SampleObject> soMap = jm.selectAll();
                    soMap.entrySet().forEach(System.out::println);
                    break;
                case "3":
                    System.out.println("select unit");
                    System.out.println("表示する対象のIDを入力してください。");
                    select = new Scanner(System.in).nextLine();
                    System.out.println(jm.selectUnit(select));
                    break;
                case "4":
                    System.out.println("select update");
                    System.out.println("updateする対象のIDを入力してください。");
                    select = new Scanner(System.in).nextLine();
                    jm.update(select);
                    break;
                case "5":
                    System.out.println("table initialize");
                    jm.deleteAll();
                    break;
                case "6":
                    System.out.println("select delete");
                    System.out.println("updateする対象のIDを入力してください。");
                    select = new Scanner(System.in).nextLine();
                    jm.deleteUnit(select);
                    break;
            }
        }
    }
}
