# Memory Management Simulator

## Overview
This program simulates a simple memory management system.

The computer has:
- 16 GB total memory
- 100 memory pages
- Each page size = 160 MB

Processes are randomly generated and allocated into memory pages until all memory is full.

The simulation demonstrates:
- Page allocation
- Memory fragmentation
- Unused memory calculation
- Process starting memory addresses

## How It Works

- A random number between 1 and 30 is generated.
- Each number represents 80 MB.
- Process size = (random number × 80 MB).
- The program calculates how many 160 MB pages are required.
- Memory is allocated starting from address 2000.
- The program continues until all 100 pages are filled.

## Output

The program prints a summary report in the format:

Process Id | Starting Memory Address | Size (MB) | Unused Space (MB)

## Technologies Used
- Java
- Random number generation
- Arrays
- Object-Oriented Programming
