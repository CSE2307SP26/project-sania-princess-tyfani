# project26

## Team Members:

* Princess Ghann
* Sania Ali
* Tyfani Fennell
  

## User stories

1. A bank customer should be able to deposit into an existing account. (Shook)
2. A bank customer should be able to withdraw from an account. (Sania Iteration 1)
3. A bank customer should be able to check their account balance. (Princess Iteration 1)
4. A bank customer should be able to view their transaction history for an account. (Sania Iteration 2)
5. A bank customer should be able to create an additional account with the bank. (Princess Iteration 1)
6. A bank customer should be able to close an existing account.(Tyfani Iteration 1)
7. A bank customer should be able to transfer money from one account to another. (Tyfani Iteration 1)
8. A bank adminstrator should be able to collect fees from existing accounts when necessary.
9. A bank adminstrator should be able to add an interest payment to an existing account when necessary.

## What user stories do you intend to complete next iteration?
8. A bank adminstrator should be able to collect fees from existing accounts when necessary.
9. A bank adminstrator should be able to add an interest payment to an existing account when necessary.

## Is there anything that you implemented but doesn't currently work?
Currently our Task 7 relies on Task 2, so both are in need of remedying.

## What commands are needed to compile and run your code from the command line?
What commands are needed to compile and run your code from the command line (you should provide a script that people can use to run your program) - for this question, I already put the command in the readme and you can look at my runApp.sh file too, but essentially I put these commands in the runApp.sh file:
javac -d out src/main/*.java #compiles java files into class files, puts them in a folder called out
java -cp out main.MainMenu #runs the app, -cp out tells Java where to find the compiled files (out folder), main.MainMenu is the class to run

and all we need to do is run ./runApp.sh in the terminal
