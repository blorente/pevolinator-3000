PEVOLINATOR - 3000
===============
Pedro García Castillo, Borja Lorente Escobar

Cambios Relevantes
---------

### Representación de Programa

En esta iteración del proyecto, un individuo es un programa representado por un árbol de sintaxis abstracta. Un nodo del árbol está representado por su aridad, su operador, una referencia a su padre, y una lista de nodos hijo:

```java
public abstract class Node {  
	protected String op;
	public Node parent;
	public List<Node> children = new ArrayList<Node>();

  public abstract int arity();
}
```

En el caso de los nodos no-terminales, el campo `op` denota el tipo de operación a ejecutar, mientras que en el caso de los terminales ese campo representa la variable de entrada a la que hace referencia.

Con esta representación, los operadores de cruce y mutación se implementan fácilmente como operaciones sobre punteros.

Los genomas incluyen algunas funciones útiles comunes a todos los tipos de nodos. En particular, la función `listNodes()` devuelve enumeraciones de los descendientes de ese nodo, diferenciando entre nodos no-terminales y nodos terminales:

```java
public PairTuple<List<Node>, List<Node>> listNodes();
```

### Evaluación de Aptitud

La aptitud de un programa se mide según la cantidad de resultados que difieren de los esperados de un banco de entradas de prueba, por lo que es un problema de minimización. Actualmente el banco de entradas se forma con todas las posibles combinaciones de entradas de datos y de selección.

Por cada entrada de prueba, se forma una lista de valores de entrada que luego se pasa junto al programa al evaluador de epresiones. El evaluador de expresiones recorre el árbol de sintaxis, sustituyendo las referencias a variables por los valores asignados en la lista.

```java
public static boolean evalTree(Node root, boolean[] variables, int numD) {
	switch(root.op) {
	case "NOT":
		return !evalTree(root.children.get(0), variables, numD);
	case "OR":
		return (evalTree(root.children.get(0), variables, numD)
				|| evalTree(root.children.get(1), variables, numD));
	case "AND":
		return (evalTree(root.children.get(0), variables, numD)
				&& evalTree(root.children.get(1), variables, numD));
	case "IF":
		boolean cond = evalTree(root.children.get(0), variables, numD);
		if (cond) {
			return evalTree(root.children.get(1), variables, numD);
		} else {
			return evalTree(root.children.get(2), variables, numD);
		}
	default:
		char varType = root.op.charAt(0);
		int index = Integer.parseInt(root.op.substring(1));
		if (varType == 'A') {
			return variables[numD + index];
		} else if (varType == 'D') {
			return variables[index];
		}
		throw new RuntimeException("Unrecognized variable name: " + root.op);
	}
}
```

Por último, el resultado obtenido se compara con el que obtendría un multiplexor, y se contabiliza si difieren.

### Operadores



Resultados
------------

### Ejecuciones Significativas
