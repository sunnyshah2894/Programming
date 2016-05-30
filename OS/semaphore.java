import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.Semaphore;

class producer extends Thread{
	Semaphore sun;
	int buffer[];
	producer(Semaphore s,int []buf){
		sun=s;
		buffer=buf;
	}
	public void run(){
		Random r=new Random();
		int index=0;
		while(true){
			try{
				sun.acquire();
				index = r.nextInt(buffer.length);
				if(buffer[index]==0){
					System.out.println("Producer produced "+(index+1));
					buffer[index]=1;
				}
				else
					System.out.println("Producer cannot produce "+(index+1)+" as Buffer is not empty");
				sun.release();
				Thread.sleep(100);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}

class consumer extends Thread{
	Semaphore sun;
	int buffer[];
	consumer(Semaphore s,int []buf){
		sun=s;
		buffer=buf;
	}
	public void run(){
		Random r=new Random();
		int index=0;
		while(true){
			try{
				sun.acquire();
				index = r.nextInt(buffer.length);
				if(buffer[index]!=0){
					System.out.println("Consumer Consumed "+(index+1));
					buffer[index]=0;
				}
				else
					System.out.println("Consumer cannot consume "+(index+1)+" as Buffer is empty");
				sun.release();
				Thread.sleep(100);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}

public class semaphore {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter The Buffer Size: ");
		int n=Integer.parseInt(br.readLine());
		int buf[]=new int[n];
		Semaphore s=new Semaphore(1,true);
		producer p=new producer(s, buf);
		consumer c=new  consumer(s,buf);
		p.start();
		c.start();
	}
}
