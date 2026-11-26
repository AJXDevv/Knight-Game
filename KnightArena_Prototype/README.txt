KNIGHT ARENA — GREENFOOT PROTOTYPE
Game Programming 1 / 420-141-VA

OPEN THE GAME
1. Unzip KnightArena_Greenfoot_Prototype.zip.
2. Open Greenfoot on your computer.
3. Project > Open, and select the KnightArena_Prototype folder
   (or double-click project.greenfoot).
4. Make sure MenuWorld is showing. If it is not, right-click MenuWorld
   and choose new MenuWorld().
5. Click Run, then PLAY.

HOW TO PLAY
WASD or arrow keys ........ move the knight
SPACE ..................... swing the sword in the direction faced
Mouse ..................... click menu buttons

GOAL
Clear five waves. Enemies give 100 points each.
The knight has five health. Being hit gives temporary immunity.
Basic red enemies take two swings; faster purple enemies take one.
If health reaches zero, Game Over displays your score.
Defeating the final wave displays Victory.

FILES
MenuWorld.java ....... game start screen
HelpWorld.java ....... controls / instructions
ArenaWorld.java ...... waves, enemy spawning, collisions, scoring
Knight.java .......... movement, sword swings, health
Enemy.java ........... enemy AI and damage
Obstacle.java ........ solid obstacle objects
Slash.java ........... sword swing animation
Hud.java ............. health, wave, score display
EndWorld.java ........ win/loss screen
MenuButton.java ...... menu interactions
TextLabel.java ....... UI text
images/ .............. original PNG art for the game
sounds/ .............. original WAV effects
project.greenfoot .... Greenfoot project metadata
