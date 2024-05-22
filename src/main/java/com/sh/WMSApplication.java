package com.sh;

import com.sh.view.InWarehousingView;
import com.sh.view.MemberView;

import java.util.Scanner;

public class WMSApplication {

    public static boolean EX = false;

    private static Scanner scanner = new Scanner(System.in);
    public static Integer PUB_MANAGER_ID = 0;
    public static Integer PUB_ID = 0;
    public static Integer INVEN_MANAGER_ID  = 0;

    public static void main(String[] args) {
        MemberView memberView = new MemberView();
        memberView.mainMenu();
        System.out.println("✨WMS 프로그램 종료되었습니다.✨");
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
