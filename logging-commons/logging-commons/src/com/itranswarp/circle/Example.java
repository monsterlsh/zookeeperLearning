package com.itranswarp.circle;


import java.util.*;

public class Example {

    public static void main(String[] args) {

        String exp = "x + 2 * (y - 5)";
        SuffixExpression2 se2 = compile(exp);
        Map<String, Integer> env = new HashMap<>();
        env.put("x",1);
        env.put("y",9);
       // ("x", 1, "y", 9);
        double result = se2.execute(env);
        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * (9 - 5) ? "✓" : "✗"));

    }
    static SuffixExpression2 compile(String exp) {
        // TODO:
        //运算符优先级
        Map<String, Integer> map = new HashMap<>();
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("/", 2);
        map.put("(", 0);    // ( 优先级最低
        map.put(")", 3);
        //后缀表达式
        StringBuilder sb = new StringBuilder();
        //临时运算符栈
        Deque<Character> stackChar = new LinkedList<>();

        //把字符串转化为字符数组处理
        char[] chars = exp.toCharArray();

        /**
         * 中缀表达式转后缀表达式的方法：
         * 1.遇到操作数：直接输出（添加到后缀表达式中）
         * 2.栈为空时，遇到运算符，直接入栈
         * 3.遇到左括号：将其入栈
         * 4.遇到右括号：执行出栈操作，并将出栈的元素输出，直到弹出栈的是左括号，左括号不输出。
         * 5.遇到其他运算符：加减乘除：弹出所有优先级大于或者等于该运算符的栈顶元素【栈内的栈顶运算符>=遇到的运算符，就弹出】，然后将该运算符入栈
         * 6.最终将栈中的元素依次出栈，输出。
         */
        //遍历
        for (int i = 0; i < chars.length; i++) {
            //跳过空白字符
            if (chars[i] == ' ') {
                continue;
            }

            //1
            if (Character.isDigit(chars[i])) {
                StringBuilder sbi=new StringBuilder();
                while (Character.isDigit(chars[i])||chars[i]=='.'){
                    sbi.append(chars[i]);
                    i++;
                }
                sb.append(Double.parseDouble(sbi.toString())+" ");
                i--;
                continue;
            }
            //x、y
            if (chars[i]=='x'||chars[i]=='y'){
                sb.append(chars[i]+" ");
                continue;
            }

            //2、 3
            if (stackChar.isEmpty() || chars[i] == '(') {
                stackChar.push(chars[i]);
                continue;
            }

            //4
            if (chars[i] == ')') {
                while (!stackChar.isEmpty() && stackChar.peek() != '(') {
                    sb.append(stackChar.pop() + " ");
                }
                if (stackChar.peek() == '(') {
                    stackChar.pop();
                }
                continue;
            }

            //5
            while (!stackChar.isEmpty() && map.get(chars[i] + "") <= map.get(stackChar.peek() + "")) {
                sb.append(stackChar.pop() + " ");
            }
            stackChar.push(chars[i]);
        }

        //6
        while (!stackChar.isEmpty()) {
            sb.append(stackChar.pop() + " ");
        }

        System.out.println(sb.toString());

        return new SuffixExpression2(sb.toString());
    }
}

class SuffixExpression2 {
    //后缀表达式
    private String str;

    public SuffixExpression2(String str) {
        this.str = str;
    }

    //利用栈执行后缀表达式获得计算结果
    double execute(Map<String, Integer> env) {

        //替换变量后需要重新赋值回后缀表达式字符串，只调用replace并不会改变原后缀表达式
        this.str=this.str.replace("x",env.get("x").doubleValue()+"");
        this.str=this.str.replace("y",env.get("y").doubleValue()+"");

        //运算时存数值的临时栈
        Deque<Double> stack = new LinkedList<>();

        char[] chars = this.str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            //跳过空白字符
            if (chars[i] == ' ') {
                continue;
            }

            //数值压栈
            if (Character.isDigit(chars[i])) {
                StringBuilder sbi=new StringBuilder();
                while (Character.isDigit(chars[i])||chars[i]=='.'){
                    sbi.append(chars[i]);
                    i++;
                }
                stack.push(Double.parseDouble(sbi.toString()));
                i--;
                continue;
            }

            //读取两个运算数，注意顺序
            double b = stack.pop();
            double a = stack.pop();
            //根据运算符运算，把结果压栈
            switch (chars[i]) {
                case '+':
                    stack.push(a + b);
                    break;
                case '-':
                    stack.push(a - b);
                    break;
                case '*':
                    stack.push(a * b);
                    break;
                case '/':
                    stack.push(a*1.0 / b);
                    break;
                default:
                    break;
            }
        }

        return stack.pop();
    }
}