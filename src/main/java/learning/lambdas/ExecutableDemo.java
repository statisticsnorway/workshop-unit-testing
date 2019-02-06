package learning.lambdas;

public class ExecutableDemo {

    interface Executable{
        void execute();
    }

    public void run(Executable e){
        e.execute();
    }

    public static void main(String[] args) {
        ExecutableDemo executable = new ExecutableDemo();

        executable.run(new Executable() {
            @Override
            public void execute() {
                System.out.println("Before Java 8");
            }
        });

        executable.run( () -> System.out.println("After Java 8"));

    }
}
