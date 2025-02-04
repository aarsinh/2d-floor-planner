# 2D Floor Planner

## Overview
This is a **Java Swing-based GUI application** for designing **2D floor plans** of houses. Users can add rooms, place furniture and fixtures, and ensure that elements do not overlap. The application follows an **object-oriented design** and provides an interactive user experience.

## Features
- **Add rooms** with specified dimensions and predefined colors:
  - Bedroom
  - Bathroom
  - Kitchen
  - Living/Dining
  - Walls
  - Outside
- **Room positioning**:
  - Rooms can be dragged and dropped
  - **Overlap prevention** ensures rooms do not overlap.
- **Drag and edit rooms**:
  - Move rooms with the mouse while maintaining 
- **Doors and Windows**:
  - Doors create an opening between rooms.
  - Validation prevents incorrect placements (e.g., windows between rooms, external doors in bedrooms/bathrooms).
- **Furniture & Fixtures**:
  - Add essential furniture (bed, sofa, table, chair, etc.).
  - Add fixtures (washbasin, stove, commode, etc.).
  - Rotate furniture in **90-degree** steps.
- **Save & Load Floor Plans**:
  - Plans can be saved in a custom file format and reloaded later.
- **Full-screen UI**:
  - The interface consists of a **Canvas** (for drawing the floor plan) and a **Control Panel** (for adding elements).

## Technologies Used
- **Java**
- **Swing (Java GUI Framework)**
- **Object-Oriented Programming (OOP)**
- **Custom File Handling for Save/Load**
