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
			ret.append(first);
			first = first.next;
		}
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
		return null;
	}

	@Override
	public IDnaStrand reverse() {
		Node cur = myFirst;
		Node n = new Node(myFirst.info);
		cur = cur.next;
		while(cur.next != null) {
			Node temp = new Node(cur.info);
			temp.next = n;
			n = temp;
			cur = cur.next;
		}
		Node first = n;
		while(n.next != null) {
			StringBuilder tempsb = new StringBuilder(n.info);
			tempsb.reverse();
			n.info = tempsb.toString();
			n = n.next;
		}
		LinkStrand ret = new LinkStrand(first.info);
		first = first.next;
		while(first != null) {
			ret.append(first.info);
			first = first.next;
		}
		return ret;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		int count = myIndex;
		int dex = myLocalIndex;
		Node list = myFirst;
		int listcount = 0;
		while(listcount < myCurrent) {
			list = list.next;
			listcount += 1;
		}
		while (count != index) {
			count++;
			dex++;
			myLocalIndex = dex;
			if (dex >= list.info.length()) {
				dex = 0;
				list = list.next;
				myCurrent += 1;
			}
		}
		myIndex = index;
		return list.info.charAt(dex);
	}


}
