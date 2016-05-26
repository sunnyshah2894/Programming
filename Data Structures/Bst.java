package pracks;
import java.io.*;
class node2{
	node2 left;
	node2 right;
	node2 parent;
	int data;
	node2(int x){
		data=x;
		left=null;right=parent=null;
	}
}
class tree{
	node2 root;
	tree(){
		root=null;
	}
	public void insert(int x)
	{
		node2 n=new node2(x);
		if(root==null)
			root=n;
		else{
			node2 p=root;boolean duplicate=false;
			node2 q=root;
			while(p!=null){
				q=p;
				if(n.data<p.data)
					p=p.left;
				else if(n.data==p.data){
					duplicate=true;
					break ;
					}
				else
					p=p.right;
			}
			if(duplicate)
				System.out.println("Number Already Exists!");
			else{
				n.parent=q;
				if(n.data>q.data)
					q.right=n;
				else
					q.left=n;
			}
		}
	}
	public void delete(int x){
			
			node2 n=new node2(x);
			node2 replacement=null;
			node2 p=root;
			while(p!=null && p.data!=x){
				if(p.data>x)
					p=p.left;
				else p=p.right;
			}
			if(p==null)
				System.out.println(x+ " is not Present in the Tree!");
			else{
				if(p.left==null && p.right==null)
					replacement=null;
				if(p.left==null && p.right!=null)
					replacement=p.right;
				if(p.left!=null && p.right==null)
					replacement=p.left;
				if(p.left!=null && p.right!=null){
					replacement=p.right;
					while(replacement.left!=null){
						replacement=replacement.left;
					}
					if(replacement.parent!=p){
						replacement.parent.left=replacement.right;
						replacement.right=p.right;
					}
					replacement.left=p.left;
					}
					if(p!=root){
						if(p.data<p.parent.data)
							p.parent.left=replacement;
						else
							p.parent.right=replacement;
					}
					else root=replacement;
				}
		}
	
	public boolean search(int x){
		node2 p=root;
		boolean present=false;
		while(p!=null){
			if(x<p.data)
				p=p.left;
			else if(x==p.data){
				present=true;
				break;
			}
			else
				p=p.right;
		}
		return present;
	}
	public void inorder(node2 n){
		if(n==null){
			 return;
		}
		else{
			
			inorder(n.left);
			System.out.print(n.data+" ");
			inorder(n.right);
		}
	}
}
public class Bst {
	public static void main(String []ard){
		tree b=new tree();
		b.insert(5);
		b.insert(2);
		b.insert(7);
		b.insert(6);
		b.insert(1);
		b.insert(9);
		b.delete(8);
		System.out.println(b.search(8));
		b.inorder(b.root);
	}
}
