# SnakeAI
Automated Snake AI game developed during second year in university

# How it Works

The task was simple: write a program to play a
multiplayer version of the game Snake. The goal? Eat
enough apples to become the longest snake on the
board. But don't forget about the other players. You'll
want to stop them doing the same thing. By any means
necessary.

# Game Mechanics

Here's how it all works. At any point in time, there is a single apple on the board. Eating the apples causes your snake to
grow by a certain length. There are three things you need to know about: snakes, obsticles and apples. Let's look at the mechanics of each in turn.

## Snakes

Snakes move in one of the four cardinal directions at a speed of one square per timestep, with all moves executed simultaneously. 
A snake dies when it collides with any of the grid's sides, or moves into an obsticle. If a snake collides with the body of another snake, 
the latter is credited with a kill. In the event of a head-on collision between two snakes, both snakes are killed, but neither is credited with a kill. When
a snake dies, it will miss the next timestep, and be respawned on the following step at a random location on the board. Note that if your program has crashed,
your snake will be removed from the board for the duration of the round.

## Apple

The apple appears at a random location at the beginning of the game. Every time it is eaten, it is
immediately respawned at a random location. Additionally, the apple will be respawned if it hasn't
been eaten within a certain number of moves. Eating an apple causes a snake to grow for the next few rounds by
having its tail remain in its current position. If multiple snakes consume the same apple at the same time, both
snakes are killed and the apple is respawned at a new location. Additionally, the apple will be respawned if it
hasn't been eaten within a certain number of moves.

## Interacting with the Game

Now that we've explained how the game works, let's look at how your agent will actually play the game. Your
agent interacts with the game through its standard I/O mechanisms. This means that at every step, your agent
will read in the current state of the game, and make its move by printing to standard output.

## Initialisation

When the game first starts, each agent is sent an initialisation string which specifies the number of snakes
in the game, the width and height of the board, and the type of game being played (mode). For these purposes,
you can assume that the number of snakes is always 4, the width and height 50, and the mode 1. The initial
input thus looks something like this:
4 50 50 1

## Game-State Updates

At each step in the game, a game-state string is generated and sent to all agents, which can then be read
in via standard input. Coordinates are such that (0,0) represents the top le square of the board. Each state
takes the following form:

x- and y-coordinates of the first apple
x- and y-coordinates of the second appleyour snake number (an integer from 0 to 3)
description of snake 0
description of snake 1
description of snake 2
description of snake 3

When a snake is not invisible, it is described in the
following format:

alive/dead length kills headX,headY bodyX,bod

To better describe what's going on here, let's look at a
concrete example. Imagine that we receive the following
game-state:

-1 -1
8 16
0
alive 26 2 10,12 15,12 15,7 5,7 5,2
dead 6 6 14,13 19,13
alive 2 1 12,13 12,14
invisible 17 1 9 21,14 21,14 15,14 15,13

In this state, the first apple is not on the board, while
the second is at (8,16).
The next line gives the index of our snake. In this case,
we're snake 0, so we're the first one in the next fourlines. If we were the last snake, we'd get an index of 3.
The next four lines describe each snake in the game. The first word of each line is either "alive", "dead" or
"invisible". Dead snakes are not displayed on the game board, and so they should be ignored. Next comes the
snake's current length, followed by the number of other snakes it has killed.
If the snake is alive, then what follows is the snake's coordinate chain. The coordinate chain is made up of
(x,y) coordinates representing points of interest in the snake's body. The first coordinate is the snake's head,
the next represents the first kink in the snake. There can be any number of kinks in the snake, all of which are all
listed in order. Finally, the last coordinate represents the tail of the snake. As an example, the 3rd snake has the
following description:

Lastly the snake's coordinate chain is given.
alive 2 1 12,13 12,14

This snake is alive, has length 2, and 1 kill. Its head is at position (12, 13) and its tail is at (12, 14). From this we
can deduce that the snake is traveling upwards, since the y-coordinate of its head is less than its tail's.
If the snake is invisible,invisible 17 1 9 21,14 21,14 15,14 15,13 , the line starts the same way as an alive snake,
"invisible" followed by an integer denoting the snake's current length and another integer denoting its kills.
This is followed by an integer representing the number of time steps the snake has been invisible for (starting at
0 immediately after eating the apple). This is followed by an x,y coordinate pair giving the position the snake went
invisible. Lastly there is a coordinate chain giving the known information about the snake. When a snake goes
invisible, the tail portion of the snake is known to all other snakes. We send you this information so that you
don't have to keep track yourselves. If the invisible snakeis your snake, the coordinate chain will contain your
entire snake and not just the known tail information. In this example, the snake is invisible, has length 17, has 1
kill, has been invisible for 9 timesteps, went invisible at 21,14, and the bit of the snake we know about is:
21,14 15,14 15,13.

## Making a Move

Once the game-state has been read in, your agent should use that information to decide on its next move. A move
is made simply by printing out an integer in the range 0-6. The available moves are as follows:

0 Up (relative to the play area - north)
1 Down (relative to the play area - south)
2 Left (relative to the play area - west)
3 Right (relative to the play area - east)
4 Left (relative to the head of your snake)
5 Straight (relative to the head of your snake)
6 Right (relative to the head of your snake)

Note that if you output a move that is opposite to the direction you're currently headed, you will simply continue straight.

## Logging

In order to enable some form of logging, the game creates two files per agent, located in the same directory as your program.
This is especially useful for Python or C++ agents, as they have no other method of debugging. The first file is an error 
file which logs all runtime errors triggered by the code, while the second is a log file which allows your program to save output. 
To write to the log file, simply prepend the word "log" and a space to your print statements. For example, if you output the
string "log message", "message" will be appended to the end of the log file. Anything beginning with "log " will not be treated 
as a game move.Game Over When the game has been concluded, instead of a normal game-state, a single line containing the words "Game
Over" will be sent to each agent. This gives you the opportunity to do some last minute cleanup, saving data to files, etc. 
before you are shut down. If you do not exit after 500 milliseconds, you will be forcibly shut down.

## Scoring

Throughout each game, the longest length achieved by each snake is recorded. Snakes are ranked based on their longest length, 
with ties broken by kill count. If the number of kills is also equal, then the snake with the higher index takes the win.

## The Configuration File

Unless you feel very strongly about it, feel free to skip this section.
The various parameters of the game can be set through a configuration file. If you want to use non-default
parameters, create a text file with a name containing the phrase "snake_config" in the directory (or subdirectory)
from which the game is run. Note that if multiple configuration files are found, they will all be applied, but
we make no guarantee of the order in which this will occur. An example of a configuration file, as well as all available
parameters and their default values, are given below.

#snake_config.txt
#Comments are allowed in the file, as are bla
num_snakes 8
#add double the number of snakes
#Make the board bigger
game_width 75
game_height 75
Key
Default
Description
Value

game_width
50
The width of the boardKey
Default
Description
Value
game_height 50 The height of the board
duration 300 The length of a single
round, in seconds speed
50
The amount of time
each agent is given to
calculate a move, in milliseconds
num_snakes
4
The number of snakes
that contest a single
round. The game
supports any number of
snakes greater than 1
random_seed
null
This value sets the random number generator's seed, which allows for repeatable games. Any string value
(including the string "null") can be used to
set this configuration.Kludged together with duct tape and prayers

University of the Witwatersrand, South Africa
