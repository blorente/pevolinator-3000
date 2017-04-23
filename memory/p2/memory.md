PEVOLINATOR - 3000
===============
Pedro García Castillo, Borja Lorente Escobar

Cambios Relevantes
---------

### Representación de genes

Por conveniencia, hemos incluido una representación de genes basada en enteros, lo cual facilita la creación de individuos basados en permutaciones, como se verá más adelante. Estos genes exponen una interfaz similar a los genes basados en cadenas binarias, por lo que cambiar entre representaciones es transparente para el resto del programa.

### Poblaciones combinatorias

Hemos creado nuevas funciones para crear poblaciones combinatorias que mantengan el invariante de las permutaciones. Para mantener el invariante de evitar crear individuos duplicados, hemos intentado crear individuos por bloques, teniendo en cuenta el número máximo de permutaciones que hay en un genoma de tanaño n (pseudocódigo a continuación). Por supuesto, esta función depende de la corrección de `createAllPermutations`, pero ésta contiene chequeos para comprobar que cada individuo generado es de hecho una permutación. Somos conscientes de la ligera ineficiencia de generar todas las permutaciones para el último bloque, pero creemos que no es un problema demasiado grande ya que esta función solo se ejecuta una vez en cada lanzamiento del AG.

  ```java
  public static Population createIntegerCombinatorics(int populationSize, int genomeSize, int seed, int n) {
        Population randomPopulation = new Population();
        Random random = new Random(seed);
        Individual base = new Individual(GenomeFactory.createRandomPermutation(genomeSize, n, random));
        int numPermutations = factorial(genomeSize);

        // Crear bloques de todas las permutaciones de un individuo
        for (int i = 0; i < populationSize / numPermutations; i++) {
            randomPopulation.insertAll(createPermutations(base, numPermutations));
        }

        // Insertar los populationSize % numPermutations individuos restantes
        int remainingPermutations = populationSize - randomPopulation.getSize();
  	    Population lastBatch = createPermutations(base, remainingPermutations);
  	    for (int i = 0; i  < lastBatch.getSize(); i++) {
  	    	randomPopulation.addIndividual(lastBatch.getPopulation().get(i));
  	    }

        return randomPopulation;
    }
  ```

### Operadores adicionales

Hemos incluído los operadores requeridos de cruce y mutación, de manera que ahora se pueden seleccionar en la interfaz. Hay que denotar que no todos los operadores funcionan para todo tipo de problemas. Por ejemplo, el cruce granular de la práctica uno puede elegir puntos de cruce a mitad del gen, algo que claramente no funciona con genes basados en enteros. Los operadores añadidos son:

| Cruce | Mutación     |
| :------------- | :------------- |
| PMX       | Inserción       |
| OX        | Intercambio       |
| OX Priority Position       | Inversión       |
| OX Priority Order       | Heurística       |
| CX       |    -    |
| ERX       |    -    |
| N Point Permutation       |     -    |


### Operadores propios

- Cruce

- **Mutación por Inserción Circular (Round Insertion):** Este método es muy similar a la Mutación por Inserción común. El único cambio es que, a diferencia de ésta, la Inserción Circular puede sobrepasar los límites del genoma al elegir un segundo punto, en cuyo caso usaría aritmética modular para determinar cual es el segundo índice a intercambiar:

  ```java
	//Pseudocódigo
	void mutateIndividual(Individual ind) {
		for (int i = 0; i < mutationPoints; i++) {
			int n = ind.numGenes();
			int from = randomBetween(0, n-1);
			int to = (from + randomBetween(0, n-1)) % n;
			ind.swapGenes(from, to);
		}
	}
  ```

Resultados
------------
