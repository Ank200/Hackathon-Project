package baseClass;

import java.util.HashSet;

public class set {

	public static void main(String[] args) {
		HashSet<Character> set= new HashSet<>();
		String s= "aniket kamal";
		for(int i=0; i<s.length(); i++){
			if(!set.contains(s.charAt(i))){
				set.add(s.charAt(i));
			}
			else
				System.out.println(s.charAt(i));
		}
	}

}
