package learning.lambdas;

public class LamdasDemo {
    interface NumericTest {
        boolean computeTest(int n);
    }

    interface MyString{
        String myStringFunction(String str);
    }

    interface MyGeneric<T> {
        T compute(T t);
    }

    public static void main(String[] args) {
        NumericTest isEven = (n) -> (n % 2) == 0;
        NumericTest isNegative = (n) -> (n <0);

        // Output: false
        System.out.println(isEven.computeTest(5));

        // Output: true
        System.out.println(isNegative.computeTest(-5));

        MyString reverseStr = (str)->{
             String result = "";
             for(int i = str.length()-1; i>= 0; i--){
                 result += str.charAt(i);
             }
             return result;
        };

        // Output: omeD adbmaL
        System.out.println(reverseStr.myStringFunction("Lambdas Demo"));

        // String version of MyGenericInteface
        MyGeneric<String> reverse = (str) -> {
            String result = "";

            for(int i = str.length()-1; i >= 0; i--)
                result += str.charAt(i);

            return result;
        };

        // Integer version of MyGeneric
        MyGeneric<Integer> factorial = (Integer n) -> {
            int result = 1;

            for(int i=1; i <= n; i++)
                result = i * result;

            return result;
        };

        // Output: omeD adbmaL
        System.out.println(reverse.compute("Lambda Demo"));

        // Output: 120
        System.out.println(factorial.compute(5));

    }
}
