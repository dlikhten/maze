The solution to the maze problem. It goes a little something like this:

The goal is to write a program that solves a maze like the following:

    #############
    #s  #     #f#
    ### # ### # #
    #     # # # #
    ####### # # #
    #       # # #
    # ####### # #
    #           #
    #############

"s" and "f" are used to mark the start and finish, respectively, "#" is used to mark non-traversable walls, and spaces are traversable. Your program should take as its only command-line argument, a reference to a file containing the maze representation, and it should print out the solution to the maze like so:

    #############
    #s++#+++++#f#
    ###+#+###+#+#
    #  +++# #+#+#
    ####### #+#+#
    #       #+#+#
    # #######+#+#
    #        +++#
    #############

Assumptions:

* The maze will always be rectangular
* There are no cycles
* There will always be a solution
* Diagonal moves are not allowed

We prefer solutions that are elegant and concise, using few lines of code while still being readable.

Enjoy!


However my solution removes some assumptions, as they were not needed for my solution:
* The maze may be of any shape
* There may be no solution, in which case "No solution" is printed
* There may be as many cycles as you want. However as long as a solution exists, a path must be determined.