## Java 8 lambdas

Lambda expressions take advantage of parallel process capabilities of multi-core environments as seen with the support of 
pipeline operations on data in the Stream API.

They are anonymous methods (methods without names) used to implement a method defined by a functional interface. 
It’s important to know what a functional interface is before getting your hands dirty with lambda expressions.

**Functional interface**: 
A functional interface is an interface that contains one and only one abstract method.

If you take a look at the definition of the Java standard [Runnable interface](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html), 
you will notice how it falls into the definition of functional interface because it
 only defines one method: ```run()```.

In the code sample below, the method ```computeName``` is implicitly abstract and is the only method defined,
 making MyName a functional interface.

```
interface MyName{
   String computeName(String str);
}
```


**The Arrow Operator**

Lambda expressions introduce the new arrow operator ``` -> ``` into Java. It divides lambda expressions in two parts:

```
(n) -> n*n
```

The left side specifies the parameters required by the expression, which could also be empty if no parameters are required.


The right side is the lambda body which specifies the actions of the lambda expression.
It might be helpful to think about this operator as “becomes”. 
For example, “n becomes n*n”, or “n becomes n squared”.

With functional interface and arrow operator concepts in mind, 
you can put together a simple lambda expression:

```
    interface NumericTest {
        boolean computeTest(int n);
    }

    public static void main(String[] args) {
        NumericTest isEven = (n) -> (n % 2) == 0;
        NumericTest isNegative = (n) -> (n <0);

        System.out.println(isEven.computeTest(5));
        System.out.println(isNegative.computeTest(-5));
    }
```

**Block Lambda Expressions**

when the code on the right side of the arrow operator contains more than one statement known as **block lambdas**.

```
    interface NumericTest {
        boolean computeTest(int n);
    }

    interface MyString{
        String myStringFunction(String str);
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
    }      
```

**Generic Functional Interfaces**
A lambda expression cannot be generic. But the functional interface associated with a lambda expression can.
It is possible to write one generic interface and handle different return types like this.

```
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

```



**Supplier** : ``` java.util.function.Supplier ``` is a functional interface. 
The supplier can be used in all contexts where there is no input but an output is expected. 
It has one abstract functional method 

```T get()```

Since **Supplier** is a functional interface, hence it can be used as assignment target for a lambda expression or a 
method reference.

It can be used as assignment target for a lambda expression or method reference. Instances of this can be created with 
lambda expressions, method references or constructor references.

``` T get() ```: This abstract method does not accept any argument but instead returns newly generated values, T, 
in stream 

**Consumer** : ``` java.util.function.Consumer``` is also a functional interface.
 Like Supplier, it has one abstract functional method ```accept(T t) ``` and
  a default method ```andThen(Consumer<? super T> after ``` )
 
 The consumer can be used in all contexts where an object needs to be consumed, i.e. taken as an input and some operation 
 is to be performed on object without returning any result.
 
 A common example of such an operation is printing where an object is taken as input to the printing function
  and the value of the object is printed. Since Consumer is a functional interface, hence it can be used as the assignment 
  target for a lambda expression or a method reference.
  
Refer: [Supplier](TestConsumer.java ) and [Consumer](TestSupplier.java )
example


**Basic Lambda Expressions**

 * Passing Code with Anonymous Classes (A Review)


Let's start by creating a class. We'll call it "Runner", just to give it a name. 
I'm going to give this method which I'll call “run”. Now let's imagine that for some reason we want 
to pass a block of code to "run".

```
class Runner {
    public void run() {
       
    }
}
```
How would you do that in previous versions of Java?
 
Well, the answer is, 

**First** you need to define an interface. Let's do that; 

I'll call it "Executable". We need to give this interface a method, and this method would be the place 
where we put the code we want to run.

```
interface Executable {
    int execute();
}
```

Now we can make the Runner class accept an object that implements the Executable interface. 
It's going to get something that implements this interface and its going to know that that object, whatever it is, 
has an execute method.

```
class Runner {
    public void run(Executable e) {
        e.execute();
    }
}
```


I've made the Runner class call the "execute" method of whatever object implements "Executable". 

Now to actually use this, we need an instance of the Runner class. 
In previous versions of Java, to actually pass it some block of code we've got to somehow have an object that implements
 this interface, and we can do that using an anonymous class syntax. 

Let's take a look at the completed code.
 
If we now run this program, it says "hello there".

```
interface Executable {
    void execute();
}

class Runner {
    public void run(Executable e) {
        e.execute();
    }
}

public class App {

    public static void main(String[] args) {
            Runner runner = new Runner();
            runner.run(new Executable() {
                public void execute() {
                    System.out.println("Hello there.");
                }
            });
        }
}
``` 


 The whole point of this is just to pass in some code to the run() method. Then this method can and do what 
 it likes with it, 
 and in this case it’s just executing the code that we passed it in the execute() method.
  So this is a lot of text purely just to pass a code block. Let's have a look at how we can do that with 
  lambda expressions in Java 8. 


**Basic Lambda Expressions**


By the way, the term “lambda expression” apparently comes from mathematics where the Greek letter lambda has been 
historically associated with some kind of analogous situation, passing a function to a function, or some such thing .... 

The same functionality can be replicated with a lambda expression in Java 8 that looks like this:

```
interface Executable {
    void execute();
}

class Runner {
    public void run(Executable e) {
        e.execute();
    }
}

public class App {
    public static void main(String[] args) {
            Runner runner = new Runner();
            runner.run( () -> System.out.println("Hello there.") );
            
        }
}

```

 
If you run this program, you'll see that it does the same thing as the unwieldy syntax above that we had in previous 
versions of Java. 

It's really important to keep in mind that all this is, is a way of passing a block of code that the run() method here 
is going to execute.
 Blocks of codes, of course, might have return values and might also accept parameters. We're going to look at how
  we can do that with a lambda expression. 

So firstly, let's ask what we’re going to do if we don't want just one expression like the System.out.println above; 
what about if we want multiple lines or blocks of code? Well, we might guess we can put curly brackets in here. 
Now we can't just have an expression in there, we’ve got to have complete statements and statements have got to
 end in semi-colons.

```
public class App {

    public static void main(String[] args) {
            Runner runner = new Runner();
            runner.run( () -> {
                    System.out.println("Hello there.");
                    System.out.println("And hello again.");
            });
        }
}
``` 



 
Again we're executing a block of code. The lambda expression by itself is just a way of passing the code to our Runner
 object here. It just so happens in this case, the run() method we defined, happens to execute that code, but of course 
 we could do whatever we like with it. 
 
 Refer [code](ExecutableDemo.java)