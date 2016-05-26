package pracks;
import java.io.*;
class que{
	int ar[];
	int front,rear;
	public boolean isEmpty(){
		return ((rear+1)==front);
	}
	public boolean isFull(){
		return rear==(ar.length-1);
	}
	que(int capacity){
		ar=new int[capacity];
		front=0;
		rear=-1;
	}
	public void enqueue(int x){
		if(isFull())
			System.out.println("Queue Is Full!");
		else{
			int temp=x;
			int j=rear;
			while(j>=front && ar[j]<temp){
				ar[j+1]=ar[j];
				j--;	
			}
			j++;
			ar[j]=temp;
			rear++;
		}
	}
	public void dequeue(){
		if(isEmpty())
			System.out.println("Queue Is Empty!");
		else{
			System.out.println("\nDeleted: "+ar[front++]);
		}
 }
	public void display(){
		System.out.println("");
		for(int i=front;i<=rear;i++){
			System.out.print(ar[i]+" ");
		}
	}
}
public class priorityqueue {
 public static void main(String [] ar){
	 que q=new que(20);
	 q.enqueue(1);
	 q.enqueue(2);
	 q.display();
	 q.dequeue();
	 q.enqueue(45);
	 q.enqueue(3);
	 q.enqueue(6);
	 q.display();
	 q.dequeue();
	 q.dequeue();q.dequeue();q.dequeue();q.dequeue();
	 q.display();
 }
}
