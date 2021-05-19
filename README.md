# JavaFX-XO 🕹️

> Play the famous classic TicTacToe game with your friends online!
> 
> Jump to the [Setup](#setup) section to to download the game, enjoy!
> 
> Discover [How to play](#how-to-play) and give me your [Feedback](#feedback) on this project.
> 
> You can find 5 more board games in the [Collection](#collection) section, give them a try!

## Table of contents
* [Hello, World!](#hello-world)
* [xo.shared.Game Charachteristics](game-charachteristics)
* [How to play](#how-to-play)
* [Setup](#setup)
* [Collection](#collection)
* [Project dependencies](#project-dependencies)
* [Additional info](#additional-info)
* [Feedback](#feedback)
* [Copyright notice](#copyright-notice)

# Hello, World!
The simplicity of this game makes it a good **starting** point to learn, 
my **ultimate goal** was to be able to tackle any game of this kind, 
discover my [**collection**](#collection) to see the path I took to reach it.

A GridPane filled with Clickable Nodes and you got yourself an XO game **gui**,
combined with a little bit of **logic** to handle game outcomes (win, lose, tie) is all you need to make an XO game,
the **networking** part is nothing but a tunnel between 2 clients.

# xo.shared.Game Charachteristics
- Turn based
- Multiplayer (no AI)
- Tabletop view (2D)
- Distributed (client/server)
- Socket programming (TCP)
- Message oriented communication (except game app)
- Supports multiple resolutions thanks to JavaFX scaling
- Supports fullscreen mode

# How to play
![Main app gui](./screenshots/mainApp.png)

After connecting to the server, the main app gui is presented to the player, 
allowing him to enter any username, then either Host, Join a specific room through its ID, 
or Join public rooms.

![Join app gui](./screenshots/joinApp.png)

If the player chooses to play with random people, he will be presented with a list of public rooms

![Room app gui](./screenshots/roomApp.png)

After joining a **room** the player is presented with this gui, where he can:
- Start a **chat** with people already in the room
- Change his **name**
- Take an empty **place**
- Set his **ready** status

The host has in addition the ability to:
- Change the room's **privacy** to either public/private
- **Kick** someone out of the room
- **Start** the game

Host privileges are **passed** automatically to the next player if the host leaves the room

![xo.shared.Game app gui](./screenshots/gameApp.png)

The game works as any other XO game but in a distributed environment, 
you can end the game without leaving the room using the **Return to..** menu at the top.

# Setup
Click [here](./setup/XO.jar?raw=true) to download the executable jar.
Double click it to launch the game, that's it!

# Collection
- XO (current)
- [Checkers](https://github.com/BHA-Bilel/JavaFX-CHECKERS)
- [Chess](https://github.com/BHA-Bilel/JavaFX-CHESS)
- [Connect4](https://github.com/BHA-Bilel/JavaFX-CONNECT4)
- [Dominoes](https://github.com/BHA-Bilel/JavaFX-DOMINOS)
- [Coinche](https://github.com/BHA-Bilel/JavaFX-COINCHE)

# Project dependencies
- **Java**-15.0.1
- **JavaFX**-11.0.2
- **controlsfx**-11.0.0
- **jfoenix**-9.0.10

# Additional info
- This project was developed intermittently due to other preoccupations, that's why I can't tell how much time it took me to complete it.
All the collection was initially developed in **Eclipse IDE** in late 2019, before I migrated to **Intellij IDEA** in 2021 to code the remaining parts while redesigning some parts to fit the new workflow model.

- This project wasn't my first nor my last experience coding in JavaFX, I'll do my best to publish other projects on my GitHub.

- **All** of the projects/repositories in my profile are produced by an **individual** effort, that I coded from **scratch**. However, I won't deny that I had to watch some Youtube tutorials to get a hint of how the logic works, for example I inspired the game logic from the tutorial below:

[JavaFX xo.shared.Game Tutorial: TicTacToe](https://www.youtube.com/watch?v=Uj8rPV6JbCE) by **Almas Baimagambetov** on Youtube. Check out his channel, it's focused around game dev using JavaFX and the FXGL engine.

# Feedback
What do you think of this project? leave your thoughts/recommendations !

<p align="center">
  <a href="https://gist.github.com/BHA-Bilel/b85e19f2659dcf5ab516d742feb5903a">
    <img src="https://gist.githubusercontent.com/BHA-Bilel/6eb01c298f0ccceff7511427afb52534/raw/ebb3b59e6e8af742699627d15672f28a1f144d26/feedback.gif" alt="Click here to give feedback!">
  </a>
</p>

# Copyright notice
This public repository contain purposely **unlicensed** source code (**NOT** open-source), 
that I only consider as a personal side project and a way to showcase my skills.
You can surely and gladly download and play my game, or view how it's made.

However, **I DO NOT** grant any kind of usage (Commercial, Patent, Private), Distribution or Modification of the source code contained in this repository.
For a **private** license agreement please contact me at: bilel.bha.pro@gmail.com
