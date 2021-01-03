package listArray;
import java.io.*;
import java.util.Scanner;
class Polynomial{
	class Term{
		int coef;
		int exp;
		Term link;
	}
	
	private Term head; // 첫번쨰 항을 가리키는 레퍼런스 변수
	private Term tail; // 마지막 항을 가리키는 레퍼런스 변수
	
	public Polynomial() {
		head = tail = null;
	}
	
	public void addTerm(int coef, int exp) {
		Term term = new Term();
		term.coef = coef;
		term.exp = exp;
		term.link = null;
		
		if(head == null) {
			head = tail = term;
			} else {
			tail.link = term;
			tail = term;
		}
	}
	//String result;
	public String toString() {
		String result = "0";
		for(;result == "0" && head != null;) {
			if(head.coef == 0) {
				head = head.link;
			}else if(head.coef != 0) {
				if(head.exp == 0) {
					result = head.coef + "";
					head = head.link;
				}else if(head.exp == 1) {
					if(head.coef ==1) {
						result = "x";
					}else if(head.coef == -1){
						result = "-x";
					}else {
						result = head.coef + "x";
					}
					head = head.link;
				}else {
					if(head.coef == 1) {
						result = "x^" + head.exp;
					}else if(head.coef == -1) {
						result = "-x^" + head.exp;
					}else {
						result = head.coef + "x^" + head.exp;
					}
					head = head.link;
				}
			
			}
		}
		for(;head != null;) {
			if(head.coef == 0 ) {
				head = head.link;
			}else if(head.coef < 0) {
				if(head.exp == 1) {
					if(head.coef == -1) {
						result = result + "-x";
						head = head.link;
					}else {
						result = result + head.coef + "x";
					}
				}else if(head.exp == 0) {
					result = result + head.coef;
					head = head.link;
				}else {
					if(head.coef == -1) {
						result = result + "-x^" + head.exp;
						head = head.link;
					}else {
						result = result + head.coef + "x^" + head.exp;
						head = head.link;
					}
				}
			}else if(head.coef > 0) {
				if(head.exp == 1) {
					if(head.coef == 1) {
						result = result + "+" + "x";
						head = head.link;
					}else {
						result = result + "+" + head.coef + "x";
						head = head.link;
					}
				}else if(head.exp == 0) {
					result = result + "+" + head.coef;
					head = head.link;
				}else {
					if(head.coef ==1) {
						result = result + "+" + "x^" + head.exp;
						head = head.link;
					}else {
						result = result + "+" + head.coef + "x^" + head.exp;
						head = head.link;
					}
				}
			}
			
		}
		return result;
	}
	
	public static Polynomial polyAdd(Polynomial p1, Polynomial p2) {
		Polynomial np = new Polynomial();
		if(p1.head.exp > p2.head.exp) {
			np.head = p1.head;
			np.tail = p1.head;
			p1.head = p1.head.link;
		}else if(p1.head.exp < p2.head.exp) {
			np.head = p2.head;
			np.tail = p2.head;
			p2.head = p2.head.link;
		}else if(p1.head.exp == p2.head.exp) {
			np.head = p1.head;
			np.head.coef = p1.head.coef + p2.head.coef;
			np.tail = p1.head;
			p1.head = p1.head.link;
			p2.head = p2.head.link;
		}
		for(; p1.head != null && p2.head != null;) {
			if(p1.head.exp > p2.head.exp) {
				np.tail.link = p1.head;
				np.tail = p1.head;
				p1.head = p1.head.link;
			}else if(p1.head.exp < p2.head.exp) {
				np.tail.link = p2.head;
				np.tail = p2.head;
				p2.head = p2.head.link;
			}else if(p1.head.exp == p2.head.exp) {
				np.tail.link = p1.head;
				np.tail.link.coef = p1.head.coef + p2.head.coef;
				np.tail = p1.head;
				p1.head = p1.head.link;
				p2.head = p2.head.link;
				
			}
		}
		if(p1.head == null) {
			np.tail.link = p2.head;
		}else if(p2.head == null) {
			np.tail.link = p1.head;
		}
		
		
		return np;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		Polynomial p1 = new Polynomial();
		int no = scan.nextInt();
		for(int i = 0; i< no; i++) {
			int coef = scan.nextInt();
			int exp = scan.nextInt();
			p1.addTerm(coef,exp);
		}
		
		Polynomial p2 = new Polynomial();
		int po = scan.nextInt();
		for(int i = 0; i< po ; i++) {
			int coef = scan.nextInt();
			int exp = scan.nextInt();
			p2.addTerm(coef,exp);
		}
		
		Polynomial p3 = Polynomial.polyAdd(p1,p2);
		//scan.close();
		
		System.out.print(p3);
	}

}
