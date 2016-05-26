package pracks;
import java.io.*;
class mode{
	mode next;
    int data;
    mode(int x){
    	data=x;
    }
}
class circular{
	int count;
	mode first;
	circular(){
		count=0;first=null;
	}
	public void insertAtBeginning(int x){
		mode n=new mode(x);
		count++;
		if(first==null){
			first=n;
			n.next=first;
		}
		else{
			n.next=first;
			first=n;
		}
	}
	public void insertAtPosition(int x,int pos){
		int c=pos;
		int i=count;
		mode n=new mode(x);
		if(c==0)
			insertAtBeginning(x);
		else{
			mode p=first;
			if(c<=i && c>0)
			{
				while(c>1)
				{
					p=p.next;
					c--;
				}
				n.next=p.next;
				p.next=n;
				count++;
			}
			else
				System.out.println("Invalid Position!");
		}
	}
	public void deleteFromPosition(int pos){
		if(first==null)
			System.out.println("CLL Is Empty!");
		else{
			int c=pos,i=count;
			if(c==0)
			{	System.out.println("\nDeleted: "+first.data);
				first=first.next;
				count--;
			}
			else{
				if(c>=0 && c<i)
				{
					mode q=first,p=first;
					while(c>0){
						q=p;
						p=p.next;
						c--;
					}
					System.out.println("\nDeleted: "+p.data);
					q.next=p.next;
					count--;
				}
				else{
					System.out.println("Invalid Position!");
				}
			}
		}
	}
	public void display(){
		mode p=first;
		System.out.println("");
		int i=count;
		while(i>0){
			System.out.print(p.data+"-->");
			p=p.next;
			i--;
		}
	}
	
}
public class circularLinkedList {

	public static void main(String []ars){
		circular c=new circular();
		c.insertAtBeginning(2);
		c.insertAtBeginning(1);
		c.insertAtPosition(5, 1);
		c.display();
		c.deleteFromPosition(2);
		c.display();
	}
}
