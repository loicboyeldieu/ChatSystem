import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CommunicaSystem {
	
	public static void main(String args[]){
		
		
		try {
			FileWriter fwA2B=new FileWriter("A2B.txt");
			FileWriter fwB2A=new FileWriter("B2A.txt");
			
			FileReader frA2B=new FileReader("A2B.txt");
			FileReader frB2A=new FileReader("B2A.txt");
			
			BufferedReader brA2B=new BufferedReader(frA2B);
			BufferedReader brB2A=new BufferedReader(frB2A);
			
			BufferedWriter bwA2B=new BufferedWriter(fwA2B);
			BufferedWriter bwB2A=new BufferedWriter(fwB2A);
			
			Communica communicaA=new Communica(bwA2B,brB2A);
			Communica communicaB=new Communica(bwB2A,brA2B);
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
		
		
	}

}
