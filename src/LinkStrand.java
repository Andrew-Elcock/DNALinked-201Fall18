public class LinkStrand implements IDnaStrand {
	private int myIndex = 0;
	private int myLocalIndex = 0;
	private int myCurrent = 0;
	
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

	public LinkStrand() {
		this("");
	}

	public LinkStrand(String param) {
		initialize(param);
		
	}


	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		Node v = new Node(source);
		myFirst = v;
		myLast = v;
		myAppends = 0;
		mySize = source.length();

	}
	
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

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		Node n = new Node(dna);
		myLast.next = n;
		myLast = myLast.next;
		myAppends += 1;
		mySize += dna.length();
		return this;
	}

//	@Override
//	public IDnaStrand reverse() {
//		Node cur = myFirst;
//		StringBuilder tempsb = new StringBuilder(myFirst.info);
//		tempsb.reverse();
//		Node n = new Node(tempsb.toString());
//		if(cur.next != null) {
//			cur = cur.next;
//		}
//		while(cur.next != null) {
//			Node temp = new Node(cur.info);
//			temp.next = n;
//			StringBuilder tempsb2 = new StringBuilder(temp.info);
//			tempsb2.reverse();
//			temp.info = tempsb2.toString();
//			n = temp;
//			cur = cur.next;
//		}
//		Node first = n;
//		LinkStrand ret = new LinkStrand(first.info);
//		if(first.next != null) {
//			first = first.next;
//		}
//		while(first.next != null) {
//			ret.append(first.info);
//			first = first.next;
//		}
//		//ret.append(first.info);
//		return ret;
//	}


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
	
	@Override
	public int getAppendCount() {
		return myAppends;
	}

//	@Override
//	public char charAt(int index) {
//		int count = myIndex;
//		int dex = myLocalIndex;
//		Node list = myFirst;
//		int listcount = 0;
//		while(listcount < myCurrent) {
//			list = list.next;
//			listcount += 1;
//		}
//		while (count != index) {
//			count++;
//			dex++;
//			myLocalIndex = dex;
//			if (dex >= list.info.length()) {
//				dex = 0;
//				list = list.next;
//				myCurrent += 1;
//			}
//		}
//		myIndex = index;
//		return list.info.charAt(dex);
//	}
	
	public char charAt(int index) {
		int count = 0;
		int dex = 0;
		Node list = myFirst;
		while (count != index) {
			count++;
			dex++;
			if (dex >= list.info.length()) {
				dex = 0;
				list = list.next;
			}
		}
		return list.info.charAt(dex);
	}
		


}
