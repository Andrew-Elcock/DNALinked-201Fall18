public class LinkStrand implements IDnaStrand {
	private int myIndex = 0;
	private int myLocalIndex = 0;
	private Node myCurrent;
	
	/*
	 * A sub-class that initializes the linked
	 * list structure necessary for the 
	 * class
	 */
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;

	/*
	 * A constructor without a parameter
	 * that, when called, initializes a
	 * LinkStrand with parameter ""
	 */
	public LinkStrand() {
		this("");
	}

	/*
	 * A constructor that initializes LinkStrand
	 * with the parameter passed to it
	 * @see initialize
	 */
	public LinkStrand(String param) {
		initialize(param);
		
	}

	/*
	 * @return the size of the LinkStrand
	 */
	@Override
	public long size() {
		return mySize;
	}

	/*
	 * Reverts the instance variables
	 * of the LinkStrand to the default state
	 * when given the info for the first node
	 */
	@Override
	public void initialize(String source) {
		Node v = new Node(source);
		myFirst = v;
		myLast = v;
		myAppends = 0;
		mySize = source.length();
		myCurrent = myFirst;
		myIndex = 0;
		myLocalIndex = 0;

	}
	
	/*
	 * Changes a given linked list into a
	 * concatenated String by creating a StringBuilder
	 * class
	 */
	public String toString() {
		Node first = myFirst;
		StringBuilder ret = new StringBuilder();
		while(first.next != null){
			ret.append(first.info);
			first = first.next;
		}
		ret.append(first.info);
		return ret.toString();
	}

	/*
	 * Creates a new LinkedStrand with the given
	 * String
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	/*
	 * Appends a node to the end
	 * of the linked list present within
	 * the LinkStrand class
	 * @return this
	 */
	@Override
	public IDnaStrand append(String dna) {
		Node n = new Node(dna);
		myLast.next = n;
		myLast = myLast.next;
		myAppends += 1;
		mySize += dna.length();
		return this;
	}
	
	/*
	 * Reverses a linked list and 
	 * the string present within 
	 * each node of the linked list
	 * before returning a new linked strand
	 * consisting of that linked list
	 * @return reversed LinkStrand 
	 */
	@Override
	public IDnaStrand reverse() {
		Node cur = myFirst;
		Node head = myFirst;
		Node n = new Node("");
		if(cur.next == null) {
			StringBuilder tempsb = new StringBuilder(cur.info);
			n.info = tempsb.reverse().toString();
		}
		while(cur.next != null) {
			StringBuilder tempsb = new StringBuilder(cur.info);
			Node run = new Node(tempsb.reverse().toString());
			run.next = n;
			n = run;
			cur = cur.next;
		}
		if(head.next != null) {
			StringBuilder tempsb = new StringBuilder(cur.info);
			Node n2 = new Node(tempsb.reverse().toString());
			n2.next = n;
			n = n2;
		}
		LinkStrand ret = new LinkStrand(n.info);
		if(n.next != null) {
			n = n.next;
		}
		while(n.next != null) {
			ret.append(n.info);
			n = n.next;
		}
		return ret;
	}	
	
	/*
	 * @return the number of appends
	 * on the LinkStrand
	 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}

	/*
	 * Finds a character at a specific index
	 * Altered so that finding a subsequent character
	 * is more efficient by starting when the last call
	 * left off
	 * @return desired character
	 */
	public char charAt(int index) {
		if(index >= mySize) {
			throw new IndexOutOfBoundsException("Index Out of Bounds");
		}
		if(index < myIndex) {
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
		while (myIndex != index) {
			myIndex++;
			myLocalIndex++;
			if (myLocalIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				if(myCurrent.next == null) {
					throw new IndexOutOfBoundsException("Index Out of Bounds");
				}
				myCurrent = myCurrent.next;
			}
		}
        return myCurrent.info.charAt(myLocalIndex);
        }
}
