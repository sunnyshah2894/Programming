import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class filecopy {
	public static void main(String[] args)throws IOException {
		File f1=new File("s.txt");
		File f2=new File("d.txt");
		FileReader x = new FileReader(f1);
		FileWriter y=new FileWriter(f2);
		BufferedReader br1=new BufferedReader(x);
		BufferedWriter br2=new BufferedWriter(y);
		String str;
		try{
		while((str=br1.readLine())!=null){
			br2.write(str+"\n");
		}
		}
		catch(Exception e){
			
		}
		finally{
			br1.close();
			br2.close();
		}
	}
}
