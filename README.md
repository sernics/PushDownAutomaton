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