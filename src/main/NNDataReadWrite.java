/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author rajesh
 */
public class NNDataReadWrite {
    static String data;
    	 public static void main(String[] args) {
            
		try {
			FileReader reader = new FileReader("/Users/rajesh/Documents/Masters-Edu./AI/project/AIFinalProject/NNData.txt");
			FileWriter writer = new FileWriter("/Users/rajesh/Documents/Masters-Edu./AI/project/AIFinalProject/NNinput.txt",true);
                        BufferedWriter bufferedWriter = new BufferedWriter(writer);
                        int i;
			while ((i = reader.read()) !=-1) {
                            
                            if(i==84)
                            {
                                data = "TASTE";
                                bufferedWriter.write(data);
                                bufferedWriter.newLine();
                                
                            }
                            if(i==80)
                            {
                                data = "PRICE";
                                bufferedWriter.write(data);
                                bufferedWriter.newLine();
                               
                            }
                            System.out.println(data);
                            
                           
			}
			reader.close();
                        bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
