package examples;

public class Main {
    public static void main(String[] args){
//        String text = "Hello Connor!";
//        saySomething(text);
//        System.out.println("the text has now become: " + text);

//        int number = 3;
//        int addedNumber = add(number);
//        System.out.println("added number is now " + addedNumber + ", however original number is still " + number);
        StringBuilder text = new StringBuilder("Connor is cool!");
        System.out.println("before: " + text);
        signedByTim(text);
        System.out.println("after: " + text);
    }

    public static void saySomething(String text){
        text += " And Hello Tim!";
        System.out.println(text);
    }

    public static void signedByTim(StringBuilder text){
        text.append("\n\n\n-Tim");
    }

    public static int add(int x){
        System.out.println("When x comes into add, it is: " + x);
        x += 10;
        return x;
    }


}
