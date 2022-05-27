/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) {
        if (args.length == 2) {
            System.out.println(new SfisAPI().sendToSFIS(args[0], args[1]));
        }
    }
}
