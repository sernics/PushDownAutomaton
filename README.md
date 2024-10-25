# PushDownAutomaton
### Sergio Nicolás Seguí. 

La práctica consiste en implementar un autómata de pila que reconozca el lenguaje de los palíndromos. Para ello, se ha implementado una clase `PushDownAutomaton` que representa un autómata de pila. Además, se ha realizado la implementación de dos tipos
de autómatas de pila, el automáta de pila vacía y el autómata de pila con estado final.

Para ejecutar el código en java, se deben realizar los siguientes pasos:

```bash
javac src/Main.java src/Parser.java src/components/*.java
mkdir bin
javac -d bin src/Main.java src/Parser.java src/components/*.java
java -cp bin Main
```

Esta práctica, lo primero que te pregunta es el fichero de entrada, luego tiene tanto implementación de automata de pila por vaciado de pila como automata de pila por estado final, asi que te hace elegir por uno u otro. Luego, como tiene opción de traza o no, te pregunta por la traza. Y por último, te va preguntando cadenas a evaluar hasta que escribes la cadena exit.