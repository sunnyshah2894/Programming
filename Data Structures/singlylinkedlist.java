package pracks;
import java.io.*;
class node{
	int data;
	node next;
	node(int data){
		next=null;
		this.data=data;
	}
}
class list{
	node first;
	public boolean isEmpty(){
		return first==null;
	}
	public void insertAtBeginning(int x){
		node n=new node(x);
		if(isEmpty())
			first=n;
		else{
			n.next=first;
			first=n;
		}
	}
	public void insertAtPosition(int x,int pos){
		node n=new node(x);
		int count=pos;
		node p=first;
		if(count==0)
			insertAtBeginning(x);
		else{
			while(p!=null && count>1){
		           p=p.next;
		           count--;
			}
			if(p==null)
				System.out.println("Invalid Position!");
			else{
				n.next=p.next;
				p.next=n;
			}
		}
	}
	public void deleteFromPosition(int pos){
		int deleted;
		if(isEmpty())
			System.out.println("Linked List Is Empty!");
		else{
			int count=pos;
			if(count==0){
				System.out.println("\nDeleted: "+first.data);
				first=first.next;
			}
			else{
				node q=first,p=first;
				while(p!=null && count>0){
					q=p;
					p=p.next;
					count--;
				}
				if(p==null)
					System.out.println("Invalid Position!");
				else{
					System.out.println("\nDeleted: "+p.data);
					q.next=p.next;
				}
			}
		}
	}
	public void display(){
		node p=first;
		System.out.println("");
		while(p!=null){
			System.out.print(p.data+"-->");
			p=p.next;
		}
	}
}
public class singlylinkedlist {
   public static void main(String []sre){
	   list l=new list();
	   l.insertAtBeginning(1);
	   l.display();
	   l.insertAtBeginning(2);
	   l.display();
	   l.insertAtPosition(3, 0);
	   l.display();
	   l.deleteFromPosition(2);
	   l.display();
	   l.deleteFromPosition(0);
	   l.display();
   }
}
