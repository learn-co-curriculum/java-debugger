# Code-Along: Debugger

## Learning Goals

- Use the debugger feature in IntelliJ.

## Introduction

In this lesson, we will learn about the debugger feature in IntelliJ and how to
debug a program when we see an unexpected exception is thrown at runtime. An IDE
debugger is a great tool for going through code step by step to try to figure
out why something may not be working the way that it is anticipated. We will
make use of the code from the "User Interaction" lesson to step through.

Pull down this lesson to look at the code we will be debugging. You can find
the code under `java-debugger/src/main/java/StudentGame.java`. If you open up
the Java file, you will see the following code:

```java
import java.util.Scanner;

public class StudentGame {
    public static void main(String[] args) {
        System.out.println("Please enter a number:");
        Scanner inputScanner = new Scanner(System.in);
        int userNumber = inputScanner.nextInt();
        System.out.println("The user entered " + userNumber);
    }
}
```

## Running in Debug

Let's run the debugger on this class! When we run the debugger, it will run just
like it would if we were to run it with the run button, except that it can stop
on specific lines of code and pause the execution of the program.

Does this sound familiar? That's because when we want to use the Java Visualizer
plugin in IntelliJ, we have to run our code in debug mode! So technically, we
have already seen how to run the debugger.

Let's refresh our memory with some terminology review!

- **Breakpoints** are lines that we specify where we want the execution of the
  program to pause. When the debugger comes across a breakpoint, it will stop
  and give us control over the execution of the subsequent lines of code.
- A **debug console** is the console that appears at the bottom of the IDE with
  some buttons that will allow us to proceed with the execution of the program.
  We can also see the Java Visualizer tab in the debug console.

Since we are already familiar with running in debug mode, set a breakpoint on
line 7:

![set-breakpoint-line-7](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-set-breakpoint.png)

Now let's run the debugger and wait for the program to hit the breakpoint:

![hit-breakpoint](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-hit-breakpoint.png)

Notice the buttons to the right of the Java Visualizer tab that we highlighted
in the screenshot above. These buttons show how to proceed with the execution of
the program. Let's actually go through each button and explain its purpose:

- Step Over (![step-over](https://curriculum-content.s3.amazonaws.com/java-mod-3/debugger/step-over.png)):
  runs the current line of code and stops again on the next line.
- Step Into (![step-into](https://curriculum-content.s3.amazonaws.com/java-mod-3/debugger/step-in.png)):
  follows the instruction on the current line of code by going into
  the method that is being called. If the current line does not call a method,
  then it will behave the same way as the "step over" action.
- Force Step Into (![force-step-into](https://curriculum-content.s3.amazonaws.com/java-mod-3/debugger/force-step-in.png)):
  use when "step into" doesn't actually go into the call - this
  happens when the call is to a method in a compiled class (like a
  `System.out.println()` for example).
- Step Out (![step-out](https://curriculum-content.s3.amazonaws.com/java-mod-3/debugger/step-out.png)):
  finishes the execution of the current method and pauses on the
  next line in the calling method.
- Run to cursor (![run-to-cursor](https://curriculum-content.s3.amazonaws.com/java-mod-3/debugger/run-to-cursor.png)):
  continues the execution of the program until the debugger
  reaches the instruction where your cursor is currently positioned.

For our purposes when debugging, we will mostly be using the first two actions:
"step-over" and "step-into".

Looking back at our code, let's enter in some user input and then choose "step
over" to proceed to the next line:

![enter-user-input-step-over](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-enter-input.PNG)

Notice the input we enter here is a `String` rather than an `int`. What do we
think is going to happen when we step over this line of code?

![inputmismatchexception](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/inputmismatch-error.PNG)

Once we click "Enter" and step over line 7, we will see the program throws an
`InputMismatchException` error.

If we want the program to handle this exception more gracefully, we can pinpoint
this line of code, line 7, as to where we should insert a `try-catch` block.

Replace the code in `StudentGame` with the following:

```java
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGame {
    public static void main(String[] args) {
      System.out.println("Please enter a number:");
      Scanner inputScanner = new Scanner(System.in);
      try {
        int userNumber = inputScanner.nextInt();
        System.out.println("The user entered " + userNumber);
      } catch (InputMismatchException exception) {
        System.out.println("The input was not a number");
      }
    }
}
```

Then set the breakpoint on line 9: `int userNumber = inputScanner.nextInt();`

![reset-breakpoint](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-reset-breakpoint.PNG)

Let's run the program in debug again and enter the same input of "Hello" and see
what happens this time when we step over the line:

![exception-caught](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-execution-falls-into-catch.PNG)

This time, instead of throwing the runtime error, we caught the exception using
the `try-catch` block! When we stepped over line 9, where the breakpoint is at,
it moved to the next line that would be executed, which is inside the `catch`
block.

We can continue to resume the execution of the program until it sees another
breakpoint or until the program has fully executed by clicking the
**resume program** button on the left-hand sidebar of the debug console. We
could also choose to pause or stop the execution by choosing the
**pause program** or **stop** button respectively below the resume program
button:

![resume-pause-stop-execution-buttons](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/resume-pause-stop-program.PNG)

Let's click resume the program. When we do so, the program will finish executing
since there are no more breakpoints and print out "The input was not a number".

## Conclusion

Debugging a great tool to understand what the program is actually doing and to
help find the reason(s) it's not doing what we may expect it to do. It allows us
to step into the program, line by line, and find where an error may be at within
the code.
