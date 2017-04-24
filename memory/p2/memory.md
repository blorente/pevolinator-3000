PEVOLINATOR - 3000
===============
Pedro García Castillo, Borja Lorente Escobar

Cambios Relevantes
---------

### Representación de genes

Por conveniencia, hemos incluido una representación de genes basada en enteros, lo cual facilita la creación de individuos basados en permutaciones, como se verá más adelante. Estos genes exponen una interfaz similar a los genes basados en cadenas binarias, por lo que cambiar entre representaciones es transparente para el resto del programa.

### Poblaciones combinatorias

Hemos creado nuevas funciones para crear poblaciones combinatorias que mantengan el invariante de las permutaciones. Para mantener el invariante de evitar crear individuos duplicados, hemos intentado crear individuos por bloques, teniendo en cuenta el número máximo de permutaciones que hay en un genoma de tanaño n (pseudocódigo a continuación). Por supuesto, esta función depende de la corrección de `createPermutations`, pero ésta contiene chequeos para comprobar que cada individuo generado es de hecho una permutación.

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

- **Cruce por índices:** El cruce por índices consiste en tratar uno de los genes padre como lista de índices del segundo padre. Es decir, para el hijo h1 de los padres p1 y p2, el i-ésimo gen h1[i] viene dado por p2[p1[i]]. Por lo tanto, de los padres [2, 1, 3, 0] y [3, 1, 0, 2] saldrán los hijos [0, 1, 2, 3] y [0, 1, 2, 3]. El pseudicódigo sigue:

  ```java
  private Genome performIndexCross(Genome indices, Genome contents) {
      List<Gene> newGenes = new ArrayList<>();
      for (Gene g : indices.getGenes()) {
          newGenes.add(contents.getGene(g.intValue()));
      }
      return new Genome(newGenes);
  }
  ```

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

### Resultados significativos tras 20 ejecuciones:

Para esta sección hemos utilizado los siguientes parámetros:

|:--------:|:-------:|
|Algoritmo de selección| Ruleta|
|Algoritmo de cruce | ERX |
|Algoritmo de mutación| Mutación Heurística|
|Tam. población| 100 |
|Num. generaciones | 300|

El resto de parámetros están cogidos de los valores por defecto de la práctica anterior.

- datos12.dat

|Best Fitness | Best Individual |
|:--------:|:----------------------------:|
| 237560.0 | [3 11 9 4 7 5 2 1 8 6 0 10 ] |
| 233040.0 | [3 4 10 6 0 7 11 9 8 2 5 1 ] |
| 252472.0 | [5 9 4 1 6 3 10 8 7 0 11 2 ] |
| 244332.0 | [3 8 7 2 4 11 9 10 6 1 0 5 ] |
| 233040.0 | [3 4 10 6 0 7 11 9 8 2 5 1 ] |
| 224416.0 | [7 0 5 1 10 9 2 4 8 6 11 3 ] |
| 243490.0 | [3 0 7 8 2 4 6 10 9 1 11 5 ] |
| 224416.0 | [7 0 5 1 10 9 2 4 8 6 11 3 ] |
| 224416.0 | [7 0 5 1 10 9 2 4 8 6 11 3 ] |
| 245162.0 | [3 9 4 0 5 11 8 6 10 1 2 7 ] |
| 230704.0 | [3 4 10 2 6 9 11 8 7 5 0 1 ] |
| 230704.0 | [3 4 10 2 6 9 11 8 7 5 0 1 ] |
| 242748.0 | [3 1 0 4 11 10 8 5 6 2 9 7 ] |
| 233040.0 | [3 4 10 6 0 7 11 9 8 2 5 1 ] |
| 236006.0 | [3 8 4 1 9 2 5 7 6 0 11 10 ] |
| 224416.0 | [7 0 5 1 10 9 2 4 8 6 11 3 ] |
| 241002.0 | [3 2 9 4 7 5 0 1 8 6 11 10 ] |
| 247488.0 | [4 1 6 9 5 7 0 11 3 10 8 2 ] |
| 234788.0 | [3 11 10 2 7 5 8 1 4 6 0 9 ] |
| 235554.0 | [3 5 10 8 6 2 11 9 7 0 4 1 ] |
| 240718.0 | [7 0 11 3 2 4 10 6 9 1 8 5 ] |
| 238372.0 | [7 4 5 9 1 10 11 0 2 6 8 3 ] |
| 234188.0 | [3 4 10 0 6 8 11 9 5 7 2 1 ] |
| 230704.0 | [3 4 10 2 6 9 11 8 7 5 0 1 ] |
| 245124.0 | [9 6 0 3 10 7 5 8 4 2 11 1 ] |
| 224416.0 | [7 0 5 1 10 9 2 4 8 6 11 3 ] |
| 230704.0 | [3 4 10 2 6 9 11 8 7 5 0 1 ] |
| 233040.0 | [3 4 10 6 0 7 11 9 8 2 5 1 ] |
| 233060.0 | [7 2 5 1 10 9 0 8 4 6 11 3 ] |
| 243388.0 | [3 8 1 4 7 0 6 9 5 2 11 10 ] |
| 239818.0 | [4 9 8 3 5 7 0 6 10 1 11 2 ] |

| Mejor Fitness | Mejor Absoluto | Peor Fitness | Peor Absoluto | Media | Desv. Estándar | 
|:-------------:|:--------------:|:------------:|:-------------:|:-----:|:--------------:|
|224416.0       |[7 0 5 1 10 9 2 4 8 6 11 3 ]|252472.0|[5 9 4 1 6 3 10 8 7 0 11 2 ]|235881,4839|7521,943702|