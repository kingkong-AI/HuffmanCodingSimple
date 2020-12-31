package huffman_implementation;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.util.HashMap;

class HuffmanNode{
	int data;
	char c;
	HuffmanNode left;
	HuffmanNode right;
}
class myComaparator implements Comparator<HuffmanNode>{

	@Override
	public int compare(HuffmanNode o1, HuffmanNode o2) {
		// TODO Auto-generated method stub
		return o1.data-o2.data; //for minHeap
	}
	
}
public class Huffman_Implementation {
	public static void getCodes(HuffmanNode root, String s, HashMap<Character, String> codeMap) {
		if(root.left==null&&root.right==null&&Character.isLetter(root.c)) {
			//System.out.println(root.c+"="+s);
			codeMap.put(root.c, s);
			return;
		}//baseCase
		getCodes(root.left,s+"0",codeMap);
		getCodes(root.right,s+"1",codeMap);
		
	}
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter String");
		String s=sc.next();
		//Generating a map from the character of a string
		HashMap<Character,Integer> map=new HashMap<Character, Integer>();
		for(int i=0;i<s.length();i++) {
			if(map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i),map.get(s.charAt(i))+1);
			}else map.put(s.charAt(i),1);
		}
		System.out.println(map);
		int n=map.size();

		PriorityQueue<HuffmanNode> q=new PriorityQueue<HuffmanNode>(n, new myComaparator());

		for(char ch: map.keySet()) {
			HuffmanNode hn=new HuffmanNode();
			hn.c=ch;
			hn.data=map.get(ch);
			hn.left=null;
			hn.right=null;
			q.add(hn);
			
		}
		//System.out.println(map);
		//Generating a huffmantree for our data
		HuffmanNode root=null;
		while(q.size()>1) {
			HuffmanNode x=q.peek();
			q.poll();
			HuffmanNode y=q.peek();
			q.poll();
			
			HuffmanNode f=new HuffmanNode();
			f.data=x.data+y.data;
			f.c='-';
			
			f.left=x;
			f.right=y;
			root=f;
			q.add(f);
		}//codes saved in form of a tree
		HashMap<Character, String> codeMap=new HashMap<Character, String>();
		getCodes(root,"",codeMap);
		System.out.println(codeMap);
		
		String encoded=encode(s,codeMap);
		System.out.println(encoded);
//		String decoded=decode(encoded,codeMap);
//		System.out.println(decoded);
	}
//	public static String decode(String encoded,HashMap<Character, String> codeMap) {
//		String output="";
//		int j=0;
//		for(int i=0;i<encoded.length();i++) {
//			if(codeMap.containsValue(encoded.substring(j,i))) {
//				output=output+codeMap
//			}
//		}
//		return output;
//	}
	public static String encode(String s, HashMap<Character, String> codeMap) {
		String encoded="";
		for(int i=0;i<s.length();i++) {
			encoded=encoded+codeMap.get(s.charAt(i));
		}
		return encoded;
	}
}
