package pracks;
import java.io.*;
class Q{
	int rear=-1;
	int front=0;
	int ar[];
	int count=0;
	int size;
	Q(int capacity){
		size=capacity;
		ar=new int[capacity];
	}
	public void dequeue(){
		if(isEmpty()){
			System.out.println("Queue is Empty!");
			
		}
		else{
			System.out.print("\nDELETED: "+ ar[front]);
			front=(front+1)%size;
			count--;
			
		}
	}
	public void enqueue(int data){
		if(isFull())
			System.out.println("\nQueue is Full! cannot add "+ data);
		else{
			rear=(rear+1)%size;
			ar[rear]=data;
			count++;
		}
	}
	public boolean isEmpty(){
		return count==0;
	}
	public boolean isFull(){
		return count==size;
	}
	public void display(){
		System.out.println("\nQUEUE DISPLAY");
		for(int i=0;i<count;i++){
			System.out.print(ar[(front+i)%size]+"  ");
		}
	}
}
public class circularq {
  public static void main(String []atr)throws IOException{
	  
	  Q q=new Q(5);
	  q.enqueue(1);
	  q.enqueue(2);
	  q.enqueue(3);
	  q.display();
	  q.dequeue();
	  q.dequeue();
	  q.display();
	  q.enqueue(4);
	  q.enqueue(5);
	  q.enqueue(6);
	  q.enqueue(7);
	  q.enqueue(8);
	  q.display();
  }
}
