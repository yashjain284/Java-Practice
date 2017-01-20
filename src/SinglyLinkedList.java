import java.util.HashMap;

//Note that Singly Linked Lists does not make use if previous object.
public class SinglyLinkedList 
{
	public static class Node{
		int data;
		Node next;
		Node(int data)
		{
			this.data = data;
			this.next = null;
		}
		public String toString()
		{
			return data+"";	
		}
	}
	
	Node head = null;
	Node tail = null;
	
	
	public boolean isEmpty()
	{
		if(head == null && tail == null)
			return true;
		return false;
		
	}
	
	public void addToStart(int data)
	{
		//Create New Node
		Node temp = new Node(data);
		
		//Case 1 : If Linked List is Empty
		if (isEmpty())
		{
			head = tail = temp;
		}
		//Case 2 : If Linked list is not empty
		else
		{
			temp.next = head;
			head = temp;
		}
	
	}
	
	public void addToEnd(int data)
	{
		//Create New Node
		Node temp = new Node(data);
		
		//Case 1 : If Linked List is Empty
		if(isEmpty())
		{
			head = tail = temp;
		}
		
		//Case 2 : If Linked List is not empty
		{
			tail.next = temp;
			tail = temp;
		}
	}
	
	public boolean deleteHead()
	{
		//Case 1 : When linked List is empty
		if(isEmpty())
		{
			return false;
		}
		//Case 3 : When linked list has only 1 element
		else if(head == tail)
		{
			head = tail = null;
		}
		//Case 2 : When linked list has more than 1 element
		else
		{
			head = head.next;
		}
		return true;
	}
	
	public boolean deleteTail()
	{
		//Case 1 : When linked List is empty
		if(isEmpty())
		{
			return false;
		}
		//Case 3 : When linked list has only 1 element
		else if(head == tail)
		{
			head = tail = null;
		}
		//Case 2 : When linked list has more than 1 element
		else
		{
			Node temp = head;
			while(temp.next != tail)
			{
				temp = temp.next;
			}
			temp.next = null;
			tail=temp;
		}
		return true;
	}
	
	public boolean delete(int x)
	{
		//Case 1 : When linked List is empty
		if(isEmpty())
		{
			return false;
		}
		//Case 2 : When the tail element is the one to be deleted
		else if(tail.data == x)
		{
			System.out.println("FOund at Tail");
			this.deleteTail();
		}
		//Case 3 : When the head element is the one to be deleted
		else if(head.data == x)
		{


			System.out.println("FOund at Head");
			this.deleteHead();
		}
		//Case 4 : When the element to be deleted is in the middle
		else
		{
			Node search = find(x);
			if(search == null)
			{
				return false;
			}
			//Node is found somewhere in the middle and is returned
			else
			{
				Node temp = head;
				while(temp.next != search)//Loop till the element before the "searched" element is found
				{
					temp = temp.next;
				}
				temp.next = search.next;
			}
		}
		System.out.println(this.printList());
		return true;
	}
	
	public Node find(int x)
	{
		//Case 1 : When list is empty
		if(isEmpty())
		{
			return null;
		}
		//Case 2 : When list is non empty
		else
		{
			Node temp = head;
			do
			{
				if(temp.data == x)
				{
					return temp;
				}
				temp = temp.next;
			}while(temp != null);
			
			return null;
		}
	}
	
	public String printList()
	{
		if(isEmpty())
		{
			return "Linked List is Empty";
		}
		else
		{
			String ll = "";
			Node temp = head;
			while(temp != null)
			{
				ll = ll+ "->" +temp.data;
				temp = temp.next;
			}
			return ll;
		}
	}

	//Uses the concept of a runner. Running Time is O(n^2)
	public void removeDuplicate()
	{
		Node current = head;
		Node runner  = null;
		while(current != null)
		{
			runner  = current;
			while(runner.next != null)// Stop at the last node
			{
				if(current.data == runner.next.data)
				{
					Node deletedNode = runner.next;
					runner.next = runner.next.next;
					System.out.println("Removed" + deletedNode.data);
				}
				else
				{
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}
	
	public int kthLastElement(int k)
	{
		Node current = head;
		Node runner  = current;
		
		for(int i = 1;i< k;i++)
		{
			runner = runner.next;
		}
		
		while(runner.next!= null)
		{
			current = current.next;
			runner = runner.next;
		}
		
		return current.data;
	}
	
	//This an easy implementation to check if a linked List is palindrome or not (But it involves creating various functions like deleting head and tail which might
	public boolean palindrome()
	{
		SinglyLinkedList x = this;
		while(!x.isEmpty())
		{
			if(x.head.data != x.tail.data)
				return false;
			else if(head == tail)
			{
				return true;
			}
			else
			{
				x.deleteHead();
				x.deleteTail();
			}
		}
		return true;
	}
	
	//Following is  a program to check if Linked list is palindrome or not (USING RECURSION)
	Node left;
	public boolean checkPalin(Node right)
	{
		left = head;
		if(right == null)//Call stack is empty
		{
			return true;
		}
		
		boolean x = checkPalin(right.next);
		if(!x)
		{
			return false;
		}
		if(left.data != right.data)
		{
			return false;
		}
		left = left.next;
		return true;
	}
	
	public SinglyLinkedList findSum(SinglyLinkedList a, SinglyLinkedList b)
	{
		
		SinglyLinkedList result = new SinglyLinkedList();
		if(a.isEmpty()&& !b.isEmpty())
		{
			return b;
		}
		else if(!a.isEmpty()&& b.isEmpty())
		{
			return a;
		}
		else if(a.isEmpty() && b.isEmpty())
		{
			return null;
		}
		else
		{
			Node aptr = a.head;
			Node bptr = b.head;
			int carry = 0;
			int sum = 0;
			while(aptr!=null || bptr !=null)
			{
				sum =carry;
				sum += (aptr!=null)?aptr.data:0;
				sum += (bptr!=null)?bptr.data:0;
				carry = (sum>=10)?1:0;
				sum = sum % 10;
				System.out.println(sum +" "+carry);
				result.addToEnd(sum);
				aptr = (aptr!=null)?aptr.next:null;
				bptr = (bptr!=null)?bptr.next:null;
			}
			if(carry > 0)
			{
				result.addToEnd(carry);
			}
			return result;
		}
	}

	public SinglyLinkedList partition(Node head, int pivot)
	{
		Node current = head;
		SinglyLinkedList result1 = new SinglyLinkedList();
		SinglyLinkedList result2 = new SinglyLinkedList();
		
		if(head == null)
		{
			return null;
		}
		
		while(current!=null)
		{
			if(current.data < pivot)
			{
				result1.addToEnd(current.data);

			}
			else
			{
				result2.addToEnd(current.data);
			}
			current = current.next;
		}
		result1.tail.next = result2.head;
		result1.tail = result2.tail;
		return result1;
	}
	
	public Node intersection(Node a, Node b)
	{
		Node aptr = a;
		Node bptr = b;
		HashMap<Node,Integer> occ = new HashMap<Node,Integer>();
		while(aptr != null)
		{
			occ.put(aptr,1);
			aptr = aptr.next;
		}
		while(bptr != null)
		{
			boolean x = occ.containsKey(bptr);
			if(x)
			{
				System.out.println(x);
				return bptr;
			}
			bptr = bptr.next;
		}
		return null;
		
		
	}
	
	public Node checkLoop(Node a)
	{
		Node aptr = a;
		HashMap<Node,Integer> occ = new HashMap<Node,Integer>();
		if(a == null)
		{
			return null;
		}
		while(aptr != null)
		{
			if(occ.containsKey(aptr.next))
			{
				return aptr.next;
			}
			occ.put(aptr, 0);
			
			aptr = aptr.next;
		}
		return null;
		
	}
	
	public Node reverse(Node current, Node previous)
	{
		if(current.next == null) //i.e we have reached the last node
		{
			head = current; //Last node will be the head obviously.
			current.next = previous; // Reverse the direction by making the last node point ot the previous one
			return null;
		}
		
		Node temp = current.next; //Store the next node in a temporary node
		current.next = previous; //Make the current node point to previous node (We are changing the directions here)
		reverse(temp,current); //Note that : The current will be the previous in the next recursive call
		return head;
	}
	
	public SinglyLinkedList reverseWithoutPointers()
	{
		if(isEmpty())//List is Empty
		{
			return null;
		}
		if(head.next == null) //Only 1 element is present
		{
			return this;
		}
		
		SinglyLinkedList a = new SinglyLinkedList();
		Node current = head;
		while(current != null)
		{
			a.addToStart(current.data);
			current = current.next;
		}
		return a;
	}
	
	public Node deleteNode(Node x)
	{
		Node temp;
		if(isEmpty() || x == null)
		{
			return null;
		}
		if(x==head)
		{
			temp = head;
			head = head.next;
			return temp;
		}
		Node current = head;
		while(current != null)
		{
			if(current.next == x)
			{
				temp = current.next;
				current.next = current.next.next;
				temp.next = null;
				return temp;
			}
			current = current.next;
		}
		return null;
	}
	

	public static void main(String[] args)
	{
		SinglyLinkedList x = new SinglyLinkedList();
		x.addToEnd(3);
		x.addToEnd(5);
		x.addToEnd(8);
		x.addToEnd(9);
		x.addToEnd(10);
		
		System.out.println(x.printList());
		SinglyLinkedList y = x.reverseWithoutPointers();
		System.out.println(y.printList());
		
	}

}
