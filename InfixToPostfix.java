import java.util.HashMap;
import java.util.Stack;
import org.junit.Test;


/* Infix to Postfix conversion
   Supported operator: (),+,-,*,?,%
   Exp:(A+B)*(C+D) -> AB+CD+*
*/
public class InfixToPostfix{
	private HashMap<Character, Integer> precedence = new HashMap<Character,Integer>();
	public InfixToPostfix(){
		precedence.put('(',17);
		precedence.put(')',17);
		precedence.put('+',12);
		precedence.put('-',12);
		precedence.put('*',13);
		precedence.put('/',13);
		precedence.put('%',13);
	}
	
	public String solution(String infix){
		

		Stack<Character> st = new Stack<Character>();
		StringBuilder postfix = new StringBuilder();
		for(int i = 0; i<infix.length(); i++){
			if(!precedence.containsKey(infix.charAt(i))){
				// not the operator
				postfix.append(infix.charAt(i));
			}
			else{
				if(st.isEmpty() || st.peek()=='('){
					st.push(infix.charAt(i));
					continue;
				}
				if(infix.charAt(i)==')'){
					//remove element until meet '('
					while(st.peek()!='('){
						postfix.append(st.pop());
					}
					st.pop();
					continue;
				}
				if( precedence.get(st.peek())!=17 && precedence.get(st.peek())>=precedence.get(infix.charAt(i))){
					//pop the peak, push the new opertor
					// notice that '(' is not for prioirty comparison, because we take special care for this
					// if prioity is the same, print first one
					postfix.append(st.pop());
				}
				st.push(infix.charAt(i));				
			
				
			}
		}
		while(!st.isEmpty()){
			postfix.append(st.pop());
		}
		return postfix.toString();
	}
	
	public int calPostfix(String postfix){
		// drawbacks: only process integers
		int opt1=0;
		int opt2=0;
		Stack<String> st = new Stack <String> ();
		for(int i = 0;i<postfix.length();i++){
			if(precedence.containsKey(postfix.charAt(i))==false){
				st.push(postfix.charAt(i)+"");
			}
			else{
				opt1 = Integer.parseInt(st.pop());//second operator
				opt2 = Integer.parseInt(st.pop());//first operator, 注意顺序！
				if(postfix.charAt(i)=='+'){
					opt1 = opt1+opt2;
				}
				else if(postfix.charAt(i)=='-'){
					opt1 = opt2-opt1;
				}
				else if(postfix.charAt(i)=='*'){
					opt1 = opt1*opt2;
				}
				else if(postfix.charAt(i)=='/'){
					opt1 = opt2/opt1;
				}
				else{
					opt1 = opt2%opt1;
				}
				st.push(Integer.toString(opt1));
			}
		}
		return opt1;
	}
	@Test
	public void test(){
		String a = "(2-3)*(9/3)";
		String b = solution(a);
		System.out.println(b);
		System.out.println(calPostfix("123*+5-"));
	}
}