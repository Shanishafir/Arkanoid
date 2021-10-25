Here you can find an Arkanoid-type game I have written in Java while undertaking an OOP course, it contains 4 levels of varrying difficulty.

An ant build.xml file is provided for convenience of compiling and executing.
Steps to run the program:
- First, make sure you have [Ant](https://ant.apache.org/) installed
- `git clone` the repository to a desired folder
- Use `ant` to compile all the .java files to a speerate /bin folder
- Use `ant run -Dargs="Levels"` to run the program, replace Levels with level numbers in a desired order.
- Finally, run `ant clean` to delete the /bin directory.

The 'biuoop-1.4.jar' contains various GUI objects and was created by the BIU OOP course team.
