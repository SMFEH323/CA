# CA - Cellular Automata Simulator

## Overview

This project contains two Java-based cellular automata simulations:

- A fire spread simulation
- Conway’s Game of Life

The goal of this project was to explore simulation systems, rule-based state transitions, and object-oriented programming concepts such as inheritance and polymorphism.

Both simulations model how simple local rules can produce complex global behavior over time.

---

## Features

### Fire Spreading Simulation

- Simulates fire spreading across a grid
- Models changing cell states over time
- Supports different fire propagation behaviors

### Conway’s Game of Life

- Simulates cellular evolution using Conway’s rules
- Cells change state based on neighboring cells
- Demonstrates emergent patterns and behaviors

### Object-Oriented Design

- Uses inheritance and polymorphism
- Modular simulation structure
- Reusable grid and cell logic

---

## How the Simulations Work

### Fire Simulation

The grid contains cells representing different states such as:

- Empty space
- Burning cells
- Burned cells

The simulation updates cell states over time to model fire spread behavior.

### Game of Life

Each cell follows Conway’s rules:

- Cells survive, die, or reproduce
- State changes depend on neighboring cells
- Complex patterns emerge from simple rules

---

## Tech Stack

- Java
- Object-Oriented Programming
- Cellular Automata Concepts
- Grid-Based Simulations

---

## Installation

### Requirements

- Java JDK installed on your system

### Compile the Project

```bash
javac FireApp.java GameOfLifeB3App.java
```

---

## Usage

### Run the Fire Simulation

```bash
java FireApp
```

### Run Conway’s Game of Life

```bash
java GameOfLifeB3App
```

---

## Current Limitations

- Basic visualization
- Limited simulation customization
- No save/load functionality
- Fixed rule configurations

---

## Future Improvements

- Add GUI controls for simulation parameters
- Add additional cellular automata models
- Improve visualization and animations
- Support larger grid sizes
- Add performance optimizations

---

## What This Project Demonstrates

- Cellular automata concepts
- Simulation modeling
- Grid-based state management
- Object-oriented software design
- Emergent behavior from rule-based systems

---
