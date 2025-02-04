import programmers.TestUtil;

/*
문제 설명
괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다. 예를 들어

"()()" 또는 "(())()" 는 올바른 괄호입니다.
")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.
'(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때, 문자열 s가 올바른 괄호이면 true를 return 하고, 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.

제한사항
문자열 s의 길이 : 100,000 이하의 자연수
문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.

s	answer
"()()"	true
"(())()"	true
")()("	false
"(()("	false
*/

class ValidBrackets {
    boolean solution(String s) {
        int stack = 0;

        for (int i = 0; i < s.length(); i++) {
            stack += s.charAt(i) == '(' ? 1 : -1;
            if (stack < 0) return false;
        }

        return stack == 0;
    }

    public static void main(String[] args) {
        ValidBrackets vBrackets = new ValidBrackets();
    
        boolean sol1 = vBrackets.solution("()()"    );
        boolean sol2 = vBrackets.solution("(())()"  );
        boolean sol3 = vBrackets.solution(")()("    );
        boolean sol4 = vBrackets.solution("(()("    );

        TestUtil.test(true, sol1);
        TestUtil.test(true, sol2);
        TestUtil.test(false, sol3);
        TestUtil.test(false, sol4);
    }
}

// 짝을 맞춰서 스택으로 풀면 된다. 스택에 들어갈 스트링이 정해져 있으므로 그냥 카운트로 구현함
// 체감상 난이도 1
