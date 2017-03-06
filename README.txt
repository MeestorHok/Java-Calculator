Jacob Mitchell (jmitch32)
jmitch32@u.rochester.edu
Project 2: Shunting Yard Calculator
3/5/2017

All code in this project was written by me.

To run the input file, run the TestClass with 2 parameters, the input file name and the output file name.
Custom test cases were created in TestClass2 to demonstrate the use of trig functions, log, pi, sqrt, %, etc.


----------------------
|| What Does It Do? ||
----------------------

This calculator can accurately answer any simple arithmetic problem, including the use of sin, cos, tan, sec, csc, cot,
asin, acos, atan, log, ln, sqrt, pi, e, !, %, ^, *, /, +, -, <, >, =, and any proper use of parentheses. It is
unaffected by the use of whitespace and capitalization.

It will tell the user if they entered something wrong and why it is wrong, for example mismatched parentheses or an
unknown function call, invalid syntax, or a nonexistent number (like asin(3)).


-----------------------
|| How Does It Work? ||
-----------------------

The magic of the Shunting Yard Algorithm happens within the Calculator class. It has a helper class of Token, which
isolates the information about different tokens like precedence, how to evaluate them, and associativity. Calculator
relies on the Stack and Queue classes which each rely on Linked Lists, all contained within the structures package.


---------------
|| Hierarchy ||
---------------

- Calculator (Class) : Main Shunting-Yard Driver, uses a Queue and a Stack
- Token (Abstract Class) : Allows for querying about a particular token (like precedence)
- InvalidExpressionException (Class) : Exception thrown when any problem is encountered with the input
- TestClass (Class) : Tester Class to read from an input file and fill an output file with the results
- TestClass2 (Class) : Simple Test class to print more advanced calculations to the console

- structures (Package)
    |___ MyDoubleNode (Class) : Node class for MyDoublyLinkedList
    |___ MyDoublyLinkedList (Class) : Custom Implementation of a Doubly-Linked List
    |___ MyLinkedList (Class) : Custom Implementation of a Singly-Linked List
    |___ MyQueue (Class) : Custom Implementation of a Queue using a Doubly-Linked List
    |___ MyStack (Class) : Custom Implementation of a Stack using a Singly-Linked List
    |___ SimpleLinkedList (Interface) : Interface for both custom Linked List implementations
    |___ Queue (Interface) : Interface for a simple Queue
    |___ Stack (Interface) : Interface for a simple Stack


------------------
|| Extra Credit ||
------------------

For extra credit on this project, I implemented many different functions, as listed above, and I used
Exception Handling to handle invalid input in a nice way.