# Code-Along: Debugger

## Learning Goals

- Use the debugger feature in IntelliJ.

## Introduction

In this lesson, we will learn about the debugger feature in IntelliJ and how to
debug a program when we see an unexpected exception is thrown at runtime. An IDE
debugger is a great tool for going through code step by step to try to figure
out why something may not be working the way that it is anticipated.

Pull down this lesson to look at the code we will be debugging. You can find
the code under `java-debugger/src/main/java/BuggyProgram.java`. If you open up
the Java file, you will see the following code:

```java
public class BuggyProgram {
  public static void main (String[] args) {

    int pizzaSlices = 10;
    int people;

    // How many slices of pizza should each person get?
    int slicesPerPerson = pizzaSlices / people;
    System.out.println("Each person should have " + slicesPerPerson + " slices of pizza.");

    // Are there more slices of pizza than people?
    boolean morePizza = pizzaSlices < people;
    System.out.println("Are there more slices of pizza than people at the party?");
    System.out.println(morePizza);
  }
}
```

The code above is representing some people having a pizza party! But there are
some bugs in it. Let's see if we can help them out!

## Running in Debug

We may see some bugs and errors already in this code, but let's run the debugger
on this program to help us identify them. When we run the debugger, it will run
just like it would if we were to run it with the run button, except that it can
stop on specific lines of code and pause the execution of the program.

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
line 8:

![set-breakpoint-line-8](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/set-breakpoint.png)

Now let's run the debugger! But uh-oh! It looks like we hit a compile-time
error. Remember, a compile-time error is an error that occurs when we attempt
to compile the code. This means the JVM cannot successfully convert the source
code into Java bytecode.

![people-variable-not-initialized](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/debugger-variable-not-initialized.PNG)

It appears that the `people` variable was not initialized. Let's initialize it
to `0` on line 5:

```java
        int people = 0;
```

Let's run the debugger again and watch it hit the breakpoint on line 8:

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

Now notice the values the variables are holding within the debug console.
`pizzaSlices` is holding the value of 10 and `people` is holding the value of 0.
We declare the variable `slicesPerPerson` on line 8 and initialize it to
`pizzaSlices / people`. This expression is the same as `10 / 0`, which, as we
know, is not a number since we can't divide anything by 0. Let's see what
happens when we click the step-over button:

![arithmeticexception](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/arithmeticexception.png)

Here we have run into a runtime error. Recall that a runtime error is an error
that occurs at runtime, when the program is executing. This is different from
the compile-time error we saw previously since we were able to successfully
convert the source code to Java bytecode in order to run the program.

Let's change what the `people` variable is initialized to on line 5. Let's say
there are 5 people at this pizza party:

```java
        int people = 5;
```

When we run the debugger again, notice when we hit the breakpoint that the
values the variables are holding have updated!

![hit-breakpoint-again](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-hit-breakpoint-2.png)

Now instead of the expression `pizzaSlices / people` equating to `10 / 0`, it
will evaluate to `10 / 5`. If we step-over the breakpoint, we'll see that the
`slicesPerPerson` variable has now been declared and initialized to 2.

![fixed-division-by-zero](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-initialized-slices.png)

As we can see, the debugger can help us identify some obvious errors. Like a
variable not being initialized or a division by zero. But it can also help
identify bugs that may not be so obvious. They might not even be thrown as an
error or exception!

Remove the breakpoint on line 8 by clicking the red dot next to the line number.
Place another breakpoint on line 12:

![reset-breakpoint](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-reset-breakpoint.png)

We can continue to resume the execution of the program until it sees another
breakpoint or until the program has fully executed by clicking the
**resume program** button on the left-hand sidebar of the debug console. We
could also choose to pause or stop the execution by choosing the
**pause program** or **stop** button respectively below the resume program
button:

![resume-pause-stop-execution-buttons](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/resume-pause-stop-program.PNG)

Let's click "resume program" and watch it hit the breakpoint on line 12:

![hit-breakpoint-line-12](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-hit-breakpoint-3.png)

Before we step-over this statement, let's look at the boolean expression
`pizzaSlices < people`. In words, this would read as "pizza slices is less than
people." If we look in the debug console, we can see that `pizzaSlices` is set
to 10 and `people` is set to 5.

<details>
  <summary>What does the expression <code>pizzaSlices < people</code> evaluate to?</summary>

  <p>Answer: <code>false</code></p>

  <p><code>pizzaSlices < people</code> is equivalent to 10 < 5, which is false.</p>

</details>

Now let's click the step-over button:

![step-over-to-line-13](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-step-over-line-13.png)

Notice the `morePizza` variable has now been declared and initialized to the
boolean expression.

We now will print out "Are there more slices of pizza than people at the party?"
to the console. If we resume the program and look at the console, notice the
output:

```text
Each person should have 2 slices of pizza.
Are there more slices of pizza than people at the party?
false
```

Now think about the question: "Are there more slices of pizza than people at the
party?" If we look back at our program, see that we have initialized the
variables `pizzaSlices` and `people` to 10 and 5 respectively.

10 is _greater than_ 5; therefore, shouldn't our program say "true" instead of
"false" to answer the question accurately? And if this is wrong, why wasn't an
error thrown?

Look back at the `BuggyProgram` on line 12:

```java
boolean morePizza = pizzaSlices < people;
```

<details>
  <summary>What is wrong with the boolean expression?</summary>

  <p>Answer: The wrong operator is used.</p>

  <p>The question we are trying to answer is "Are more pizza slices than people?" So, we should be checking <code>pizzaSlices > people</code>.</p>

</details>

If the boolean expression is wrong, why didn't we see an error or exception
thrown?

If we look at the code, there is nothing syntactically wrong. We have a boolean
expression being assigned to a `boolean` variable. We also end the statement
with the terminator symbol, `;`. With that said, this statement will compile
just fine - resulting in no compile-time error. There is also nothing wrong with
this statement that would cause a runtime error. It is a completely valid
statement to the computer. But, because it does not do what we would expect, it
is still considered a **bug**, or error in the program.

These types of bugs are harder to detect at times since they do not throw any
compile-time or runtime errors. The debugger is an excellent tool to help find
these types of bugs and issues since they aren't always obvious. While this bug
might have been an easy one to find, you may encounter harder bugs as your time
as a developer. Make sure to take advantage of the debugger tool to help isolate
and identify bugs and problem statements.

Go ahead and fix the boolean expression. Then run the debugger again to ensure
the output looks like this:

```text
Each person should have 2 slices of pizza.
Are there more slices of pizza than people at the party?
true
```

## [Optional] Debugging Code-Along Continued

What happens if we change line 5 to:

```java
        int people = 3;
```

Set a breakpoint at line 9 and run the `BuggyProgram` in debug mode again:

![integer-division](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-hit-breakpoint-4.png)

As we can see, on line 8:

```java
int slicesPerPerson = pizzaSlices / people;
```

We performed integer division. As a reminder, with integer division, the decimal
part is discarded or removed so that we only have an `integer` result. This is
performed when we have two `int` values being divided.

So `pizzaSlices / people` is the same as saying `10 / 3`, which evaluates to 3
when using integer division. If we were to perform real division, `10 / 3` would
be approximately 3.33.

Let's say we had a pizza cutter where we could cut a slice of pizza into thirds.
With that assumption, maybe we were expecting real division rather than integer
division.

Stop the program using the stop button on the left-hand sidebar of the debug
console:

![resume-pause-stop-execution-buttons](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/resume-pause-stop-program.PNG)

Then modify the program to look like this:

```java
public class BuggyProgram {
    public static void main (String[] args) {

        // Change the data type from int to double
        double pizzaSlices = 10.0;
        int people = 3;

        // How many slices of pizza should each person get?
        // Change the data type from int to double
        double slicesPerPerson = pizzaSlices / people;
        System.out.println("Each person should have " + slicesPerPerson + " slices of pizza.");

        // Are there more slices of pizza than people?
        // Modify the boolean expression if you haven't already to use a > rather than a <
        boolean morePizza = pizzaSlices > people;
        System.out.println("Are there more slices of pizza than people at the party?");
        System.out.println(morePizza);
    }
}
```

If we run the program again and hit the breakpoint, notice what the variables
now store:

![real-division](https://curriculum-content.s3.amazonaws.com/java-mod-1/debugger/intellij-debugger-real-division.png)

If we step over this line, notice the console now has printed:

```text
Each person should have 3.3333333333333335 slices of pizza.
```

Or otherwise interpreted, each person can have 3 slices and a third of one
slice of pizza.

## Conclusion

In this lesson, we learned how to use the debugger feature in IntelliJ. The
debugger can help us identify compile-time errors, runtime errors, and the "not
so obvious" bugs - like using the wrong operator or performing integer division
versus real division. It is a powerful tool that can be used when something in
a program or application is not operating as expected.
