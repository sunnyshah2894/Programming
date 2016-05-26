package pracks;
import java.io.*;
class node1{
	int data;
	node1 left,right;
	node1(int x){
		left=right=null;
		data=x;
	}
}
class list1{
	node1 first=null;
	public boolean isEmpty(){
		return first==null;
	}
	public void insertAtBeginning(int x){
		node1 n=new node1(x);
		if(isEmpty())
			first=n;
		else{
			n.right=first;
			first.left=n;
			first=n;
		}
	}
	public void insertAtPosition(int x,int pos){
		node1 n=new node1(x);
		int count=pos;
		if(count==0)
			insertAtBeginning(x);
		else{
			node1  p=first;
			while(p!=null && count>1){
				p=p.right;
				count--;
			}
			if(p==null)
				System.out.println("Invalid Position!");
			else{
				n.right=p.right;
				if(p.right!=null)
				p.right.left=n;
				n.left=p;
				p.right=n;
			}
		}
	}
	public void deleteFromPosition(int pos){
		if(isEmpty())
			System.out.println("DDL is Empty!");
		else{
				int count=pos;
				if(count==0){
					System.out.println("\nDeleted: "+first.data);
					first=first.right;
					first.left=null;
				}
				else{
					node1 q=first,p=first;
					while(p!=null & count>0){
					q=p;
					p=p.right;
					count--;
				}
				if(p==null)
					System.out.println("Invalid Position!");
				else{
					System.out.println("\nDeleted: "+p.data);
					q.right=p.right;
					if(p.right!=null)
						p.right.left=q;
				}
			  }
			}
	}
	public void display(){
		node1 p=first;
		System.out.println("");
		while(p!=null){
			System.out.print(p.data+"<==>");
			p=p.right;
		}
	}
	public void search(int x){
		if(isEmpty())
			System.out.println("DLL Is Empty!");
		else{
			node1 p=first;int count=0;
			while(p!=null){
				if(p.data==x){
					System.out.println(x+" is present in DLL at "+ count+" position!");
					return;
				}
				count++;
				p=p.right;
			}
			System.out.println(x+" is Not Present in DLL!");
		}
	}
}
public class doublelLinkedList {
	public static void main(String [] ars){
		list1 l=new  list1();
		l.insertAtBeginning(2);
		l.display();
		l.insertAtBeginning(1);
		l.display();
		l.insertAtPosition(3, 2);
		l.display();
		l.deleteFromPosition(1);
		l.display();
		l.deleteFromPosition(2);
		l.display();
		l.search(3);
		l.search(2);
	}

}
