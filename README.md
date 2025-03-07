# Attack-On-Titan-Utopia

### A one-player, endless tower defense game inspired by the hit anime *Attack on Titan*

The story of the anime revolves around how titans, gigantic humanoid creatures, emerged one day and wiped out most of humanity. The few surviving humans fled and hid behind 3 great walls that provided safe haven from the titan threats:

- **Wall Maria** (the outer wall)
- **Wall Rose** (the middle wall)
- **Wall Sina** (the inner wall)

This game takes place in an imaginary scenario where the titans breached their way throughout Wall Maria and reached the northern border of Wall Rose at the **Utopia District**. The human forces stationed in Utopia engage the titans in battle for one last hope of preventing the titans from breaching Wall Rose. The humans fight by deploying different types of Anti-Titan weapons in order to stop the Titan’s onslaught and keep Utopia’s (and Wall Rose’s) walls safe.

---

## Game Features

### 1. **Tower Defense Games**
A tower defense game is a type of game where the player controls a base. The objective is to defend this base from incoming enemies by deploying weapons/tools to eliminate these enemies. In this case, the base that needs to be protected is the **Utopia District Walls**.

### 2. **Endless Mode**
The game is endless, meaning there is no winning condition. The player will keep playing and defeat as many enemies as possible.

---

## Battlefield Structure

The battlefield is divided into multiple lanes. Each lane will have the following:

1. **A Part of the Wall to Be Defended**  
   - Each part of the wall has a starting **HP** (health points) that decreases after being attacked.  
   - If the part of the wall is destroyed, this lane will no longer be considered an active lane and will be a **lost lane**.

2. **Weapons Deployed by the Player**  
   - The player can deploy different types of anti-titan weapons to defend the lane.

3. **Titans**  
   - Titans travel along the lane and attack the wall. Each titan has a **starting HP** that decreases as they are attacked.  
   - Titans will be at different distances from the wall depending on how far they have already moved. 

Each lane has a **danger level** calculated based on the number and types of titans inside the lane.

---

## Player’s Base

At the player's base, they have the option to:

- **View all available weapon types** and choose which to buy and deploy into an active lane.
- **Check current resources and score.**
- **See remaining HPs of all walls and titans** as well as the distance of each titan from the wall.
- **View approaching titans**, which will be added to the lanes in upcoming turns.

---

## Battle Phases

The battle progresses through 3 phases depending on the number of turns passed:

1. **Early Phase**
2. **Intense Phase**
3. **Grumbling Phase**

---

## Enemy Characters (Titans)

There are multiple types of titans in this game. Each titan will have the following stats:

1. **HP**: The health points of the titan.
2. **Damage**: The amount of damage the titan does when attacking a wall.
3. **Height**: The height of the titan in meters (does not affect gameplay).
4. **Distance from Walls**: How far the titan is from the walls in "Distance Units."
5. **Speed**: The distance the titan moves per turn in "Distance Units."
6. **Resources Value**: The amount of resources the player gains by defeating this titan.
7. **Danger Level**: How much this titan affects the lane’s danger level.

---

## Notes on "Distance Units"

- **Distance Units** are a special unit of measurement used specifically for this game and do not necessarily correspond to any realistic distance unit.
