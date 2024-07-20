# Forest Fire Cellular Automaton

Welcome to the **Forest Fire Cellular Automaton** repository! This project simulates the spread of fire through a forest using a cellular automaton model, implemented in Java with Processing. 🌲🔥

## Overview



This simulation models a forest where each cell can be in one of three states:
- **Tree** 🌳 : (In multiple level of green : 🟩)
- **Burning Tree** 🔥 (In two level of red : 🟥)
- **Empty** 🌾 (In grey)

The rules governing the spread of fire are simple yet powerful:
1. A burning tree turns into an empty cell after some duration.
2. A tree will catch fire if at least one of its neighbors is burning with a certain oriented probability.
3. An empty cell remains empty.

## Features

- **Realistic Fire Spread**: Watch as the fire spreads through the forest, consuming trees and leaving behind empty spaces.
- **Customizable Parameters**: Adjust the initial number and form of empty cells, the distibution of resistance of tree represented by the darkness of its green, the initial number of burning trees, the oriented probability to represent wind, and the size of the matrix (forest).
- **Interactive Visualization**: See the simulation in action with a dynamic and visually appealing interface.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Processing IDE

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/forest-fire-automaton.git
    cd forest-fire-automaton
    ```

2. **Open the project in Processing**:
    - Launch the Processing IDE.
    - Open the `Allumez_le_feu.pde` file.

3. **Run the simulation**:
    - Click the play button in the Processing IDE to start the simulation.

## Usage

- **Adjust Parameters**: Modify the parameters at the top of the `Allumez_le_feu.pde` file to change the forest size, tree density, and initial burning trees.
- **Start/Stop Simulation**: Use the space bar to start or stop the simulation at any time.
- **Observe the Fire Spread**: Watch how the fire spreads through the forest and analyze the patterns that emerge.

## Contributing

Contributions are welcome! If you have ideas for improvements or new features, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgements

- Inspired by the classic cellular automaton models.
- Special thanks to the Processing community for their support and resources.

## Contact

For any questions or suggestions, please reach out to mateo.gob.pro@protonmail.com

Happy coding and enjoy the simulation! 🚀

